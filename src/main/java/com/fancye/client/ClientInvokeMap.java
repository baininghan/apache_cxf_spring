package com.fancye.client;

import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fancye.client.adapter.StringObjectMap;
import com.fancye.service.HelloService;

/**
 * 客户端测试，服务接口返回参数类型，带接口 Map 的形式
 * @author Fancye
 *
 */
public class ClientInvokeMap {

	public static void main(String[] args) throws Exception {
		// WebService 服务暴露出的接口URI
		String wsdl = "http://localhost:8080/apache_cxf_spring/service/HelloServicePort?wsdl";
		
		// （1）调用 WebService 接口，采用代码编写实现
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(HelloService.class);
//		factory.setAddress(wsdl);
//		HelloService client = (HelloService)factory.create();
//		List<Map<String,Object>> list = client.getListOfMap("a", "Fancye");
//		System.out.println(list.get(0).get("a"));
		
		//（2）调用 Spring 客户端配置文件，采用代码编写实现
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/fancye/client/cxf-client-beans.xml");
//		HelloService client = (HelloService)context.getBean("helloService");
//		List<Map<String,Object>> list = client.getListOfMap("a", "Fancye");
//		System.out.println(list.get(0).get("a"));
		
		// （3）动态调用，不依赖于服务器接口文件
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(wsdl);
		Object[] o = client.invoke("getListOfMap", "a", "Fancye");
		// 此处 o[0]总是返回 `com.fancye.service.StringObjectMap` 这个类型，
		// 跟实际项目中的 `com.fancye.client.adapter` 包路径不一样，暂时还不知道怎么解决
		System.out.println(o[0]);
		
		System.exit(0);
	}

}
