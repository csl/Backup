package com.camera;

import java.util.*;

public class DateTask extends TimerTask 
{
	private int Timer_PID;
	protected MonitorCameraSocket[] newSocket = new MonitorCameraSocket[Main.CameraNum];

	private int port =12221;
	
	public DateTask(int ID)
	{
		Timer_PID = ID;
	}
	
    public void run() 
    {
    	System.out.println("running: " + Timer_PID);
    	
    	//hander: SyncTakePictrue
    	CameraGroup groupd = Main.camerGroupList.get(Timer_PID);
		int port = 12121;
		//CTakePicture = 0;
    	
    	//IP is unique for groupID
		for (int i=0; i<groupd.getIDSize(); i++)
		{
			int index = groupd.getID(i);
			
			newSocket[i] = new MonitorCameraSocket(index, Main.my);
			newSocket[i].SetAddressPort(Main.my.JTextF[index].getText() , port);
			newSocket[i].SetFunction(4);
			newSocket[i].SetCount(groupd.getTakePicNum());									
		}

		//Start Thread
		for (int i=0; i<groupd.getIDSize(); i++)
		{
			newSocket[i].start();
		}

		int count = 0;
		while (true)
		{
			//Alive or not
			if (newSocket[count].isAlive() == true)
			{
				count++;
			}
			
			if (groupd.getIDSize() == count) break;
		}
		
		//System.out.println(Integer.valueOf(CTakePicture));
		
		if (Main.my.CTakePicture == 1)
		{
			for (int i=0; i<groupd.getIDSize(); i++)
			{
				newSocket[i] = new MonitorCameraSocket(i, Main.my);
				newSocket[i].SetAddressPort(Main.my.JTextF[i].getText() , port);
				newSocket[i].SetFunction(4);
				newSocket[i].SetCount(groupd.getTakePicNum());									
			}									
			
			for (int i=0; i<groupd.getIDSize(); i++)
			{
				newSocket[i].start();
			}
			
		}
		else
		{
			groupd.incTakePicNum();
			System.out.println("CameraTakePictureNumber: " + groupd.getTakePicNum());
		}
    }
}