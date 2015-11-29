package com.fancye.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;


/**
 * 
 * @author Fancye
 *
 */
@WebService
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHi(String name) {
		System.out.println("Hi, " + name);
	}
	
	@Override
	public void addPerson(Person person){
		System.out.println(person.toString());
	}
	
	@Override
	public List<Map<String, Object>> getListOfMap(String key, String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);// JAX不能处理接口作为一个返回参数，因此用 List<Object> 代替 List<Map>
		
		return list;
	}

}
