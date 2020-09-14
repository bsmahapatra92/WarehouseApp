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


import com.cloudtree.in.model.Uom;
import com.cloudtree.in.service.IUomService;


@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService service;

	// 1.Get form
	// show form
	// by using get ruquest

	/*
	 * @GetMapping("/register") public String showRegister() {
	 * 
	 * return "UomRegister"; }
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	// 2.save operation
	// by using Post request
	@PostMapping("/save")
	public String save(@ModelAttribute Uom uom, Model model) {

		Integer id = null;
		String message = null;
		// perform save operation
		id = service.saveUom(uom);

		// generate confirmation message
		message = "Uom '" + id + "' saved successfully";
		// send to confirmation message to UI
		model.addAttribute("message", message);
		model.addAttribute("uom", new Uom());
		// Go to page
		// return "UomRegister";
		return "UomRegister";

	}

		
		
		
		//3. Display
		@GetMapping("/all")
		public String fetchAll(Model model) {
			List<Uom> list = service.getAllUoms();
			model.addAttribute("list", list);
			return "UomData";
		}
		
		
		//4. Remove one by Id
		@GetMapping("/delete/{id}")
		public String removeById(
				@PathVariable Integer id,Model model) {
				
				String msg = null;
				if(service.isUomExist(id)) {
				service.deleteUom(id);
				msg ="Uom '"+id+"' Deleted";
				} else {
					model.addAttribute("Uom '"+id+"' not existed");
				}	
				model.addAttribute("message", msg);
				
				//Fetch other Rows And Display
				
				List<Uom> list = service.getAllUoms();
				model.addAttribute("list", list);
				return "UomData";
			
		}
		
		/**
		 * On Click Edit hyperlink at UI,
		 * read one path variable and fetch data from
		 * service, if exist send to edit page
		 * else redirect to data page
		 */
		
		//5. Show Edit Page with data
		
		@GetMapping("/edit/{id}")
		public String showEdit(@PathVariable Integer id, Model model) {
			
			Optional<Uom> opt = service.getOneUom(id);
			String page = null;
			if(opt.isPresent()) {
				
				Uom u = opt.get();
				model.addAttribute("uom", u);
				page = "UomEdit";
			}else {
				return "redirect:../all";
			}
			
			return page;
		}
		

		/**
		 *  On Click Update button,read form data and perform update operation 
		 *  send back to Data page with success message.
		 *  
		 */
		//6. Update: on click update
		@PostMapping("/update")
		public String updateUom(@ModelAttribute Uom uom, Model model) {
			
			service.updateUom(uom);
			String msg = "Uom '"+uom.getId()+"' Updated";
			 
		 	model.addAttribute("message", msg);
		 
		 	//Fetch other Rows And Display

			List<Uom> list = service.getAllUoms();
			model.addAttribute("list", list);
		 
		return "uomData";
	}
		

}
