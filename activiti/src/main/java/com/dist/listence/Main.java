package com.dist.listence;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class Main {
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//先进行部署
	@Test 
	public void deploy() {
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("使用监听器")
		.addClasspathResource("listence.bpmn")
		.addClasspathResource("listence.png")
		.deploy();
		System.out.println("部署成功：报销流程部署ID"+deploy.getId());
	}
	
	@Test 
	public void startProcess() {
//		RepositoryService repositoryService=processEngine.getRepositoryService();
		RuntimeService runtimeService=processEngine.getRuntimeService();
		Map<String, Object> variables=new HashMap<String, Object>();
		variables.put("username","张三");
		runtimeService.startProcessInstanceById("监听使用:1:20004", variables);
	
	}
	
	@Test 
	public void delProcess1() {
		TaskService runtimeService=processEngine.getTaskService();
		UserUtil.username="李四";
//		Map<String, Object> variables=new HashMap<String, Object>();
//		variables.put("username","张三");
		runtimeService.complete("22506");
	}
	
	@Test 
	public void delProcess2() {
		TaskService runtimeService=processEngine.getTaskService();
		UserUtil.username="王五";
//		Map<String, Object> variables=new HashMap<String, Object>();
//		variables.put("username","张三");
		runtimeService.complete("25002");
	}
}
