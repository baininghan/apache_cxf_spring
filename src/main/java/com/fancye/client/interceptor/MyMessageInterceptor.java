/**
 * 
 */
package com.fancye.client.interceptor;

import java.util.Iterator;

import javax.xml.soap.SOAPMessage;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

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
	
	public MyMessageInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	/**
	 * 处理中间件的消息
	 */
	@Override
	public void handleMessage(Message msg) throws Fault {
		System.out.println("=====================");
		System.out.println(msg);
		System.out.println("=====================");
		
		System.out.println("请求参数列表");
		for (Iterator<Object> iterator = MessageContentsList.getContentsList(msg).iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			System.out.println(type);
		}
		System.out.println(MessageContentsList.getContentsList(msg).get(0));
		
		if (msg.getDestination() != null) {
			System.out.println(msg.getId() + " : " + msg.getDestination().getAddress() + "," + msg.getDestination().getMessageObserver());
		}
		
		if (msg.getExchange() != null) {
			System.out.println(msg.getExchange().getInMessage() + " : " + msg.getExchange().getInFaultMessage());
			System.out.println(msg.getExchange().getOutMessage() + " : " + msg.getExchange().getOutFaultMessage());
		}
		
		SOAPMessage mess = msg.getContent(SOAPMessage.class);
	}

}
