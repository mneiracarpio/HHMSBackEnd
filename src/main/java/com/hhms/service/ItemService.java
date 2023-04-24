package com.hhms.service;

import java.util.List;

import com.hhms.entity.Department;
import com.hhms.entity.Item;

public interface ItemService extends BaseService<Item, Long> {
	
	List<Item> listItemByDepartment(Long departmentId);
	
	List<Department> listItemDepartment();

}
