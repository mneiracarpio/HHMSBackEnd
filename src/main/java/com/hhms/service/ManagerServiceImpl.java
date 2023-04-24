package com.hhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Manager;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.ManagerRepository;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager, Long> implements ManagerService {

	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);
	
	@Autowired
	private ManagerRepository managerRepository;
	
	public ManagerServiceImpl(BaseRepository<Manager, Long> baseRepository) {
		super(baseRepository);
	}

	@Override
	public Manager validateManager(String username, String password) throws Exception {
		System.out.println(username);
		System.out.println(password);
		Manager manager = managerRepository.validateManager(username, password);
		System.out.println(manager);
		return manager;
	}

	@Override
	public Manager getByUserName(String username) throws Exception {
		Manager manager = managerRepository.getByUserName(username);
		//System.out.println(manager);
		return manager;
	}

	@Override
	public int existsInEmpMan(String username) throws Exception {
		int exists = managerRepository.existsInEmpMan(username);
		return exists;
	}
	
	public Manager register(Manager manager) throws Exception {
		try {
			manager.setPassword(bcrypt.hash(manager.getPassword()));
			manager = baseRepository.save(manager);
			return manager;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Manager getByEmail(String email) throws Exception {
		Manager manager = managerRepository.getByEmail(email);
		return manager;	
	}
	
	

}
