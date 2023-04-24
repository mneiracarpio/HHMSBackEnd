package com.hhms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhms.entity.Item;
import com.hhms.entity.MessageResponse;
import com.hhms.service.DepartmentServiceImpl;
import com.hhms.service.ItemServiceImpl;

@RestController
@CrossOrigin(origins="")
@RequestMapping("/v1/item")
public class ItemController extends BaseControllerImpl<Item, ItemServiceImpl> {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	/**
	 * It brings all the items for Events Department.
	 * It works only for events (catering) items
	 * @return ResponseEntity<?> object, it returns a List of Item Objects for Events Department if it is ok. It returns an error message if it fails.
	 */
	@GetMapping("/bqt/listitems")
	public ResponseEntity<?> listBqtItems() {
		try {
			Long departmentId=(long) 2;
			return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.listItemByDepartment(departmentId) );
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No items found for Events Department."));
		}
	}

	/**
	 * It brings all the items for Housekeeping Department.
	 * It works only for housekeeping items
	 * @return ResponseEntity<?> object, it returns a List of Item Objects for Housekeeping Department if it is ok. It returns an error message if it fails.
	 */
	@GetMapping("/hskp/listitems")
	public ResponseEntity<?> listHskpItems() {
		try {
			Long departmentId=(long) 1;
			return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.listItemByDepartment(departmentId) );
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No items found for Events Department."));
		}
	}
	
	/**
	 * It changes item state
	 * @return ResponseEntity<?> object, it returns the item updated if it is ok. It returns an error message if it fails.
	 */
	@PutMapping("/updatestate/{id}")
	public ResponseEntity<?> updateState(@PathVariable Long id, @RequestBody Item newItem) {
		try {
			Item item = itemServiceImpl.findById(id);
			item.setState(newItem.getState());
			return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.update(id, item));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: When try to change item state"));
		}
	}

	/**
	 * It update item fields (
	 * @return ResponseEntity<?> object, it returns the item updated if it is ok. It returns an error message if it fails.
	 */
	@PutMapping("/updateitem/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item newItem) {
		try {
			Item item = itemServiceImpl.findById(id);
			item.setName(newItem.getName());
			if (newItem.getDepartment() != null ) {
				item.setDepartment(departmentServiceImpl.findById(newItem.getDepartment().getDepartmentId()));
			}
			return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.update(id, item));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: When try to change item state"));
		}
	}
	
	/**
	 * It update item fields (
	 * @return ResponseEntity<?> object, it returns the item updated if it is ok. It returns an error message if it fails.
	 */
	@PostMapping("/registationitem")
	public ResponseEntity<?> registerItem(@RequestBody Item newItem) {
		try {
			//Item item = itemServiceImpl.findById(id);
			Item item = new Item();
			item.setName(newItem.getName());
			item.setDepartment(departmentServiceImpl.findById(newItem.getDepartment().getDepartmentId()));
			item.setState("A");
			Item itemCreated = itemServiceImpl.save(item);
			itemCreated.setItem(itemCreated.getItemId());
			itemServiceImpl.update(itemCreated.getItemId(), itemCreated);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The Item was successfully created"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: When trying to create a new item"));
		}
	}
	
	/**
	 * It brings all valid departments
	 * @return ResponseEntity<?> object, it returns a List of Department Objects if it is ok. It returns an error message if it fails.
	 */
	@GetMapping("/listdepartment")
	public ResponseEntity<?> listDepartment() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.listItemDepartment() );
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No departments were found."));
		}
	}
	
}
