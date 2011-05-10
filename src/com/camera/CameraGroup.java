package com.camera;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class CameraGroup
{
		static int MAX = 255;
		private int TimeSec;
		private int [] ID;
		private int ID_Size;
		private int left_index;
		private int right_index;
		private int TakePicNum;
		private boolean HasData;
	
        public CameraGroup(int s) 
        {
        	TimeSec = s;
        	ID = new int[MAX];
        	ID_Size = 0;
        	left_index = 0;
        	right_index = 0;
        	TakePicNum = 0;
        	HasData = false;
        }
        
        public int getTimeSec()
        {
        	return TimeSec;
        }
        
        public int getID(int idc)
        {
        	if (idc > ID_Size) return -1;
        	return ID[idc];
        }
        public void addID(int cid)
        {
        	ID[ID_Size] = cid;
        	ID_Size++;
        }
        public int getIDSize()
        {
        	return ID_Size;
        }
        
        public int getLindex()
        {
        	return left_index;
        }
        public void refleshLindex()
        {
        	left_index++;
        }
        
        public int getRindex()
        {
        	return right_index;
        }
        public void refleshRindex()
        {
        	right_index++;
        }
        
        public int getTakePicNum()
        {
        	return TakePicNum;
        }
        public void incTakePicNum()
        {
        	TakePicNum++;
        }
        public void SetTakePicNum(int tpn)
        {
        	TakePicNum = tpn;
        }
        
        public boolean getHasData()
        {
        	boolean temp = HasData;
        	
        	if (HasData == true)
        	{
            	HasData = false;        		
        	}
        	
        	return temp;
        }
        
        public void SetHasData()
        {
        	HasData = true;
        }        
}