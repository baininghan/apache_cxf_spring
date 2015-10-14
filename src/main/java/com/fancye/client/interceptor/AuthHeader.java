/**
 * 
 */
package com.fancye.client.interceptor;

import org.apache.cxf.common.util.StringUtils;

/**
 * @author Fancye
 * @2015-10-13
 *
 */
public class AuthHeader {
	
	private final static String QNAME = "http://test.com/auth";
	private String KEY = "MyAuthHeader";
	private String TOKEN = "Token";

	private String qName;
	private String key;
	private String token;
	private String content;

	public AuthHeader() {}

	public String getqName() {
		if (StringUtils.isEmpty(qName)) {
			qName = QNAME;
		}
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}

	public String getKey() {
		if (StringUtils.isEmpty(key)) {
			key = KEY;
		}
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToken() {
		if (StringUtils.isEmpty(token)) {
			token = TOKEN;
		}
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static String getQname() {
		return QNAME;
	}
	
}
