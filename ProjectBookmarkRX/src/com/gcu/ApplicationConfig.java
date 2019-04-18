package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.gcu.business.BookBusinessInterface;
import com.gcu.business.BookBusinessService;
import com.gcu.controller.UserController;

@Configuration
public class ApplicationConfig {
	
	/**
	 * 
	 * @return 
	 */
	@Bean(name = "userController")
	@Scope(value = "singleton")
	public UserController getUserController() {
		return new UserController();
	}

	/**
	 * @since 11-3-18
	 * @return
	 */
	@Bean(name = "ordersService", initMethod = "init", destroyMethod = "destroy")
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public BookBusinessInterface getOrdersService() {
		return new BookBusinessService();
	}

}
