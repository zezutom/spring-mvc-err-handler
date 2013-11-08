package org.zezutom.spring_mvc_err_handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@ControllerAdvice
public class ExceptionAdvisor {

	@ExceptionHandler(Exception.class)
	public ModelAndView handle500(Exception ex, HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView("500", "message", "An unexpected exception occurred");
		
		final String acceptedType = request.getHeader("Accept");
		
		if (MediaType.APPLICATION_JSON_VALUE.equals(acceptedType))					
			mav.setView(new MappingJacksonJsonView());

		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return mav;
	}
	
}
