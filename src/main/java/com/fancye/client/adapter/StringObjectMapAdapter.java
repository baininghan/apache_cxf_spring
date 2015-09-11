/**
 * 
 */
package com.fancye.client.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 自定义一个适配器，用于解决 JAX 不能使用 Map 接口作为返回参数
 * @author Fancye
 * @2015-9-11
 *
 */
public class StringObjectMapAdapter extends XmlAdapter<StringObjectMap, Map<String, Object>> {

	/**
	 * XML => Java
	 * 根据我们自定义的适配对象，返回我们实际需要的Java类型对象
	 */
	@Override
	public Map<String, Object> unmarshal(StringObjectMap v) throws Exception {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (StringObjectMap.StringObjectEntry e : v.getEntries()) {
            map.put(e.getKey(), e.getObj());
        }
        
        return map;
	}

	/**
	 * Java => XML
	 * 根据给出的Java类型对象，返回一个我们自定义的适配对象
	 */
	@Override
	public StringObjectMap marshal(Map<String, Object> v) throws Exception {
		StringObjectMap map = new StringObjectMap();
		for(Map.Entry<String, Object> e : v.entrySet()) {
			StringObjectMap.StringObjectEntry soe = new StringObjectMap.StringObjectEntry();
			soe.setKey(e.getKey());
            soe.setObj(e.getValue());
            map.getEntries().add(soe);
		}
		
		return map;
	}

}
