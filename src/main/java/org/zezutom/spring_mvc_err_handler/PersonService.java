package org.zezutom.spring_mvc_err_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("personService")
public class PersonService {

	private Map<Long, String> nameMap = new HashMap<Long, String>();
	
	{
		nameMap.put(1l, "Peter");
		nameMap.put(2l, "Hannah");
		nameMap.put(3l, "Ann");
	}
	
	public Person byId(Long id) {
		if (!nameMap.containsKey(id))
			throw new NoSuchPersonException(id);
		
		return getPerson(id);
	}
	
	public List<Person> search() {
		return search(null);
	}
	
	public List<Person> search(Long id) {
		List<Person> people = new ArrayList<Person>();
		
		if (id != null) 
			people.add(byId(id));
		else {
			for (Long key : nameMap.keySet()) {
				people.add(getPerson(key));
			}			
		}
		return people;
	}
	
	private Person getPerson(Long id) {
		return new Person(id, nameMap.get(id));
	}
}
