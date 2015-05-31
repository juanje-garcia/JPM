package com.jp.scheduler.constants;

public interface Constants {

	//processing time in seconds for external resources to be executed
	public final int processingDuration=2;
	//Number of message groups
	public final int maxGroups=3;
	//Maximum number of minutes to keep in the queue before discard
	public int minutesQueued=5;
	//Queue max size
	public final int queueMaxSize = 1000;
	
	public final String Algorithm_Alternative = "com.jp.scheduler.algorithms.AlternativeAlgorithm";
	public final String Algorithm_Default = "com.jp.scheduler.algorithms.DefaultAlgorithm";
	
}
