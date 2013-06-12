package Zephyr;

import NotificationCenter.TANotificationCenter;
import TestClasses.TATestBox;
import processing.core.*;


@SuppressWarnings("serial")
public class Zephyr extends PApplet
{	
	// Notification center for sending/receiving messages.
	private TANotificationCenter notificationCenter;
	// Test class.
	private TATestBox testBox;
	
	public static void main(String args[])
	{
		PApplet.main(new String[] {"Zephyr.Zephyr"});
	}
	
	public void setup()
	{
		size(displayWidth, displayHeight, P2D);
		
		notificationCenter = new TANotificationCenter();
		
		testBox = new TATestBox(this);
	}
	
	public void draw()
	{
		background(0xffff0000);
		
		testBox.display();
	}
	
	public TANotificationCenter notificationCenter()
	{
		return notificationCenter;
	}
	
	public void mouseClicked()
	{
		// post a notification letting us know the mouse was clicked.
		notificationCenter().postNotification("testNotification", this);
	}
	
	public void mousePushed()
	{
	}
	
	public void mouseReleased()
	{
	}
}
