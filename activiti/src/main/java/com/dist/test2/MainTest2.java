package com.dist.test2;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.h2.util.New;
import org.junit.Test;

public class MainTest2 {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	//可以当都创建流程部署
	@Test
	public void deployProcess0() {
		
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("请假流程")
		.addClasspathResource("test.bpmn")
		.addClasspathResource("test.png")
		.deploy();
		System.out.println("部署成功：流程部署ID"+deploy.getId());
		
	}
	
	
	@Test
	public void startDeployProcess0() {
		RuntimeService runtimeService =processEngine.getRuntimeService();
		Map<String, Object> variables =new HashMap<String, Object>();
		variables.put("请假原因", 2);
		variables.put("请假时间", LocalDate.now());
		variables.put("请假人", new User("张三"));
		
//		runtimeService.set
		ProcessInstance processInstance= runtimeService.startProcessInstanceByKey("test", variables);
	}
	
	
	//获取流程变量 
	@Test
	public void getVariables() {
		RuntimeService runtimeService =processEngine.getRuntimeService();
		System.out.println(runtimeService.getVariable("10009", "请假人"));;
	
	}
	
}
