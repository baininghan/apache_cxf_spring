/**
 * 
 */
package com.fancye.service.interceptor;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.frontend.FaultInfoException;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

/**
 * <pre>
 * 客户端的每一次请求，都要带着请求头，而服务端就去解析请求头，看里面带的token是否跟预期的一致
 * 1. 在客户端发送webservice调用以前，构造一个SOAP消息头，把token带过去
 * 2. 在服务端解析消息头，把指定的那个头字段解析出来，对比 两边的token是否相同
 * 
 * @author Fancye
 * @2015-10-13
 * 
 */
public class AuthorizationInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private SAAJInInterceptor saaIn = new SAAJInInterceptor();
	private String namespaceURI = "http://test.com/auth";
	private String localPart = "MyAuthHeader";
	private String token;

	public void setToken(String token) {
		this.token = token;
	}

	public AuthorizationInterceptor() {
		super(Phase.PRE_PROTOCOL);
		getAfter().add(SAAJInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("=========>message:" + message);
		
//		if (!checkQnameHeader(message) && !checkMessageHeader(message)) {
//			throw new IllegalArgumentException("The Token wrong!");
//		}
		
		System.out.println(message.getExchange().getInMessage());
		System.out.println(message.getExchange().getInFaultMessage());
		System.out.println(message.getExchange().getOutMessage());
		System.out.println(message.getExchange().getOutFaultMessage());
//		if (true) {
//			throw new Fault((Message) message.getExchange().getInMessage());
//		}
		
	}
	
	public void handleFault(SoapMessage message) throws Fault {
		
	}

	/**
	 * 校验指定的Qname头
	 * 
	 * @param message
	 * @return
	 */
	private boolean checkQnameHeader(SoapMessage message) {
		SoapHeader header = (SoapHeader) message.getHeader(new QName(namespaceURI, localPart));
		if (header == null)
			return false;
		// ElementNSImpl是内部专用 API, 可能会在未来发行版中删除
		ElementNSImpl ei = (ElementNSImpl) header.getObject();
		String mytoken;
		try {
			mytoken = ei.getFirstChild().getFirstChild().getTextContent();
			return mytoken.equals(token);
		} catch (Exception e) {
			throw new IllegalArgumentException("Method --> checkQnameHeader error", e);
		}
	}

	/**
	 * 校验消息头
	 * 
	 * @param message
	 * @return
	 */
	private boolean checkMessageHeader(SoapMessage message) {
		try {
			SOAPMessage mess = message.getContent(SOAPMessage.class);
			if (mess == null) {
				saaIn.handleFault(message);
				mess = message.getContent(SOAPMessage.class);
			}
			// 获得SOAPHeader
			SOAPHeader head = mess.getSOAPHeader();
			if (head == null)
				return false;

			// 获得这个名称的NodeList
			NodeList nodes = head.getElementsByTagName(localPart);
			if (nodes == null)
				return false;

			// 取出来判断是否相等
			String mytoken = "";
			for (int i = 0; i < nodes.getLength(); i++) {
				mytoken += nodes.item(i).getTextContent();
			}

			return mytoken.equals(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
