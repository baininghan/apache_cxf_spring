/**
 * 
 */
package com.fancye.restful;

import java.util.HashMap;
import java.util.Map;

import com.fancye.restful.po.BookVO;

/**
 * @author Fancye
 * @2016-1-23
 *
 */
public class Constants {

	public static Map<String, BookVO> bookMaps = new HashMap<String, BookVO>();
	private static Constants constants = new Constants();
	
	private Constants() {
		
	}
	
	public static synchronized Constants getInterence() {
		if (constants == null) {
			constants = new Constants();
		}
		return constants;
	}
	
	public void insertBook(BookVO bookVO) {
		if (!bookMaps.containsKey(bookVO.getBookName())) {
			bookMaps.put(bookVO.getBookName(), bookVO);
		}
	}
	
	public BookVO getBook(String bookName) {
		return bookMaps.get(bookName);
	}
}
