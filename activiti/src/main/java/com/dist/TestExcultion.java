package com.dist;
/**
 * 测试使用 流程实例
 * @author xupp
 *
 */

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class TestExcultion {
	
	
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public  void getInstance() {
		// TODO Auto-generated method stub
		ProcessInstance processInstance	=
				
				processEngine.getRuntimeService().startProcessInstanceByKey("test");
		
		
		
		
	}
	

}
