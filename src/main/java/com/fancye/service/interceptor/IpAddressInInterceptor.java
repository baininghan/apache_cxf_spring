/**
 * 
 */
package com.fancye.service.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

/**
 * @author Fancye
 * @2015-10-13
 *
 */
public class IpAddressInInterceptor extends AbstractPhaseInterceptor<Message> {

	//这个属性是注入进来的,你也可以从properties,xml文件中去读取,也可以从数据库中去获取;
	private List<String> ipList;
//	private final static Logger
	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}
	
	public IpAddressInInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message msg) throws Fault {
		//指定CXF获取客户端的HttpServletRequest : http-request
		HttpServletRequest request = (HttpServletRequest)msg.get(AbstractHTTPDestination.HTTP_REQUEST);
		String ipAddress="";
        boolean flag = false;
        if (null != request) {
            ipAddress = getUserIpAddr(request); // 取客户端IP地址
//            logger.info("请求客户端的IP地址:" + ipAddress);
            for (String s : ipList) {
                if (s.equals(ipAddress)) {
                    flag = true;
                    break;
                }
            }
        }
        if(!flag) {
            throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is stint"));
        }
	}
	
	 /**
     * 获取IP地址的方法
     * @param request
     * @return
     */
    private String getUserIpAddr(HttpServletRequest request) {
        //获取经过代理的客户端的IP地址; 排除了request.getRemoteAddr() 方法 在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了
//        String ip = CodesUtil.getIpAddr(request);
        String ip = request.getRemoteAddr();
        if (ip != null && ip.indexOf(",") > 0) {
//            logger.info("取到客户多个ip1====================" + ip);
            String[] arr = ip.split(",");
            ip = arr[arr.length - 1].trim();//有多个ip时取最后一个ip
//            logger.info("取到客户多个ip2====================" + ip);
        }
        return ip;
    }

}
