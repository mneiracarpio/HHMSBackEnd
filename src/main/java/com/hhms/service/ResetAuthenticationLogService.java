package com.hhms.service;

import com.hhms.entity.Manager;
import com.hhms.entity.ResetAuthenticationLog;

public interface ResetAuthenticationLogService extends BaseService<ResetAuthenticationLog, Long> {

	void registerLog(Manager manager, Long operation) throws Exception;

		
}
