package com.dist;

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

public class TestActiviti {
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deployProcess() {
		
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("请假流程")
		.addClasspathResource("test.bpmn")
		.addClasspathResource("test.png")
		.deploy();
		System.out.println("部署成功：流程部署ID"+deploy.getId());
		
	}
	
	@Test
	public void startProcess() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
		
//		ProcessInstance processInstance=	runtimeService.startProcessInstanceById("test:1:4");
		//这块的 excution 生成的 数据条数和5 和 6版本不一样 
		ProcessInstance processInstance=	runtimeService.startProcessInstanceByKey("test");
		System.out.println("流程启动成功"+processInstance.getId());
		
	}
	
	//查询任务
	@Test
	public void findTask() {
		TaskService taskService=processEngine.getTaskService();
		List<Task> tasks= taskService.createTaskQuery().taskAssignee("张三").list();
		for(Task task:tasks) {
			System.out.println(task.getId());
		}
		
		
		
	}
	
	
	//办理任务
	@Test
	public void finshTask() {
		RuntimeService runtimeService=processEngine.getRuntimeService();
		TaskService taskService=processEngine.getTaskService();//上面两个都可以执行完成
		List<Task> tasks= taskService.createTaskQuery().taskAssignee("王五").list();
		for(Task task:tasks) {
			System.out.println(task.getId());
		
			taskService.complete(task.getId());
		}
		
		
		
	}
	
	

}
