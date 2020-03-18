package com.dist.pauseresume;
/**
 * 流程的暂停 继续
 * @author xupp
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.StartProcessInstanceByMessageCmd;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class Main {
	
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deploy() {
		Deployment deployment =
				processEngine.getRepositoryService().createDeployment().name("暂停继续").
				addClasspathResource("pauseresume.bpmn").
				deploy();
		System.out.println("id:"+deployment.getId());
		System.out.println("name:"+deployment.getName());
	}
	
	@Test
	public void startProcess() {
		Map< String, Object> vMap=new HashMap<String, Object>();
		
		vMap.put("assignee","张三");
		ProcessInstance processInstance =processEngine.getRuntimeService().startProcessInstanceById("暂停继续:3:5004",vMap);
		processEngine.getRuntimeService().suspendProcessInstanceById(processInstance.getId());//暂停
		System.out.println("流程启动成功");
	}
	
	@Test
	public void startProcess2() {
		Map< String, Object> vMap=new HashMap<String, Object>();
		processEngine.getRuntimeService().activateProcessInstanceById("7501"); //继续 如果不继续 会抛出异常
		TaskService taskService=processEngine.getTaskService();
		taskService.complete("7506");
	}
	
	
	
	
}
