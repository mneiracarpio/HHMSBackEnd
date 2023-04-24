package com.hhms.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hhms.entity.Employee;
import com.hhms.entity.Manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtilService {
  // LLAVE_MUY_SECRETA => [Base64] => TExBVkVfTVVZX1NFQ1JFVEE=
  private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";

  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas
  
  @Autowired 
  private ManagerServiceImpl managerServiceImpl;

  @Autowired 
  private EmployeeServiceImpl employeeServiceImpl;
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(extractAllClaims(token));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserDetails userDetails, long tokenValidity) {
    Map<String, Object> claims = new HashMap<>();
    // Agregando informacion adicional como "claim"
    var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
    Manager manager;
    Employee employee;
	try {
		claims.put("rol", rol);
	    // check if it's an employee or manager
	    int exists = managerServiceImpl.existsInEmpMan(userDetails.getUsername());
	    // when it's an Employee
	 	if (exists == 1 ) {
	 		employee = employeeServiceImpl.searchByPunchNumber(userDetails.getUsername());
	 		claims.put("employeeId", employee.getEmployeeId());		        
	 	} else if (exists == 2) {
			manager = managerServiceImpl.getByUserName(userDetails.getUsername());
		    claims.put("managerId", manager.getManagerId());
	 	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
    return createToken(claims, userDetails.getUsername(), tokenValidity==0?JWT_TOKEN_VALIDITY:tokenValidity);
  }

  private String createToken(Map<String, Object> claims, String subject, long tokenValidity) {
	  return Jwts
		    .builder()
		    .setClaims(claims)
		    .setSubject(subject)
		    .setIssuedAt(new Date(System.currentTimeMillis()))
		    .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
		    .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
		    .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}