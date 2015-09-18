/**
 * 
 */
package com.fancye.client.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

/**
 * @author Fancye
 * @2015-9-18
 *
 */
public class MyMessageInterceptor extends AbstractPhaseInterceptor<Message> {

	// 至少要一个带参的构造函数
	public MyMessageInterceptor(String phase) {
		super(phase);
	}

	/**
	 * 处理中间件的消息
	 */
	@Override
	public void handleMessage(Message msg) throws Fault {
		System.out.println("=====================");
		System.out.println(msg);
		System.out.println("=====================");
		
		if (msg.getDestination() != null) {
			System.out.println(msg.getId() + " : " + msg.getDestination().getAddress() + "," + msg.getDestination().getMessageObserver());
		}
		
		if (msg.getExchange() != null) {
			System.out.println(msg.getExchange().getInMessage() + " : " + msg.getExchange().getInFaultMessage());
			System.out.println(msg.getExchange().getOutMessage() + " : " + msg.getExchange().getOutFaultMessage());
		}
	}

}
