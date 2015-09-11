package com.fancye.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 客户端调用服务端的测试方法
 * @author Fancye
 *
 */
public class ClientInvoke {

	public static void main(String[] args) throws Exception {
		// WebService 服务暴露出的接口URI
		String wsdl = "http://localhost:8080/apache_cxf_spring/service/HelloServicePort?wsdl";
		
		// （1）调用 WebService 接口，采用代码编写实现
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(HelloService.class);
//		factory.setAddress(wsdl);
//		HelloService client = (HelloService)factory.create();
//		client.sayHi("Fancye");
		
		//（2）调用客户端配置文件，采用代码编写实现
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/fancye/service/cxf-client-beans.xml");
//		HelloService client = (HelloService)context.getBean("helloService");
//		client.sayHi("Fancye");
		
		// （3）动态调用，不依赖于服务器接口文件
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(wsdl);
		client.invoke("sayHi", "Fancye");
		
		//（4）还可以使用其他第三方的插件生成客户端代码，比如MyEclipse自带的JAX插件
	}

}
