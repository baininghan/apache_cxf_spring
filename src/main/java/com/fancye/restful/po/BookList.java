/**
 * 
 */
package com.fancye.restful.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Fancye
 * @2016-1-23
 *
 */
@XmlRootElement(name="BookList")
public class BookList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BookVO> bookList;

	public List<BookVO> getBookList() {
		if (bookList == null) {
			bookList = new ArrayList<BookVO>();
		}
		return bookList;
	}

	public void setBookList(List<BookVO> bookList) {
		this.bookList = bookList;
	}
	
}
