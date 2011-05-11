package com.camera;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
 
public class SyncDataThread extends Thread 
{
	int recieve_port = 12124;
   	int port = 12121;
	
	@Override
	public void run() 
	{
		while (true)
		{
			int group_size = Main.camerGroupList.size();
			for (int i=0; i<group_size; i++)
			{
				CameraGroup groupd = Main.camerGroupList.get(i);
				
				if (!groupd.getHasData()) continue;
				
				int start_index = groupd.getLindex();
				
				int count = start_index;
				int CameraTakePictureNumber = groupd.getRindex();
				
       		    try {
			           //Open Socket Waiting
       		    		ServerSocket serverSkt = new ServerSocket(recieve_port);
       		    		
       		    		while (CameraTakePictureNumber > count)
			             {
				          	 //prepare directory
				    	     String fileName = Main.my.root_path + "/" + groupd.getGroupID() + "/" + count + "/" + Main.IMEI  + "-" + count  + ".jpg" ;
				    	     File directory = new File(Main.my.root_path + "/" + groupd.getGroupID() + "/" + count);

			            	  BufferedInputStream inputStream;
			            	  BufferedOutputStream outputStream;
				    	        
					         if (!directory.exists()) 
						               directory.mkdirs();

				    	      System.out.println("save fileName: " + fileName);
					            
					          for (i=0; i<groupd.getIDSize(); i++)
					           {
					        	  int index = groupd.getID(i);
					        	  
					        	   if (Main.my.syncT[index] == false) continue;
					        	   
			            		  //Send message for request
			            		  Socket client = new Socket();
			            		  InetSocketAddress isa = new InetSocketAddress(Main.my.JTextF[index].getText() , port);
			            		  client.connect(isa, 10000);
			            		  DataOutputStream out = new DataOutputStream(client.getOutputStream());
				            	  
				            	  out.writeUTF("getPicData");            	            	
				            	  out.writeUTF(Integer.toString(count)); 
				            	  
				            	  //waiting pic data
				            	  Socket clientSkt = serverSkt.accept();
					                
					             System.out.printf("%s connect to \n", 
					                        clientSkt.getInetAddress().toString());  
					 
					             inputStream = 
					                    new BufferedInputStream(clientSkt.getInputStream()); 
					                
					             outputStream = 
					                    new BufferedOutputStream(new FileOutputStream(fileName)); 
					 
					             int bufferSize = 1024;
					             byte[] buffer = new byte[bufferSize];
					             int length;
					 
					             while((length = inputStream.read(buffer)) != -1)
					               {
					              	  	outputStream.write(buffer, 0, length);
					               }
					 
						          outputStream.flush();
						          outputStream.close();                
						          inputStream.close(); 
						          clientSkt.close();
				           	    }
					          count++;
						}

       		    		serverSkt.close();
      	    			//Main.my.jLabelStatus.setText("Sync Picture Success.");
					}
       		    	catch (Exception ec)
					{
       		    		ec.printStackTrace();
					}
			}

			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}