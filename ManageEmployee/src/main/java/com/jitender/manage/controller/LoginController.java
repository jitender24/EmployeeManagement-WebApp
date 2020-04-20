package com.jitender.manage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jitender.manage.service.LoginService;
import com.jitender.manage.model.Employee;
import com.jitender.manage.service.EmployeeService;


@Controller
@RequestMapping("/")
public class LoginController {
	
	public LoginController() {
		System.out.println("LoginController()");
	}

	@Autowired
	LoginService service;
	
    @RequestMapping(value = "/login", method =RequestMethod.GET)
	public String ShowLoginPage() {
		return "login"; 
	} 
 
    @RequestMapping(value = "/login", method =RequestMethod.POST)
    public String HandleLoginRequest(@RequestParam String name,@RequestParam String password, ModelMap model) {
    	//System.out.println(name);
    	//ModelAndView mv = new ModelAndView();
    	if(!service.validateUser(name,password)){
    	 	model.put("errorMessage","Invalide Credentail");
    	 
            return "login";
        }
     
         model.put("name", name);
    	 model.put("password",password);
    	// get customers from the service
// 		List<Employee> listEmployee = employeeService.getAllEmployees();
// 				
// 		// add the customers to the model
// 		model.addAttribute("listEmployee", listEmployee);
 		
    	
    	return "welcome";
    }   
///////////////////////////////////////////////////////////////////
    
    @Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/EmployeeList")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if (employee.getId() == 0) { // if employee id is 0 then creating the
			// employee other updating the employee
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/EmployeeList");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);

		return new ModelAndView("redirect:/EmployeeList");
		
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}

    
    
    
	
}