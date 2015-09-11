package com.fancye.service;

/**
 * 
 * @author Fancye
 *
 */
public class HelloDoubleServiceImpl implements HelloDoubleService {

	@Override
	public void sayDoubleHi(String name) {
		System.out.println("Hi, " + name + " Double !!!");
	}

}
