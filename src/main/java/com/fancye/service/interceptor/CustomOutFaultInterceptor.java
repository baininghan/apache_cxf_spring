/**
 * 
 */
package com.fancye.service.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * @author Fancye
 * @2015-11-29
 *
 */
public class CustomOutFaultInterceptor extends AbstractPhaseInterceptor<Message> {

	public CustomOutFaultInterceptor(String phase) {
		super(phase);
	}

	public CustomOutFaultInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }
	
	@Override
	public void handleMessage(Message message) throws Fault {
		Fault f = (Fault)message.getContent(Exception.class);
		f.setMessage("aaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

}
