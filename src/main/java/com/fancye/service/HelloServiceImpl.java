package com.fancye.service;

import javax.jws.WebService;

/**
 * 
 * @author Fancye
 *
 */
@WebService
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHi(String name) {
		System.out.println("Hi, " + name);
	}

}
