package com.camera;

import java.util.*;

public class DateTask extends TimerTask 
{
	private int Timer_PID;
	protected MonitorCameraSocket[] newSocket = new MonitorCameraSocket[Main.CameraNum];

	private int port =12221;
	
	DateTask(int ID)
	{
		Timer_PID = ID;
	}
	
    public void run() 
    {
    	//hander: SyncTakePictrue
    	CameraGroup groupd = Main.camerGroupList.get(Timer_PID);
    	
		for (int i=0; i<groupd.getIDSize(); i++)
		{
			int index = groupd.getID(i);
			//newSocket[index] = new MonitorCameraSocket();
			newSocket[index].SetAddressPort("192.168.123.254" , port + i);
			newSocket[index].SetFunction(1);
		}

		//Start
		for (int i=0; i<groupd.getIDSize(); i++)
		{
			//newSocket[index].start();
		}
    	
    }
}