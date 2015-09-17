
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
第一种解决方案：可以通过适配器的方式来解决，需要继承 `XmlAdapter` 类，参考[StringObjectMapAdapter](/src/main/javacom/fancye/client/adapter/StringObjectMapAdapter.java)，[其他资料](http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm)
第二种解决方案：`Map`改为`HashMap`

2. 使用动态客户端调用WSDL时，若出现以下错误：
```
Exception in thread "main" org.apache.cxf.service.factory.ServiceConstructionException : Failed to create service.
     at org.apache.cxf.wsdl11.WSDLServiceFactory.<init>(WSDLServiceFactory.java:80)
     at org.apache.cxf.endpoint.dynamic.DynamicClientFactory.createClient(DynamicClientFactory.java:292)
     at org.apache.cxf.endpoint.dynamic.DynamicClientFactory.createClient(DynamicClientFactory.java:235)
     at org.apache.cxf.endpoint.dynamic.DynamicClientFactory.createClient(DynamicClientFactory.java:228)
     at org.apache.cxf.endpoint.dynamic.DynamicClientFactory.createClient(DynamicClientFactory.java:183)
     at com.ws.client.ClientTest.main( ClientTest.java:28)
Caused by: javax.wsdl.WSDLException: WSDLException (at /soap:Envelope): faultCode=INVALID_WSDL: Expected element '{http://schemas.xmlsoap.org/wsdl/}definitions'.
     at com.ibm.wsdl.xml.WSDLReaderImpl.checkElementName(Unknown Source)
     at com.ibm.wsdl.xml.WSDLReaderImpl.parseDefinitions(Unknown Source)
     at com.ibm.wsdl.xml.WSDLReaderImpl.readWSDL(Unknown Source)
     at com.ibm.wsdl.xml.WSDLReaderImpl.readWSDL(Unknown Source)
     at org.apache.cxf.wsdl11.WSDLManagerImpl.loadDefinition(WSDLManagerImpl.java:247)
     at org.apache.cxf.wsdl11.WSDLManagerImpl.getDefinition(WSDLManagerImpl.java:192)
     at org.apache.cxf.wsdl11.WSDLServiceFactory.<init>(WSDLServiceFactory.java:78)
     ... 5 more
```
此处是因为你访问的WSDL地址后面忘记加`?wsdl`

