package com.hhms.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhms.entity.Manager;

@Repository
public interface ManagerRepository extends BaseRepository<Manager, Long> {

	@Query(value= "SELECT * from manager WHERE username = :username AND password = :password AND state='A'",
			nativeQuery = true )
	Manager validateManager(@Param("username") String username, @Param("password") String password);

	@Query(value= "SELECT * from manager WHERE username = :username AND state='A'",
			nativeQuery = true )
	Manager getByUserName(@Param("username") String username);
	
	//List<Manager> findByUsernameTrue(String punchNumber);
	@Query(value="SELECT nvl( "
			+ " (SELECT 1 FROM EMPLOYEE WHERE punch_number=:username "
			+ " UNION "
			+ " SELECT 2 FROM MANAGER WHERE USERNAME = :username "
			+ " ),0) from dual",
			nativeQuery = true )
	int existsInEmpMan(@Param("username") String username);

	@Query(value= "SELECT * from manager WHERE email = :email AND state='A'",
			nativeQuery = true )
	Manager getByEmail(@Param("email") String email);
	
}
