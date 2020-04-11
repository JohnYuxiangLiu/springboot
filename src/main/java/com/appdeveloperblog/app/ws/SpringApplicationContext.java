package com.appdeveloperblog.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//this class as a medium to access other classes
public class SpringApplicationContext implements ApplicationContextAware{
	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		CONTEXT=context;
	}
	
//	beanName can be changed to anyother beans, so they can be accessed anywhere using this method
	public static Object getBean (String beanName) {
		return CONTEXT.getBean(beanName);
	}
	
}
