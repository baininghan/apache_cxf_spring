package com.fancye.service;

import javax.jws.WebService;

/**
 * 配置第二个Web Service 服务
 * @author Fancye
 *
 */
@WebService
public interface HelloDoubleService {

	public void sayDoubleHi(String name);
}
