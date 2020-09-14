package com.cloudtree.in.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudtree.in.model.ShipmentType;
import com.cloudtree.in.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipmenttype")
public class ShipmentTypeController {

	@Autowired
	private IShipmentTypeService service;

	//1. Show Register Page
	/**
	 * Url  /register, GET=> ShipmentTypeRegister.html
	 */
	@RequestMapping("/register")
	public String showRegister(Model model) {
		//Form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}


	//2. save : on Click submit
	/**
	 * 	URL: /save ,Type, POST
	 * 	GOTO: ShipmentType Register
	 * 
	 */

	@PostMapping("/save")
	public String save(
			//read the form data from UI(Given by container)
			@ModelAttribute ShipmentType shipmentType,
			Model model) //To send Data To UI
	{
		//perform save operation	
		Integer id = service.saveShipmentType(shipmentType);

		//Construct One Message
		String message = "ShipmentType '"+id+"' Saved Successfully";
		//send message to UI
		model.addAttribute("message", message);

		//Form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		//GOTO page
		return "ShipmentTypeRegister";
	}

	//3. Display
	/**
	 * 
	 */
	@GetMapping("/all")
	public String fethcAll(Model model) {

		List<ShipmentType> list = service.getAllShipmentTypes();
		model.addAttribute("list", list);
		return "ShipmentTypeData";
	}

	/**
	 *  From client (browser) enter URL Like
	 *  /delete/3 -> copy 10 into id path variable
	 *  read id using @PathVariable DataType key syntax
	 *  After Delete successful send message to UI
	 *  Also select other rows from DB.
	 */


	//4. Remove one by Id
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id,Model model) {


		String msg = null;			
		if(service.isShipmentTypeExist(id)) {

			service.deleteShipmentType(id);
			msg ="ShipmentType '"+id+"' Deleted";
		} else {
			//					
			msg="ShipmentType '"+id+"' not existed";
		}

		model.addAttribute("message", msg);

		//Fetch other Rows And Display

		List<ShipmentType> list = service.getAllShipmentTypes();
		model.addAttribute("list", list);

		return "ShipmentTypeData";

	}
	
	/**
	 * On Click Edit hyperlink at UI,
	 * read one path variable and fetch data from
	 * service, if exist send to edit page
	 * else redirect to data page
	 */

	//5. Show Edit Page with data
	
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model) {
		
		Optional<ShipmentType> opt = service.getOneShipmentType(id);
		String page=null;
		if(opt.isPresent()) {
			ShipmentType st = opt.get();
			model.addAttribute("shipmentType", st);
			page = "ShipmentTypeEdit";
		}else {
			page="redirect:../all";
		}
		
		return page;
	}
	
	/**
	 *  On Click Update button,reaad form data and perform update operation 
	 *  send back to Data page with success message.
	 *  
	 */
	//6. Update: on click update
	@PostMapping("/update")
	public String update(@ModelAttribute ShipmentType shipmentType, Model model) {
		
		 service.updateShipmentType(shipmentType);
		 String msg = "ShipmentType '"+shipmentType.getId()+"' Updated";
		 
		 	model.addAttribute("message", msg);
		 
		 	//Fetch other Rows And Display

			List<ShipmentType> list = service.getAllShipmentTypes();
			model.addAttribute("list", list);
		 
		return "shipmentTypeData";
	}

}
