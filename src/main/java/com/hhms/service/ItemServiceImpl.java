package com.hhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhms.entity.Department;
import com.hhms.entity.Item;
import com.hhms.repository.BaseRepository;
import com.hhms.repository.DepartmentRepository;
import com.hhms.repository.ItemRepository;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, Long> implements ItemService {
	
	@Autowired
	ItemRepository		itemRepository;

	@Autowired
	DepartmentRepository		departmentRepository;
	
	public ItemServiceImpl(BaseRepository<Item, Long> baseRepository) {
		super(baseRepository);
	}

	@Override
	public List<Item> listItemByDepartment(Long departmentId) {
		List<Item> items = itemRepository.listItemByDepartment(departmentId);
		return items;
	}

	@Override
	public List<Department> listItemDepartment() {
		List<Department> departments = departmentRepository.listItemDepartments();
		return departments;
	}

}
