package com.dist;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

/**
 * 初始化表
 * @author xupp
 *
 */
public class InitTable {
	
	@Test
	public void initTable1() {
		ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("org.postgresql.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/activiti?currentSchema=public");
		processEngineConfiguration.setJdbcUsername("xupp");
		processEngineConfiguration.setJdbcPassword("pass");
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		//	得到流程引擎
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine);
	}
	
	
	@Test
	public void initTable2() {
		ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		//	得到流程引擎 只有在获取流程引擎之后才会去创建表
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
		System.out.println(1111);
		System.out.println(processEngine);
	}
	
	
	//是最常用的 一种方式 
	
	@Test
	public void initTable3() {
		//默认使用 就是 activiti.cfg.xml 这个文件 
		ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
//		RepositoryService repositoryService=processEngine.getRepositoryService();
//		repositoryService.createDeployment().addClasspathResource("activiti.config.xml").deploy();
		System.out.println(processEngine);
		/////////////对应初始化的各个表操作
		//流程图的
		processEngine.getRepositoryService();
		//流程运行
		processEngine.getTaskService();
		processEngine.getRuntimeService();
		
		//历史
		processEngine.getHistoryService();
		//页面表单
		processEngine.getFormService();
		
		//用户管理
		processEngine.getIdentityService();
		
		//管理器
		
		processEngine.getManagementService();
	}

}
