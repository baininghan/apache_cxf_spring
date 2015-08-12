package com.fancye.service;

import javax.jws.WebService;

/**
 * WebService 接口类，暴露服务给外部系统使用
 * @author Fancye
 *
 */
@WebService
public interface HelloService {

	public void sayHi(String name);
}
