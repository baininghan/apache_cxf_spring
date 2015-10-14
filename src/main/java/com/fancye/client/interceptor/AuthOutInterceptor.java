/**
 * 
 */
package com.fancye.client.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.helpers.XPathUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Fancye
 * @2015-10-13
 * 
 */
public class AuthOutInterceptor extends AbstractSoapInterceptor {

	private AuthHeader authHeader = new AuthHeader();

	public AuthOutInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// 设定一个QName 这里的key就是localPart uri就是自定义的
		QName qname = new QName(authHeader.getqName(), authHeader.getKey());
		// 构造一个XML
		Document doc = DOMUtils.createDocument();

		// 创建给定的限定名称和名称空间 URI 的元素 这些操作都是jdk中的代码
		Element authElement = doc.createElementNS(authHeader.getqName(),authHeader.getKey());
		Element tokenElement = doc.createElement(authHeader.getToken());// 令牌
		tokenElement.setTextContent(authHeader.getToken()); // 设置值为
		authElement.appendChild(tokenElement);

//		XPathUtils.printDOM(authElement);
		// SOAP头 将有authElement元素组成
		SoapHeader header = new SoapHeader(qname, authElement);
		// 把我们构造的这个头 添加到message中去
		message.getHeaders().add(header);
	}

	public void setAuthHeader(AuthHeader authHeader) {
		this.authHeader = authHeader;
	}

}
