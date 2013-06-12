/**
 * 
 */
package NotificationCenter;

import java.util.ArrayDeque;

/**
 * @author Andrew
 *
 */
public class TANotificationQueue
{
	// The notification center to which this queue belongs.
	TANotificationCenter notificationCenter;
	ArrayDeque<TANotification> queue;

	/**
	 * 
	 */
	public TANotificationQueue(TANotificationCenter center)
	{
		notificationCenter = center;
		queue = new ArrayDeque<TANotification>();
	}

	public synchronized void add(TANotification n)
	{
		System.out.println("Adding notification to notification queue.");
		
		queue.add(n);
	}
	
	public synchronized TANotification getHead()
	{
		// System.out.println("Removing head from notification queue.");
		
		return queue.poll();
	}
	
	public synchronized int count()
	{
		return queue.size();
	}
}
