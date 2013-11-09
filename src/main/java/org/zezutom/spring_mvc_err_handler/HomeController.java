package org.zezutom.spring_mvc_err_handler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Resource(name="personService")
	private PersonService personService;
	
	@RequestMapping("/")
	public String home() {
		//throw new IllegalStateException();
		return "home";
	}
	
	@RequestMapping(value="/person/{id}/json", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> searchById(@PathVariable Long id) {		
		return personService.search(id);
	}
	
	@RequestMapping(value="/person/json", method=RequestMethod.GET)
	@ResponseBody
	public List<Person> searchAll() {
		return personService.search();
	}	
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.GET)
	public ModelAndView defaultSearchById(@PathVariable Long id) {
		return MyMvcUtils.getMaV("search", "result", personService.search(id));
	}

	@RequestMapping(value="/person", method=RequestMethod.GET)
	public ModelAndView defaultSearchAll() {
		return MyMvcUtils.getMaV("search", "result", personService.search());
	}
	
}
