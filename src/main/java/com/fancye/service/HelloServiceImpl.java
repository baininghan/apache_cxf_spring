package com.fancye.service;

/**
 * 
 * @author Fancye
 *
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHi(String name) {
		System.out.println("Hi, " + name);
	}

}
