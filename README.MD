
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

### 几个在线 Web Service 接口

[在线接口](/src/main/java/com/fancye/client/online/ClientOnlineInvoke.java)

### 常见问题

1. JAX 无法处理无参构造函数的类型作为接口方法的返回参数，例如下面的接口方法
```java
public List<Map<String, Object>> getListOfMap(String key, String value);
```
启动的时候，或者客户端访问的时候，会报如下异常：
```
Exception in thread "main" org.apache.cxf.binding.soap.SoapFault: 
	Marshalling Error: class java.util.HashMap以及其任何超类对此上下文都是未知的。
...
...

```
可以通过适配器的方式来解决，需要继承 `XmlAdapter` 类，参考[StringObjectMapAdapter](/src/main/javacom/fancye/client/adapter/StringObjectMapAdapter.java)

[其他资料](http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm)

