package com.dist.dynamicparam;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * 
 * 实际的使用中肯定 不是 都是写死的很多 都是依靠变量传递 
 * @author xupp
 *
 */
public class Main {
	
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//先进行部署
		@Test 
		public void deploy() {
			RepositoryService repositoryService=processEngine.getRepositoryService();
			Deployment deploy= repositoryService.createDeployment().name("动态设置请求参数 ")
			.addClasspathResource("dynamicparam.bpmn")
			.addClasspathResource("dynamicparam.png")
			.deploy();
			System.out.println("部署成功：报销流程部署ID"+deploy.getId());
		}
		@Test 
		public void startprocess() {
			RuntimeService runtimeService=processEngine.getRuntimeService();
			Map<String, Object> variables =new HashMap<String, Object>();
			
			variables.put("username", "张三");
			
			runtimeService.startProcessInstanceById("动态参数:1:10004", variables);
		}
		
		
		@Test 
		public void startprocess2() {
			TaskService  taskService=processEngine.getTaskService();
			Map<String, Object> variables =new HashMap<String, Object>();
			
			variables.put("username", "李四");
			
			taskService.complete("12506", variables);
		}
		
		@Test 
		public void startprocess3() {
			TaskService  taskService=processEngine.getTaskService();
//			Map<String, Object> variables =new HashMap<String, Object>();
		
			taskService.complete("15002");
		}
}
