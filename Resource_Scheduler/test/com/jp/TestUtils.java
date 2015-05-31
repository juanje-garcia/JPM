package com.jp;

import com.jp.scheduler.model.Message;
import com.jp.scheduler.model.impl.MessageImpl;
import com.jp.scheduler.model.impl.Scheduler;

public class TestUtils {


	public static Message[] getTenMessages(){
		
		Message msg1=new MessageImpl("Message 1",1);
		Message msg2=new MessageImpl("Message 2",1);
		Message msg3=new MessageImpl("Message 3",1);
		Message msg4=new MessageImpl("Message 4",2);
		Message msg5=new MessageImpl("Message 5",2);
		Message msg6=new MessageImpl("Message 6",3);
		Message msg7=new MessageImpl("Message 7",3);
		Message msg8=new MessageImpl("Message 8",2);
		Message msg9=new MessageImpl("Message 9",2);
		Message msg10=new MessageImpl("Message 10",1);
				
		Message[] messages=new Message[]{msg1,msg2,msg3,msg4,msg5,msg6,msg7,msg8,msg9,msg10};
		return messages;
	}
	
	/**
	 * Message 1(1), Message 2(1), Message 3(1), Message 10(1), Message 4(2), Message 5(2), Message 8(2), Message 9(2), Message 6(3), Message 7(3)
	 * @return
	 */
	public static Message[] getTestDefaultAlgorithmExpectedArray(){
		
		Message msg1=new MessageImpl("Message 1",1);
		Message msg2=new MessageImpl("Message 2",1);
		Message msg3=new MessageImpl("Message 3",1);
		Message msg4=new MessageImpl("Message 4",2);
		Message msg5=new MessageImpl("Message 5",2);
		Message msg6=new MessageImpl("Message 6",3);
		Message msg7=new MessageImpl("Message 7",3);
		Message msg8=new MessageImpl("Message 8",2);
		Message msg9=new MessageImpl("Message 9",2);
		Message msg10=new MessageImpl("Message 10",1);
				
		
		Message[] messages=new Message[]{msg1,msg2,msg3,msg10,msg4,msg5,msg8,msg9,msg6,msg7};
		return messages;
	}
	
	
	/**
	 * Message 1(1), Message 6(2), Message 7(3), Message 8(4), Message 9(5), Message 10(6), Message 2(1), Message 3(1), Message 4(1), Message 5(1)
	 * @return
	 */
	public static Message[] getTestAlternativeAlgorithmExpectedArray(){
		
		Message msg1=new MessageImpl("Message 1",1);
		Message msg2=new MessageImpl("Message 2",1);
		Message msg3=new MessageImpl("Message 3",1);
		Message msg4=new MessageImpl("Message 4",1);
		Message msg5=new MessageImpl("Message 5",1);
		Message msg6=new MessageImpl("Message 6",2);
		Message msg7=new MessageImpl("Message 7",3);
		Message msg8=new MessageImpl("Message 8",4);
		Message msg9=new MessageImpl("Message 9",5);
		Message msg10=new MessageImpl("Message 10",6);
				
		
		Message[] messages=new Message[]{msg1,msg6,msg7,msg8,msg9,msg10,msg2,msg3,msg4,msg5};
		return messages;
	}
	
	
	public static Message[] getTenMessagesAlternative(){
		
		Message msg1=new MessageImpl("Message 1",1);
		Message msg2=new MessageImpl("Message 2",1);
		Message msg3=new MessageImpl("Message 3",1);
		Message msg4=new MessageImpl("Message 4",1);
		Message msg5=new MessageImpl("Message 5",1);
		Message msg6=new MessageImpl("Message 6",2);
		Message msg7=new MessageImpl("Message 7",3);
		Message msg8=new MessageImpl("Message 8",4);
		Message msg9=new MessageImpl("Message 9",5);
		Message msg10=new MessageImpl("Message 10",6);
				
		Message[] messages=new Message[]{msg1,msg2,msg3,msg4,msg5,msg6,msg7,msg8,msg9,msg10};
		return messages;
	}
	
	public static Message[] get_N_Messages(int n){
		Message[] messages=new Message[n];
		for (int i = 0; i < n; i++) {
			Message msg=new MessageImpl("Message " + i,1);
			messages[i]=msg;
		}
		return messages;
	}
	
	public static void executeScheduler(Scheduler scheduler,Message[] messages){
		
		
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			scheduler.process(message);
			try {
				//There is 100 milliseconds between message being received
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Wait while all messages are processed
		waitForCompletion(scheduler, messages);
		
		System.out.println("MessagesCompleted order:" + scheduler.getMessagesCompleted());
		System.out.println("MessagesSent order:"+scheduler.getMessagesSent());	
		
		
	}
	
	public static void executeSchedulerWithCancellation(Scheduler scheduler,Message[] messages,Integer groupCancelled){
		
		
		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			scheduler.process(message);
			try {
				//There is 500 milliseconds between message being received
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		scheduler.cancelGroup(groupCancelled);
		
		waitForCompletion(scheduler, messages);
		
		
		
	}
	
	
	public static void waitForCompletion(Scheduler scheduler, Message[] messages){
		//Wait while all messages are processed
		while (scheduler.getCompletedCount()<messages.length){
			
		}
	}
	
}
