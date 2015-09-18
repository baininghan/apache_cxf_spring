package com.fancye.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.fancye.client.interceptor.MyMessageInterceptor;

/**
 * 客户端调用服务端的测试方法，处理一些复杂的业务
 * 
 * @author Fancye
 *
 */
public class ClientInvokeOther {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String wsdl = "http://localhost:8080/apache_cxf_spring/service/HelloServicePort?wsdl";
		
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(wsdl);
		
		// 除了在cxf-client-beans.xml 进行配置，还可以通过代码的方式设置超时时间
		HTTPClientPolicy policy = new HTTPClientPolicy();
		HTTPConduit conduit = (HTTPConduit)client.getConduit();
		policy.setConnectionTimeout(30000);// TCP连接握手时的连接超时设置，，默认30000毫秒
		policy.setAllowChunking(false);//
		policy.setReceiveTimeout(60000);// 数据响应超时，默认60000毫秒
		conduit.setClient(policy);
		
		// 添加 消息拦截器
		// 添加请求和响应的拦截器，Phase.RECEIVE只对In有效，Phase.SEND只对Out有效
		client.getInInterceptors().add(new MyMessageInterceptor(Phase.RECEIVE));
		client.getOutInterceptors().add(new MyMessageInterceptor(Phase.SEND));
		client.invoke("sayHi", "Fancye");
		
		System.exit(0);
	}

}
