package com.hhms.service;

import com.hhms.entity.ParamValue;

public interface ParamValueService  extends BaseService<ParamValue, String> {

	ParamValue getLaborFactor() throws Exception;
}
