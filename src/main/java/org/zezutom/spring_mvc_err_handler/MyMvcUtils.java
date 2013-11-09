package org.zezutom.spring_mvc_err_handler;

import org.springframework.web.servlet.ModelAndView;

public class MyMvcUtils {

	private MyMvcUtils() {}
	
	public static final ModelAndView getMaV(String viewName, String key, Object value) {
		return new ModelAndView(viewName, key, value);
	}
}
