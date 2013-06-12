/**
 * 
 */
package TestClasses;

import processing.core.*;
import NotificationCenter.TANotification;
import Zephyr.Zephyr;

/**
 * @author Andrew
 *
 */
public class TATestBox
{
	private PApplet applet;
	// Starting color.
	private int col;
	
	// Test notification.
	/**
	 * 
	 */
	public TATestBox(PApplet applet)
	{
		this.applet = applet;
		
		// set the starting color to blue.
		col = 0xff0000ff;
		
		// Subscribe to the notification Center.
		((Zephyr)applet).notificationCenter().addObserver(this, "testNotificationMethod", "testNotification");
	}
	
	public void display()
	{
		applet.pushMatrix();
		applet.translate(applet.width/2, applet.height/2);
		applet.rect(0, 0, 50, 50);
		applet.fill(col);
		applet.noStroke();
		applet.popMatrix();
	}
	
	public void testNotificationMethod(TANotification n)
	{
		col = 0xff00ff00;
		((Zephyr)applet).notificationCenter().removeObserver(this);
	}

}
