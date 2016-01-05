package com.fancye.client;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.ProxyFactory;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fancye.client.adapter.StringObjectMap;
import com.fancye.client.adapter.StringObjectMap.StringObjectEntry;
import com.fancye.client.interceptor.MyMessageInterceptor;
import com.fancye.service.HelloService;
import com.fancye.service.Person;

/**
 * 客户端调用服务端的测试方法
 * @author Fancye
 *
 */
public class ClientInvoke {

	public static void main(String[] args) throws Exception {
		// WebService 服务暴露出的接口URI
		String wsdl = "http://localhost:8080/apache_cxf_spring/service/HelloServicePort?wsdl";
		String wsdl2 = "http://localhost:8080/apache_cxf_spring/service/HelloDoubleServicePort?wsdl";
		
		// （1）调用 WebService 接口，采用代码编写实现
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(HelloService.class);
//		factory.setAddress(wsdl);
//		HelloService client = (HelloService)factory.create();
//		client.sayHi("Fancye");
		
		//（2）调用 Spring 客户端配置文件，采用代码编写实现
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/fancye/client/cxf-client-beans.xml");
//		HelloService client = (HelloService)context.getBean("helloService");
//		client.sayHi("Fancye");
		
		// （3）动态调用，不依赖于服务器接口文件
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(wsdl2);
		client.invoke("sayDoubleHi", "Fancye");
		
		//（4）还可以使用其他第三方的插件生成客户端代码，比如MyEclipse自带的JAX插件
		
		// （5）
//		DynamicClientFactory dcf = DynamicClientFactory.newInstance();
//		Client client = dcf.createClient(wsdl, Person.class.getClassLoader());
//		 
//		Object person = Thread.currentThread().getContextClassLoader().loadClass("com.fancye.service.Person").newInstance();
//		 
//		Method m = person.getClass().getMethod("setName", String.class);
//		m.invoke(person, "Joe Schmoe");
//		 
//		client.invoke("addPerson", person);
		System.exit(0);
	}

}
