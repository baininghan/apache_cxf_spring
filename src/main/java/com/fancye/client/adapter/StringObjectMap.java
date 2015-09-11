/**
 * 
 */
package com.fancye.client.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 自定义一个适配器类，用于解决 JAX 不能识别 Map 类型
 * @author Fancye
 * @2015-9-11
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class StringObjectMap {

	@XmlElement(nillable = false, name = "entry")
	List<StringObjectEntry> entries = new ArrayList<StringObjectEntry>();
	
	public List<StringObjectEntry> getEntries() {
        return entries;
    }
	
	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType
    public static class StringObjectEntry {
		
		//Map keys cannot be null
        @XmlElement(required = true, nillable = false)
        String key;
        Object obj;
        
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public Object getObj() {
			return obj;
		}
		public void setObj(Object obj) {
			this.obj = obj;
		}
        
	}
}
