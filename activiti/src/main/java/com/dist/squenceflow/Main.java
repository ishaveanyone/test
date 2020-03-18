package com.dist.squenceflow;

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


public class Main {
	
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	//先进行部署
	@Test 
	public void deploy() {
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("报销流程")
		.addClasspathResource("sequence_flow.bpmn")
		.addClasspathResource("sequence_flow.bpmn")
		.deploy();
		System.out.println("部署成功：报销流程部署ID"+deploy.getId());
	}
	
	
	//启动实例
	@Test 
	public void startProcess() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
		ProcessInstance processInstance =runtimeService.startProcessInstanceById("报销审批:2:2504");
		System.out.println(processInstance.getId());
	}
	
	//开始第一步的审批
	@Test
	public void dealProcess1() {
		TaskService taskService=processEngine.getTaskService();
		List<Task>  taList =taskService.createTaskQuery().taskAssignee("张三").list();
		
		taList.forEach(o->{
			taskService.complete(o.getId());
		});
		
	}
	
	@Test
	public void dealProcess2() {
		TaskService taskService=processEngine.getTaskService();
		List<Task>  taList =taskService.createTaskQuery().taskAssignee("李四").list();
	
		taList.forEach(o->{
			taskService.setVariable(o.getId(), "outcome", "重要");
			taskService.complete(o.getId());
		});
		System.out.println("成功");
	}
	
	
	@Test
	public void dealProcess3() {
		TaskService taskService=processEngine.getTaskService();
		List<Task>  taList =taskService.createTaskQuery().taskAssignee("王五").list();
	
		taList.forEach(o->{
//			taskService.setVariable(o.getId(), "outcome", "重要");
			taskService.complete(o.getId());
		});
		System.out.println("成功");
	}
	
	//
	

}
