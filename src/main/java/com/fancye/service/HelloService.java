package com.fancye.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fancye.client.adapter.StringObjectMapAdapter;


/**
 * WebService 接口类，暴露服务给外部系统使用
 * @author Fancye
 *
 */
@WebService
public interface HelloService {

	public void sayHi(String name);
	
	public void addPerson(Person person);
	
	@XmlJavaTypeAdapter(StringObjectMapAdapter.class)
	public List<Map<String, Object>> getListOfMap(String key, String value);
}
