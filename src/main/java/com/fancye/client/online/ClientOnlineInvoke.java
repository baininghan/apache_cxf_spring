/**
 * 
 */
package com.fancye.client.online;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

/**
 * 几个在线 WebService 接口, 可供调测
 * @author Fancye
 * @2015-9-11
 *
 */
public class ClientOnlineInvoke {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * 输入参数：
		 * QQ号码 String，默认QQ号码：8698053。
		 * 返回数据：String，
		 * Y = 在线；N = 离线；E = QQ号码错误；A = 商业用户验证失败；V = 免费用户超过数量
		 */
//		String wsdl = "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl";
//		Client client = getClient(wsdl);
//		Object[] o = client.invoke("qqCheckOnline", "363310763");
//		System.out.println(o[0]);
		
		/**
		 * 输入参数：EmailAddress = Email 地址（默认SMTP端口25）
		 * 返回数据： Byte 字节
		 * 返回值： 
		 * 0 = 请重新验证；1 = 邮件地址合法；2 = 只是域名正确；
		 * 3 = 一个未知错误；4 = 邮件服务器没有找到；5 = 电子邮件地址错误；
		 * 6 = 免费用户验证超过数量（50次/24小时）；7 = 商业用户不能通过验证
		 */
//		String wsdl = "http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl";
//		Client client = getClient(wsdl);
//		Object[] o = client.invoke("ValidateEmailAddress", "363310763@qq.com");
//		System.out.println(o[0]);
		
		/**
		 * 输入参数：
		 * startCity = 出发城市（中文城市名称或缩写、空则默认：上海）；
		 * lastCity = 抵达城市（中文城市名称或缩写、空则默认：北京）；
		 * theDate = 出发日期（String 格式：yyyy-MM-dd，如：2007-07-02，空则默认当天）；
		 * userID = 商业用户ID（免费用户不需要）
		 * 
		 * 返回数据：DataSet，Table(0)结构为 Item(Company)航空公司、Item(AirlineCode)航班号、
		 * Item(StartDrome)出发机场、Item(ArriveDrome)到达机场、Item(StartTime)出发时间、
		 * Item(ArriveTime)到达时间、Item(Mode)机型、Item(AirlineStop)经停、Item(Week)飞行周期（星期）
		 */
//		String wsdl = "http://www.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl";
//		Client client = getClient(wsdl);
//		Object[] o = client.invoke("getDomesticAirlinesTime", "长沙", "西安", "2015-09-25");
//		System.out.println(o[0]);
//		client.invoke("getDomesticAirlinesTime");
		
//		Object[] o = client.invoke("getDomesticCity", "");
//		System.out.println(o[0]);
		
		/**
		 * 
		 */
//		String wsdl = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx?wsdl";
//		Client client = getClient(wsdl);
//		Object[] o = client.invoke("getEnCnTwoWayTranslator", "China");
//		System.out.println(o[0]);
		
		/**
		 * 手机号查询
		 * 
		 * 输入参数：
		 * mobileCode = 字符串（手机号码，最少前7位数字），
		 * userID = 字符串（商业用户ID） 免费用户为空字符串；
		 * 
		 * 返回数据：
		 * 字符串（手机号码：省份 城市 手机卡类型）。
		 */
//		String wsdl = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
//		Client client = getClient(wsdl);
//		Object[] o = client.invoke("getMobileCodeInfo", "1862759");
//		System.out.println(o[0]);
		
		/**
		 * 天气
		 */
		String wsdl = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
		Client client = getClient(wsdl);
		Object[] o = client.invoke("getWeather", "长沙", "");
		System.out.println(o[0]);
	}
	
	static Client getClient(String wsdl){
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		return factory.createClient(wsdl);
	}

}
