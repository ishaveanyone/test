package com.dist.gateway;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 并行网关
 * 
 * @author xupp
 *
 */
public class Parallelway {
		private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//先进行部署
		@Test 
		public void deploy() {
			RepositoryService repositoryService=processEngine.getRepositoryService();
			Deployment deploy= repositoryService.createDeployment().name("商店系统")
			.addClasspathResource("parallelway.bpmn")
			.addClasspathResource("parallelway.png")
			.deploy();
			System.out.println("部署成功：报销流程部署ID"+deploy.getId());
		}
		
		
		//启动实例
		@Test 
		public void startProcess() {
			RuntimeService runtimeService=processEngine.getRuntimeService();
			ProcessInstance processInstance =runtimeService.startProcessInstanceById("myProcess:2:42504");
			System.out.println(processInstance.getId());
		} 
	
		//启动实例
		@Test 
		public void startProcess1() {
			TaskService taskService=processEngine.getTaskService();
			
			Task task=	taskService.createTaskQuery().taskAssignee("买家").singleResult();
			taskService.complete(task.getId());
		
		} 
		
		//启动实例
			@Test 
			public void startProcess2() {
				TaskService taskService=processEngine.getTaskService();
				
				List<Task> tasks=	taskService.createTaskQuery().taskAssignee("卖家").list();
				tasks.stream().map(Task::getId).forEach(taskService::complete);
			
			} 
			
			//启动实例
			@Test 
			public void startProcess3() {
				TaskService taskService=processEngine.getTaskService();
				
				List<Task> tasks=	taskService.createTaskQuery().taskAssignee("买家").list();
				tasks.stream().map(Task::getId).forEach(taskService::complete);
			
			} 
			

}
