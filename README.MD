
使用[Apache CXF](http://cxf.apache.org/) 实现 Web Service 的一个简单例子

### 开发环境

```
 Apache CXF : 3.1.2
 Spring : 4.2.0.RELEASE
 Tomcat : apache-tomcat-7.0.64
 
 Java7
 MyEclipse10
 Win10
```

### 常见问题

#### **JAX 无法处理无参构造函数的类型作为接口方法的返回参数**
```java
// Exception in thread "main" org.apache.cxf.binding.soap.SoapFault: 
// Marshalling Error: class java.util.HashMap以及其任何超类对此上下文都是未知的。
public List<Map<String, Object>> getListOfMap(String key, String value);
```
可以通过适配器的方式来解决，需要继承 `XmlAdapter` 类，参考[StringObjectMapAdapter](/src/main/javacom/fancye/client/adapter/StringObjectMapAdapter.java)

[其他资料](http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm)

