package org.zezutom.spring_mvc_err_handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Resource(name="personService")
	private PersonService personService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> getPerson(@PathVariable Long id) {		
		return personService.search(id);
	}
	
	@RequestMapping(value="/person", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> getPeopleAsJson() {
		return personService.search();
	}	
		
}
