package com.hhms.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Manager;
import com.hhms.entity.ResetAuthenticationLog;
import com.hhms.repository.BaseRepository;

@Service
public class ResetAuthenticationLogServiceImpl extends BaseServiceImpl<ResetAuthenticationLog, Long> implements ResetAuthenticationLogService {

	@Autowired
    private OperationServiceImpl operationServiceImpl;
	
	
	public ResetAuthenticationLogServiceImpl(BaseRepository<ResetAuthenticationLog, Long> baseRepository) {
		super(baseRepository);
	}

	@Override
	public void registerLog(Manager manager, Long operation) throws Exception {
		// current time
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
	    //write the log
		ResetAuthenticationLog resetAuthenticationLog = new ResetAuthenticationLog();
		resetAuthenticationLog.setEmail(manager.getEmail());
		resetAuthenticationLog.setLogTime(currentTimestamp);
		resetAuthenticationLog.setManager_id(manager.getManagerId());
		resetAuthenticationLog.setOperation(operationServiceImpl.findById(operation));			
		save(resetAuthenticationLog);
		
	}
	
	

}
