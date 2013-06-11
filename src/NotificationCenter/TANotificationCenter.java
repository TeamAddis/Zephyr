package NotificationCenter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TANotificationCenter
{
	// Keep track of our observers.
	private ArrayList<Object> observers;
	
	public TANotificationCenter()
	{
		observers = new ArrayList<Object>();
	}
	
	public void postNotification(String name, Object object)
	{
	}

	public void addObserver(Object observer, String method, String name, Object object)
	{
	}
	
	private void invokeMethod(Object object, String name)
	{
		Method method;
		
		try
		{
			method = object.getClass().getMethod(name);
			
			try
			{
				method.invoke(object);
			}
			catch (IllegalArgumentException e)
			{}
			catch (IllegalAccessException e)
			{}
			catch (InvocationTargetException e)
			{}
		}
		catch (NoSuchMethodException e)
		{}
	}
}
