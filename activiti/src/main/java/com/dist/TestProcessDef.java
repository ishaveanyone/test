package com.dist;
//管理流程定义

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

public class TestProcessDef {
	
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	//可以当都创建流程部署
	@Test
	public void deployProcess0() {
		
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("请假流程")
		.deploy();
		System.out.println("部署成功：流程部署ID"+deploy.getId());
		
	}
	
	
	

	
	//创建流程部署 并且同时创建 流程定义
	@Test
	public void deployProcess1() {
		
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("请假流程")
		.addClasspathResource("test.bpmn")
		.addClasspathResource("test.png")
		.deploy();
		System.out.println("部署成功：流程部署ID"+deploy.getId());
		
	}
	
	//使用 zip 进行部署 
	// 流程图文件必须是zip文件结尾 
	/**
	 * 
	 * 
	 * 重复执行部署 5 和6 有区别 对于数据生成
	 * 
	 * 
	 */
	@Test
	public void deployProcess2() {
		//不加/ 代表从 当前包 中查找文件  如果 加了  / 那么代表 从 class 目录下面 查找文件 
		InputStream inputStream= this.getClass().getResourceAsStream("/test.zip");
		ZipInputStream zipInputStream=new ZipInputStream(inputStream);
		RepositoryService repositoryService=processEngine.getRepositoryService();
		Deployment deploy= repositoryService.createDeployment().name("请假流程")
		.addZipInputStream(zipInputStream)
		.deploy();
		System.out.println("部署成功：流程部署ID"+deploy.getId());
		
	}
	

	/**
	 * 查询流程的部署信息 act_re_deployment
	 */
	@Test
	public void deployProcess3() {
		//不加/ 代表从 当前包 中查找文件  如果 加了  / 那么代表 从 class 目录下面 查找文件 
		RepositoryService repositoryService=processEngine.getRepositoryService();
		
		Deployment deployment =repositoryService.createDeploymentQuery()
		//条件
		.deploymentId("1")
		.singleResult();
		System.out.println(deployment.getName());
	}

	
	
	/**
	 * 查询流程定义的数据信息 act_re_procdef
	 */ 
	@Test
	public void deployProcess4() {
		//不加/ 代表从 当前包 中查找文件  如果 加了  / 那么代表 从 class 目录下面 查找文件 
		RepositoryService repositoryService=processEngine.getRepositoryService();
		
		ProcessDefinition processDefinition= repositoryService.createProcessDefinitionQuery()
		//条件
		.deploymentId("1")
		.singleResult();
		System.out.println(processDefinition.getName());
	}
	
	/**
	 * 查询流程定义的数据信息更新 act_re_procdef
	 */ 
	@Test
	public void deployProcess5() {
		//不加/ 代表从 当前包 中查找文件  如果 加了  / 那么代表 从 class 目录下面 查找文件 
		RepositoryService repositoryService=processEngine.getRepositoryService();
		
		repositoryService.deleteDeployment("5001");
//		System.out.println(processDefinition.getName());
	}
	
	
	//获取一个流程实例
	
	@Test
	public void deployProcess6() {
		//不加/ 代表从 当前包 中查找文件  如果 加了  / 那么代表 从 class 目录下面 查找文件 
		RepositoryService repositoryService=processEngine.getRepositoryService();
		
		repositoryService.deleteDeployment("5001");
//		System.out.println(processDefinition.getName());
	}
	
	
	
}
