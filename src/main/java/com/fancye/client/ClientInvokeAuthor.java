/**
 * 
 */
package com.fancye.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.fancye.client.interceptor.AuthOutInterceptor;
import com.fancye.client.interceptor.MyMessageInterceptor;

/**
 * @author Fancye
 * @2015-10-13
 *
 */
public class ClientInvokeAuthor {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String wsdl = "http://localhost:8080/apache_cxf_spring/service/HelloDoubleServicePort?wsdl";
		
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(wsdl);
		
		// 添加 消息拦截器
		// 添加请求和响应的拦截器，Phase.RECEIVE只对In有效，Phase.SEND只对Out有效
//		client.getInInterceptors().add(new MyMessageInterceptor(Phase.RECEIVE));
		client.getOutInterceptors().add(new AuthOutInterceptor());
		client.invoke("sayDoubleHi", "Fancye");
		
		System.exit(0);
	}

}
