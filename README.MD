
使用[Apache CXF](http://cxf.apache.org/) 实现 Web Service 的一个简单例子

#### 开发环境

```
 Apache CXF : 3.1.2
 Spring : 4.2.0.RELEASE
 Tomcat : apache-tomcat-7.0.64
 
 Java7
 MyEclipse10
 Win10
```

#### 几个在线 Web Service 接口

[在线接口](/src/main/java/com/fancye/client/online/ClientOnlineInvoke.java)

#### 常见问题

##### JAX 无法处理无参构造函数的类型作为接口方法的返回参数，例如下面的接口方法
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
- 第一种解决方案：可以通过适配器的方式来解决，需要继承 `XmlAdapter` 类，参考[StringObjectMapAdapter](/src/main/javacom/fancye/client/adapter/StringObjectMapAdapter.java)，[其他资料](http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm)
- 第二种解决方案：`Map`改为`HashMap`

##### 使用动态客户端调用WSDL时，若出现以下错误：
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

##### 使用其他客户端工具（例如soapUI）或者其他语言（例如C）,调用报错
```
[WARN ] 2015-09-18 13:56:20,697 - Interceptor for {http://server.ws.computech.com/}EcifServiceService has thrown exception, unwinding now
org.apache.cxf.binding.soap.SoapFault: Error reading XMLStreamReader.
	at org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor.handleMessage(ReadHeadersInterceptor.java:266)
	at org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor.handleMessage(ReadHeadersInterceptor.java:66)
	at org.apache.cxf.phase.PhaseInterceptorChain.doIntercept(PhaseInterceptorChain.java:272)
	at org.apache.cxf.transport.ChainInitiationObserver.onMessage(ChainInitiationObserver.java:121)
	at org.apache.cxf.transport.http.AbstractHTTPDestination.invoke(AbstractHTTPDestination.java:249)
	at org.apache.cxf.transport.servlet.ServletController.invokeDestination(ServletController.java:248)
	at org.apache.cxf.transport.servlet.ServletController.invoke(ServletController.java:222)
	at org.apache.cxf.transport.servlet.ServletController.invoke(ServletController.java:153)
	at org.apache.cxf.transport.servlet.CXFNonSpringServlet.invoke(CXFNonSpringServlet.java:171)
	at org.apache.cxf.transport.servlet.AbstractHTTPServlet.handleRequest(AbstractHTTPServlet.java:289)
	at org.apache.cxf.transport.servlet.AbstractHTTPServlet.doPost(AbstractHTTPServlet.java:209)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:637)
	at org.apache.cxf.transport.servlet.AbstractHTTPServlet.service(AbstractHTTPServlet.java:265)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)
	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)
	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)
	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)
	at java.lang.Thread.run(Thread.java:722)
Caused by: com.ctc.wstx.exc.WstxEOFException: Unexpected EOF in prolog
 at [row,col {unknown-source}]: [1,0]
	at com.ctc.wstx.sr.StreamScanner.throwUnexpectedEOF(StreamScanner.java:685)
	at com.ctc.wstx.sr.BasicStreamReader.handleEOF(BasicStreamReader.java:2141)
	at com.ctc.wstx.sr.BasicStreamReader.nextFromProlog(BasicStreamReader.java:2047)
	at com.ctc.wstx.sr.BasicStreamReader.next(BasicStreamReader.java:1131)
	at com.ctc.wstx.sr.BasicStreamReader.nextTag(BasicStreamReader.java:1154)
	at org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor.handleMessage(ReadHeadersInterceptor.java:165)
	... 24 more
```
此处主要看两个错误点：`Error reading XMLStreamReader.` 和 ` at [row,col {unknown-source}]: [1,0]`

第一个可能原因是JAW的版本太低，大多情况出现于使用JBOSS容器时发生，换一个版本高一点的就可以解决。
第二个原因是解析XML格式出错。`soapUI`工具中可以使用`<![CDATA[***]]>`将请求xml格式的报文包裹在里面即可解决。
