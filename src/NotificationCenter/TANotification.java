package NotificationCenter;

public class TANotification
{
	// Name describing the notification.
	private String name;
	// The object passed with the notification.
	private Object object;
		
	public TANotification(String name, Object object)
	{
		// The name of the notification cannot be null.
		if (name == null)
		{
			
		}
		
		this.name = name;
		this.object = object;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
	}

}
