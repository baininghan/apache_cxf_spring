package com.fancye.boot;

import javax.xml.ws.Endpoint;

import com.fancye.service.HelloServiceImpl;

public class Boot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServiceImpl hello = new HelloServiceImpl();
		Endpoint.publish("http://localhost:8080/apache_cxf_spring/service/HelloServicePort", hello);
	}

}
