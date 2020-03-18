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
 * 
 * 排他网关
 * 
 *  排他拆分 排他合并
 * @author xupp
 *
 */
public class Excluserviceway {
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//先进行部署
		@Test 
		public void deploy() {
			RepositoryService repositoryService=processEngine.getRepositoryService();
			Deployment deploy= repositoryService.createDeployment().name("王权时代的请求拨款")
			.addClasspathResource("excluserviceway.bpmn")
			.addClasspathResource("excluserviceway.png")
			.deploy();
			System.out.println("部署成功：报销流程部署ID"+deploy.getId());
		}
		
		
		//启动实例
		@Test 
		public void startProcess() {
			RuntimeService runtimeService=processEngine.getRuntimeService();
			ProcessInstance processInstance =runtimeService.startProcessInstanceById("myProcess:1:4");
			System.out.println(processInstance.getId());
		} 
		
		//开始第一步的审批 在网关的时候进行审批
		
		@Test
		public void dealProcess1() {
			TaskService taskService=processEngine.getTaskService();
			List<Task> tasks= taskService.createTaskQuery().taskAssignee("7品官员").list();
			tasks.forEach(o->{
				taskService.setVariable(o.getId(), "count", "200000");
				taskService.complete(o.getId());
			});
		}
		
		@Test
		public void dealProcess2() {
			TaskService taskService=processEngine.getTaskService();
			List<Task> tasks= taskService.createTaskQuery().taskAssignee("侍郎").list();
			tasks.forEach(o->{
			
				taskService.complete(o.getId());
			});
		}
		
		@Test
		public void dealProcess3() {
			TaskService taskService=processEngine.getTaskService();
			List<Task> tasks= taskService.createTaskQuery().taskAssignee("财务大臣").list();
			tasks.forEach(o->{
			
				taskService.complete(o.getId());
			});
		}
		
	
		@Test
		public void dealProcess4() {
			TaskService taskService=processEngine.getTaskService();
			List<Task> tasks= taskService.createTaskQuery().taskAssignee("皇帝").list();
			tasks.forEach(o->{
			
				taskService.complete(o.getId());
			});
		}
		
	
	
}
	