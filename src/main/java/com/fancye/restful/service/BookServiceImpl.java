/**
 * 
 */
package com.fancye.restful.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fancye.restful.Constants;
import com.fancye.restful.po.BookVO;

/**
 * @author Fancye
 * @2016-1-23
 * 
 */
@WebService
public class BookServiceImpl implements BookService {

	protected final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@POST
	@Path("/getbook/{name}")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	public void getBucket(@PathParam("name") String name) {
		log.debug("name : " + name);
		BookVO bookVO = null;
		Constants constants = Constants.getInterence();
		try {
			bookVO = constants.getBook(URLDecoder.decode(name, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("getBucket..");
//		if (bookVO == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(bookVO).build();
//		}
	}

	@POST
	@Path("/addbook")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	public void addBook(@FormParam("name") String bookName,
											@FormParam("author") String author) {
		log.debug("inside addBook");

		BookVO bookVO = new BookVO();
		bookVO.setBookName(bookName);
		bookVO.setAuthor(author);

		Constants constants = Constants.getInterence();
		constants.insertBook(bookVO);
		
		System.out.println("addBook : " + bookVO.getBookName());
//		if (constants.getBook(bookName) == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(bookVO).build();
//		}
	}
}
