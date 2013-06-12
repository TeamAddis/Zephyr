package NotificationCenter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TANotificationCenter
{
	// Use this Observer class to store a pointer to the
	// instance of the object, the message it cares about,
	// and the method that will be called when message is
	// received.
	class Observer 
	{
		private Object object;
		private String methodName;
		private String messageName;
		
		public Observer(Object object, String methodName, String messageName)
		{
			this.object = object;
			this.methodName = methodName;
			this.messageName = messageName;
		}

		public Object getObject()
		{
			return object;
		}

		public void setObject(Object object)
		{
			this.object = object;
		}

		public String getMethodName()
		{
			return methodName;
		}

		public void setMethodName(String methodName)
		{
			this.methodName = methodName;
		}

		public String getMessageName()
		{
			return messageName;
		}

		public void setMessageName(String messageName)
		{
			this.messageName = messageName;
		}
	};
	
	// Keep track of messages our observers want to listen to.
	private ArrayList<Observer> observers;
	// All instances of TANotification are stored in TANotificationQueue.
	private TANotificationQueue notificationQueue;
	// Worker thread that deals with TANotifications.
	private Thread workerThread;
	
	// Create the notification center.
	public TANotificationCenter()
	{
		observers = new ArrayList<Observer>();
		notificationQueue = new TANotificationQueue(this);
		
		// Create a new thread to handle checking and sending notifications.
		workerThread = new Thread()
		{
			public void run()
			{
				deliverMessages();
			}
		};
		workerThread.start();
	}
	
	// Post a notification to the notification center.
	public void postNotification(String name, Object object)
	{
		System.out.println("Posting notification.");
		
		TANotification notification = new TANotification(name, object);
		notificationQueue.add(notification);
	}

	// Subscribe as an observer to the notification center.
	public void addObserver(Object notificationObserver, String notificationMethod,
			String notificationName)
	{
		System.out.println("Adding new observer.");
		
		Observer observer = new Observer(notificationObserver, notificationMethod, notificationName);
		observers.add(observer);
	}
	
	// Unsubscribe as an observer to the notification center.
	public void removeObserver(Object notificationObserver)
	{
		System.out.println("Removing observer.");
		
		observers.remove(observers.indexOf(notificationObserver));
	}
	
	// Deliver all the messages to our observers.
	// This function runs on a separate thread.
	private synchronized void deliverMessages()
	{
		for ( ;; )
		{
			TANotification notification = notificationQueue.getHead();
			
			// If the notification queue's head isn't null,
			// we have a notification to deliver.
			if (notification != null)
			{
				System.out.println("New notification to deliver!");
				
				for (Observer observer : observers)
				{
					if (observer.getMessageName() == notification.getName())
					{
						System.out.println("Found observer listening for this notification.");
						
						invokeMethod(observer.getObject(), observer.getMethodName(), notification);
					}
				}
			}
			else
			{
				// The queue's head is null.
				//System.out.println(notificationQueue.getHead());
			}
			//System.out.println(notificationQueue.count());
		}
	}
	
	// Invoke a method on an object.
	private synchronized void invokeMethod(Object object, String methodName, TANotification notification)
	{
		Method method;
		
		try
		{
			//System.out.println(object.getClass());
			
			method = object.getClass().getMethod(methodName, notification.getClass());
			
			try
			{
				method.invoke(object, notification);
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("IllegalArgumentException");
			}
			catch (IllegalAccessException e)
			{
				System.out.println("IllegalAccessException");
			}
			catch (InvocationTargetException e)
			{
				System.out.println("InvocationTargetException");
			}
		}
		catch (NoSuchMethodException e)
		{
			System.out.println("NoSuchMethodException");
		}
	}
}
