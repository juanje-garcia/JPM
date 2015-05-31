package com.jp.scheduler.algorithms;


import com.jp.scheduler.model.Gateway;
import com.jp.scheduler.model.impl.ScheduleTask;

public abstract class ScheduleAlgorithm {

	protected Gateway gateway;
	
	public ScheduleAlgorithm(Gateway gateway) {
		this.gateway=gateway;
	}

	public abstract int compare(ScheduleTask task1, ScheduleTask task2);
	
	

}
