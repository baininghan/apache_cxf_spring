/**
 * 
 */
package com.fancye.restful.client;

import java.net.URLDecoder;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.fancye.restful.po.BookVO;

/**
 * @author Fancye
 * @2016-1-23
 *
 */
public class ClientInvoke {

	public static void main(String[] args) {
		ClientInvoke clientInvoke = new ClientInvoke();
		try {
			clientInvoke.addBook("Java", "fancye");
//			clientInvoke.getBook("Naked");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BookVO getBook(String bookName) throws Exception {
		String output = null;
		try {
			String url = "http://localhost:8080/apache_cxf_spring/service/bookService/getbook/";
			url = url + URLDecoder.decode(bookName, "UTF-8");
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);
			client.executeMethod(mPost);
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			mtHeader.setValue("application/xml");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			System.out.println("out : " + output);
		} catch (Exception e) {
			throw new Exception("Exception in retriving group page info : " + e);
		}
		return null;
	}
	
	public void addBook(String bookName, String author) throws Exception {
		String output = null;
		try {
			String url = "http://localhost:8080/apache_cxf_spring/service/bookService/addbook";
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(url);
			mPost.addParameter("name", bookName);
			mPost.addParameter("author", author);
			Header mtHeader = new Header();
			mtHeader.setName("content-type");
			mtHeader.setValue("application/x-www-form-urlencoded");
			mtHeader.setName("accept");
			mtHeader.setValue("application/xml");
			// mtHeader.setValue("application/json");
			mPost.addRequestHeader(mtHeader);
			client.executeMethod(mPost);
			output = mPost.getResponseBodyAsString();
			mPost.releaseConnection();
			System.out.println("output : " + output);
		} catch (Exception e) {
			throw new Exception("Exception in adding bucket : " + e);
		}
	}
}
