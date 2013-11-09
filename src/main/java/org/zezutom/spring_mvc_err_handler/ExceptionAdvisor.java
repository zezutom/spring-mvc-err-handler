package org.zezutom.spring_mvc_err_handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@ControllerAdvice
public class ExceptionAdvisor {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView fallBack(Exception ex, HttpServletRequest request) {		
		return handleException("An unexpected exception occurred", request);
	}

	@ExceptionHandler(NoSuchPersonException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView noSuchPerson(NoSuchPersonException ex, HttpServletRequest request) {
		return handleException(ex.getMessage(), request);
	}
	
	private ModelAndView handleException(String message, HttpServletRequest request) {
		ModelAndView mav = MyMvcUtils.getMaV("error", "message", message);
				
		if (MediaType.APPLICATION_JSON_VALUE.equals(request.getHeader("Accept")))					
			mav.setView(new MappingJacksonJsonView());

		return mav;		
	}
	
	
}
