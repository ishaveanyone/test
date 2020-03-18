package com.dist.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ReciveTask {
	
private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	//先进行部署
	@Test 
	public void deploy() {
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("数据汇总并且通知")
		.addClasspathResource("recivetask.bpmn")
		.addClasspathResource("recivetask.png")
		.deploy();
		System.out.println("部署成功：报销流程部署ID"+deploy.getId());
	}
	
	
	
	@Test 
	public void startProcess() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
		
		
		ProcessInstance processInstance= runtimeService.startProcessInstanceById("数据汇总:1:4");
		
		
		System.out.println(processInstance.getName()+":"+processInstance.getId());
		
	}
	
	
	@Test 
	public void delProcess() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
//		Execution executionId= runtimeService.createExecutionQuery().processDefinitionId("数据汇总:1:60004").singleResult();
//		System.out.println("executionId:"+executionId.getId());
		runtimeService.trigger("2502"); // activiti5 的向下一步是 称位 signal
		
	
	}
	
	
	@Test 
	public void delProcess2() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
//		Execution executionId= runtimeService.createExecutionQuery().processDefinitionId("数据汇总:1:60004").singleResult();
//		System.out.println("executionId:"+executionId.getId());
		runtimeService.trigger("2502"); // activiti5 的向下一步是 称位 signal
		
	
	}

}
