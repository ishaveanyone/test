package com.dist.listence;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskListencer implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		
		//模拟业务从全局类上面获取 用户 
		// TODO Auto-generated method stub
		delegateTask.setAssignee(UserUtil.username);
		System.out.println("进入监听");
		
	}

}
