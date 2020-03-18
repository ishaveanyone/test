package com.dist.grouptask;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.IdentityLink;
import org.junit.Test;
//组用户 可以和 指派人一样 使用 占位符表达式 
public class Main {
	
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//先进行部署
	@Test 
	public void deploy() {
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("组任务的实现")
		.addClasspathResource("grouptask.bpmn")
		.addClasspathResource("grouptask.png")
		.deploy();
		System.out.println("部署成功：报销流程部署ID"+deploy.getId());
	}
	
	@Test
	public void startprocess() {
		RuntimeService runsRuntimeService=processEngine.getRuntimeService();
		runsRuntimeService.startProcessInstanceById("组任务:1:4");
	}
	
	@Test
	public void delProcess() {
		TaskService taskService=processEngine.getTaskService();
		taskService.unclaim("5005");// 回收
		//指定派发某一个用户 
		taskService.claim("5005", "A");
	}

	
	//查询用户 
	@Test
	public void findGUsers() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
		
		List<IdentityLink> identityLinks =runtimeService.getIdentityLinksForProcessInstance("5001");
		for(IdentityLink identityLink:identityLinks) {
			
			System.out.println("type:"+identityLink.getType());
			System.out.println("name:"+identityLink.getUserId());
		}
	}
	
	
	
}
