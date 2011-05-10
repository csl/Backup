package com.camera;

import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class Main extends JFrame 
{

	static int CameraNum = 3;
	
	private Main my = this;

	static int CameraTakePictureNumber = 0;
	//ProfileDialog pd = new ProfileDialog();
	static SplashScreen splasher = new SplashScreen();
	private JFrame frame;
	static public String IMEI;
	private int count = 0;
	private boolean [] syncT;
	public String root_path = "/home/shulong/camera/";
	private int CTakePicture;
	
	//TOP
	protected JMenu jMenuFile = null;
	protected JMenu jMenuHelp = null;
	protected JMenuBar jJMenuBar = null;
	protected JMenuItem jMenuItemOption = null;
	protected JMenuItem jMenuItemSetup = null;
	protected JMenuItem jMenuItemExit = null;
	
	//Middle
	protected JPanel jPanelMiddle = null;
	protected JPanel jPanelToolBarBar = null;
	protected JPanel jPanelToolBarBarDetail = null;

	protected JToolBar JToolBarBar = null;

	protected static JLabel jLabelCameraTotal = null;
	protected static JLabel jLabelCameraStatus = null;

	protected JButton JButtonSync = null;
	protected JButton JButtonTakePicture = null;
	protected JButton JButtonSyncData = null;	

	//Bottom
	protected JTabbedPane jTabbedPane = null;
	protected JPanel jPanelCameraTabContext = null;
	protected JPanel jPanelCameraTabContextDetail = null;
	protected JPanel jPanelInCameraTab = null;
	private JTextField[] JTextF; 
	private JLabel[] JSpLabel; 
	private JTextField[] JTextSec; 
	private JLabel[] JSpLabelSec; 
	protected JLabel[] jLabelSyunStatus;
	protected JLabel[] jLabelCheckSumStatus; 
	protected JButton[] jbn; 
	protected MonitorCameraSocket[] newSocket;
	protected int[] CameraStatus;
	protected boolean[] TakePictureStatus;
	
	protected JLabel jLabelReceiver = null;
	protected JLabel jLabelSubject = null;
	protected JPanel jPanel3 = null;
	protected JPanel jPanel1 = null;
	protected JScrollPane jScrollPane = null;

	protected JMenuItem jMenuItemAbout = null;

	protected static JTextField jTextFieldSender = null;
	static JTextField jTextFieldReceiveDate = null;
	static JTextField jTextFieldReceiveSubject = null;
	protected static JTextField jTextFieldReceiveSender = null;
	protected static JTextArea jTextArea1 = null;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane1 = null;
	private JPanel jPanel9 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JDialog jDialogAbout = null;
	private JPanel jContentPane1 = null;
	private JLabel jLabelName1 = null;
	private JLabel jLabelName2 = null;
	private JLabel jLabelAddress1 = null;
	private JLabel jLabelAddress2 = null;
	protected  JButton jButtonArrowLeft = null;
	protected  JButton jButtonArrowRight = null;
	protected  JLabel jLabelStatus = null;
	
	public static ArrayList<CameraGroup> camerGroupList;
	protected boolean[] BchkSec;
	private Timer[] timer;
	private int timer_size;
	
	public Main(int num, String path) 
	{
		super();
		CameraNum = num;
		root_path = path;
		timer_size = 0;
		initialize();

		camerGroupList = new ArrayList<CameraGroup>();
		BchkSec = new boolean[CameraNum];
		timer = new Timer[CameraNum];
		
		
		splasher.hide();
	}

	
	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void initialize() 
	{
		int _programNum = CameraNum;
		
		JTextF = new JTextField[_programNum]; 
		JSpLabel = new JLabel[_programNum]; 
		JTextSec = new JTextField[_programNum]; 
		JSpLabelSec = new JLabel[_programNum]; 
		jLabelSyunStatus = new JLabel[_programNum];
		jLabelCheckSumStatus = new JLabel[_programNum]; 
		jbn = new JButton[_programNum]; 
		newSocket = new MonitorCameraSocket[_programNum];
		CameraStatus = new int[_programNum];
		TakePictureStatus = new boolean[_programNum];
		syncT = new boolean[_programNum];
		
		for (int i=0; i<CameraNum; i++)
		{
			syncT[i] = false;
		}
		
		this.setSize(508, 558);
		this.setContentPane(getJPanelMiddle());
		this.setJMenuBar(getJMenuBar());
		this.setTitle("Monitor Camera v1.0");
		this.setResizable(false);
		
		try
		{
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon image1 = new ImageIcon("images/LOGO.jpg");
			setIconImage(image1.getImage());
		}
		catch(Exception x)
		{
			
		}
	}
	
	public JMenuBar getJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuFile());
			jJMenuBar.add(getJMenuHelp());
		}
		return jJMenuBar;
	}

	public JMenu getJMenuFile() {
		if (jMenuFile == null) {
			jMenuFile = new JMenu();
			jMenuFile.add(getJMenuItemSetup());
			jMenuFile.add(getJMenuItemExit());
			jMenuFile.setText("Configure(C)");
			jMenuFile.setMnemonic(java.awt.event.KeyEvent.VK_C);
		}
		return jMenuFile;
	}

	public JMenu getJMenuHelp() {
		if (jMenuHelp == null) {
			jMenuHelp = new JMenu();
			jMenuHelp.add(getJMenuItemAbout());
			jMenuHelp.setText("Help(H)");
			jMenuHelp.setMnemonic(java.awt.event.KeyEvent.VK_H);
		}
		return jMenuHelp;
	}

	public JMenuItem getJMenuItemSetup() {
		if (jMenuItemOption == null) {
			jMenuItemOption = new JMenuItem();
			jMenuItemOption.setText("Setup");
			jMenuItemOption
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) 
						{
							ProfileDialog pd = new ProfileDialog(my);
							Dimension dlgSize = pd.getPreferredSize();
							Dimension frmSize = getSize();
							Point loc = getLocation();
							pd.setLocation((frmSize.width - dlgSize.width) / 2
									+ loc.x, (frmSize.height - dlgSize.height)
									/ 2 + loc.y);
							pd.show();							
						}
					});
		}
		return jMenuItemOption;
	}	
	
	public JMenuItem getJMenuItemOption() {
		if (jMenuItemOption == null) {
			jMenuItemOption = new JMenuItem();
			jMenuItemOption.setText("Sync");
			jMenuItemOption
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) 
						{
							//camerGroupList
							for (int i=0; i<CameraNum; i++)
							{
								if (BchkSec[i] == true) continue;
								
								String sec = JTextSec[i].getText();
								CameraGroup mcameragroup = new CameraGroup(Integer.valueOf(sec));
								mcameragroup.addID(i);
								
								for (int j=i+1; j<CameraNum; j++)
								{
									if (sec.equals(JTextSec[j].getText()))
									{
										BchkSec[j] = true;
										mcameragroup.addID(j);
									}
								}						

								camerGroupList.add(mcameragroup);
							}
							
							int count=0;
							for (CameraGroup item: camerGroupList)
							{
								CameraGroup mcameragroup = item;
								int sec_d = mcameragroup.getTimeSec();
								timer[timer_size].schedule(new DateTask(count), sec_d);
								timer_size++;
								count++;
							}
						}
					});
		}
		return jMenuItemOption;
	}

	public JMenuItem getJMenuItemExit() {
		if (jMenuItemExit == null) {
			jMenuItemExit = new JMenuItem();
			jMenuItemExit.setText("Exit");
			jMenuItemExit.setMnemonic(java.awt.event.KeyEvent.VK_X);
			jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_X, java.awt.Event.ALT_MASK,
					false));
			jMenuItemExit
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.exit(0);
						}
					});
		}
		return jMenuItemExit;
	}

	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("CameraStatus", getJPanelTabCameraStatus());
			//jTabbedPane.addTab("Setting", getJPanel8());
			jTabbedPane.setBounds(new Rectangle(1, 45, 501, 442));
		}
		return jTabbedPane;
	}

	public JPanel getJPanelTabCameraStatus() {
		if (jPanelCameraTabContext == null) {
			jPanelCameraTabContext = new JPanel();
			jPanelCameraTabContext.setLayout(null);
			jPanelCameraTabContext.add(getJPanelCameraTabContext(), null);
		}
		return jPanelCameraTabContext;
	}

	public JPanel getJPanelCameraTabContext() {
		if (jPanelCameraTabContextDetail == null) {
			jPanelCameraTabContextDetail = new JPanel();
			jPanelCameraTabContextDetail.setLayout(null);
			jPanelCameraTabContextDetail.setBounds(new Rectangle(14, 2,  501, 442));
			jPanelCameraTabContextDetail.add(getJPanelInCameraTab(), null);
			//jPanelCameraTabContextDetail.add(getJPanel3(), null);
		}
		return jPanelCameraTabContextDetail;
	}
	
	//Button Listener
	static class ButtonListener implements ActionListener{
		
		private int bindex;
		private Main subclass;
		
		public ButtonListener(int index, Main my)
		{
			bindex = index;
			subclass = my;
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			int port = 12121;
			int timeout = 10000000;

			subclass.newSocket[bindex] = new MonitorCameraSocket(bindex, this.subclass);
			subclass.newSocket[bindex].SetAddressPort(subclass.JTextF[bindex].getText() , port);
			subclass.newSocket[bindex].SetFunction(3);

			int count = 0;
			while (true)
			{
				if (subclass.newSocket[count].isAlive() == true)
				{
					count++;
				}
				
				if (3 == count) break;
			}
		}
	}
	
	private int SetJPanel(final int i, JPanel TabJPanel) 
	{
		
		JSpLabel[i] = new JLabel();
		JSpLabel[i].setText("IP");
		JSpLabel[i].setBounds(new Rectangle(0, i*40 + 20, 45, 27));
		
		JTextF[i] = new JTextField();
		JTextF[i].setBounds(new Rectangle(30, i*40 + 20, 100, 22));
		
		JSpLabelSec[i] = new JLabel();
		JSpLabelSec[i].setText("Sec");
		JSpLabelSec[i].setBounds(new Rectangle(0 + 140, i*40 + 20, 45, 27));
		
		JTextSec[i] = new JTextField();
		JTextSec[i].setBounds(new Rectangle(30 + 140, i*40 + 20, 50, 22));

		jLabelSyunStatus[i] = new JLabel();
		jLabelSyunStatus[i].setText("NotSync");
		jLabelSyunStatus[i].setBounds(new Rectangle(30 + 200, i*40 + 20 , 60, 27));
		
		jLabelCheckSumStatus[i] = new JLabel();
		jLabelCheckSumStatus[i].setText("NoChecknum");
		jLabelCheckSumStatus[i].setBounds(new Rectangle(30 + 220 + 60, i*40 + 20, 100, 27));
		
		jbn[i] = new JButton();
		jbn[i].setText("Sync");
		jbn[i].setBounds(new Rectangle(30 + 220 + 60 + 95, i*40 + 20, 68, 27));
		jbn[i].addActionListener(new ButtonListener(i, this));
		jbn[i].setEnabled(false);
		
		TabJPanel.add(JSpLabel[i], null);
		TabJPanel.add(JTextF[i], null);
		TabJPanel.add(JSpLabelSec[i], null);
		TabJPanel.add(JTextSec[i], null);
		TabJPanel.add(jLabelSyunStatus[i], null);
		TabJPanel.add(jLabelCheckSumStatus[i], null);
		TabJPanel.add(jbn[i], null);

		return 0;
	}

	public JPanel getJPanelInCameraTab() {
		if (jPanelInCameraTab == null) {
			jPanelInCameraTab = new JPanel();
			jPanelInCameraTab.setLayout(null);
			jPanelInCameraTab.setBounds(new Rectangle(0, 0,  501, 442));
			jPanelInCameraTab.setName("jPanelInCameraTab");

			for (int i=0; i<CameraNum; i++)
			{
				SetJPanel(i, jPanelInCameraTab);
			}
		}
		return jPanelInCameraTab;
	}

	public JPanel getJPanelMiddle() {
		if (jPanelMiddle == null) {
			jLabelStatus = new JLabel();
			jLabelStatus.setBounds(new Rectangle(1, 487, 501, 16));
			jLabelStatus.setText("");
			
			jPanelMiddle = new JPanel();
			jPanelMiddle.setLayout(null);
			jPanelMiddle.add(getJPanelMidToolBar(), null);
			jPanelMiddle.add(getJTabbedPane(), null);
			jPanelMiddle.add(jLabelStatus, null);
		}
		return jPanelMiddle;
	}

	public JPanel getJPanelMidToolBar() {
		if (jPanelToolBarBar == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setVgap(0);
			gridLayout.setHgap(0);
			jPanelToolBarBar = new JPanel();
			jPanelToolBarBar.setBounds(new Rectangle(0, 0, 501, 37));
			jPanelToolBarBar.setLayout(gridLayout);
			jPanelToolBarBar.add(getJToolBarBar(), null);
		}
		return jPanelToolBarBar;
	}

	public JToolBar getJToolBarBar() {
		if (JToolBarBar == null) {
			JToolBarBar = new JToolBar();
			JToolBarBar.add(getJPanelToolBarBarDetail());
		}
		return JToolBarBar;
		}

	public JPanel getJPanelToolBarBarDetail() {
		if (jPanelToolBarBarDetail == null) {
			jLabelCameraStatus = new JLabel();
			jLabelCameraStatus.setBounds(new Rectangle(350, 19, 120, 15));
			jLabelCameraTotal = new JLabel();
			jLabelCameraTotal.setBounds(new Rectangle(350, 1, 120, 15));
			jPanelToolBarBarDetail = new JPanel();
			jPanelToolBarBarDetail.setLayout(null);
			jPanelToolBarBarDetail.add(getJButtonSync(), null);
			jPanelToolBarBarDetail.add(getJButtonTakePicture(), null);
			jPanelToolBarBarDetail.add(getJButtonSyncData(), null);
			jPanelToolBarBarDetail.add(jLabelCameraTotal, null);
			jPanelToolBarBarDetail.add(jLabelCameraStatus, null);
		}
		return jPanelToolBarBarDetail;
	}
	
	public void SyncCallback(int index, int timeout, String timestamp)
	{
		if (timeout == 0)
		{
			jbn[index].setEnabled(true);						
			jLabelSyunStatus[index].setText("error");
			jLabelCheckSumStatus[index].setText(newSocket[index].error_string);
		}
		else
		{
			CameraStatus[index] = Integer.parseInt(timestamp);
			jLabelCheckSumStatus[index].setText(Integer.toString(CameraStatus[index]));		
		}		
	}
	
	public void NeedSyncCallback(int index, int timeout, String timestamp)
	{
		if (timeout == 0)
		{
			jbn[index].setEnabled(true);						
			jLabelSyunStatus[index].setText("error");
			jLabelCheckSumStatus[index].setText(newSocket[index].error_string);
		}
		else
		{
			CameraStatus[index] = Integer.parseInt(timestamp);
			jLabelCheckSumStatus[index].setText(Integer.toString(CameraStatus[index]));
			syncT[index] = false;
			jbn[index].setEnabled(false);						
		}
	}

	public void TakePictureCallback(int index, int timeout, String err)
	{
		if (timeout == 0)
		{
			jbn[index].setEnabled(true);						
			jLabelSyunStatus[index].setText("error");
			jLabelCheckSumStatus[index].setText(newSocket[index].error_string);
		}
		else
		{
			jLabelCheckSumStatus[index].setText(err);
			if (err.equals("error"))
			{
				CTakePicture = 1;
			}
		}
	}
	
	
	public JButton getJButtonSync() 
	{
		if (JButtonSync == null) {
			JButtonSync = new JButton();
			JButtonSync.setIcon(new ImageIcon("images/sync.png"));
			JButtonSync.setToolTipText("Sync");
			JButtonSync.setBounds(new Rectangle(1, 0, 58, 33));
			JButtonSync.setMnemonic(java.awt.event.KeyEvent.VK_S);
			JButtonSync.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent ae) 
				{
  	    			jLabelStatus.setText("");
					CameraTakePictureNumber = 0;
					
					int port = 12121;
					for (int i=0; i<CameraNum; i++)
					{
						newSocket[i] = new MonitorCameraSocket(i, my);
						newSocket[i].SetAddressPort(JTextF[i].getText() , port);
						newSocket[i].SetFunction(1);
					}

					//Start
					for (int i=0; i<CameraNum; i++)
					{
						newSocket[i].start();
					}
					
					int count = 0;
					while (true)
					{
						if (newSocket[count].isAlive() == true)
						{
							count++;
						}
						
						if (CameraNum == count) break;
					}
					
					int sync = 0;
					sync = CameraStatus[0];
					for (int i=1; i<CameraNum; i++)
					{
						if (CameraStatus[i] < sync)
						{
							sync = CameraStatus[i];
						}						
					}

					//Check
					for (int i=0; i<CameraNum; i++)
					{
						if (CameraStatus[i] != sync)
						{
							jLabelSyunStatus[i].setText("NoSync");
							
							newSocket[i] = new MonitorCameraSocket(i, my);
							newSocket[i].SetAddressPort(JTextF[i].getText() , port);							
							newSocket[i].SetFunction(3);							
							newSocket[i].start();

							count = 0;
							while (true)
							{
								if (newSocket[count].isAlive() == false)
								{
									count++;
								}
								
								if (CameraNum == count) break;
							}
						}
						else
						{
							jLabelSyunStatus[i].setText("Sync");
							newSocket[i] = new MonitorCameraSocket(i, my);
							newSocket[i].SetAddressPort(JTextF[i].getText() , port);							
							newSocket[i].SetFunction(2);							
							newSocket[i].start();
							syncT[i] = true;
							jbn[i].setEnabled(false);
							jLabelCheckSumStatus[i].setText(Integer.toString(CameraStatus[i]));
						}
						
					}
  	    			jLabelStatus.setText("Sync Success.");
					
				}
			});
		}
		return JButtonSync;
	}
	
	private JButton getJButtonTakePicture() 
	{
		if (JButtonTakePicture == null) {
			JButtonTakePicture = new JButton();
			JButtonTakePicture.setBounds(new Rectangle(70, 0, 58, 33));
			JButtonTakePicture.setMnemonic(KeyEvent.VK_R);
			JButtonTakePicture.setIcon(new ImageIcon("images/take_picture.png"));
			JButtonTakePicture.setToolTipText("TakePicture");
			JButtonTakePicture
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
		  	    			jLabelStatus.setText("");
							try {
								
								//camerGroupList
								for (int i=0; i<CameraNum; i++)
								{
									if (BchkSec[i] == true) continue;
									
									String sec = JTextSec[i].getText();
									CameraGroup mcameragroup = new CameraGroup(Integer.valueOf(sec));
									mcameragroup.addID(i);
									
									for (int j=i+1; j<CameraNum; j++)
									{
										if (sec.equals(JTextSec[j].getText()))
										{
											BchkSec[j] = true;
											mcameragroup.addID(j);
										}
									}						

									camerGroupList.add(mcameragroup);
								}
								
								int count=0;
								for (CameraGroup item: camerGroupList)
								{
									CameraGroup mcameragroup = item;
									int sec_d = mcameragroup.getTimeSec();
									timer[timer_size].schedule(new DateTask(count), sec_d);
									timer_size++;
									count++;
								}
							
								
								
/*								
								int port = 12121;
								CTakePicture = 0;
								for (int i=0; i<CameraNum; i++)
								{
									newSocket[i] = new MonitorCameraSocket(i, my);
									newSocket[i].SetAddressPort(JTextF[i].getText() , port);
									newSocket[i].SetFunction(4);
									newSocket[i].SetCount(CameraTakePictureNumber);									
								}

								//Start
								for (int i=0; i<CameraNum; i++)
								{
									newSocket[i].start();
								}

								int count = 0;
								while (true)
								{
									if (newSocket[count].isAlive() == true)
									{
										count++;
									}
									
									if (CameraNum == count) break;
								}
								
								System.out.println(Integer.valueOf(CTakePicture));
								
								if (CTakePicture == 1)
								{
									for (int i=0; i<CameraNum; i++)
									{
										newSocket[i] = new MonitorCameraSocket(i, my);
										newSocket[i].SetAddressPort(JTextF[i].getText() , port);
										newSocket[i].SetFunction(4);
										newSocket[i].SetCount(CameraTakePictureNumber);									
									}									
									
									for (int i=0; i<CameraNum; i++)
									{
										newSocket[i].start();
									}
									
								}
								else
								{
								CameraTakePictureNumber++;
								System.out.println("CameraTakePictureNumber: " + CameraTakePictureNumber);
								}
							*/	
							} catch (Exception ec) {
							}
							
          	    			jLabelStatus.setText("Take Picture Success.");
          	    			
						}
					});
			JButtonTakePicture.setMnemonic(java.awt.event.KeyEvent.VK_R);
		}
		return JButtonTakePicture;
	}

	public JButton getJButtonSyncData() {
		if (JButtonSyncData == null) {
			JButtonSyncData = new JButton();
			JButtonSyncData.setIcon(new ImageIcon("images/syncdata.png"));
			JButtonSyncData.setToolTipText("SyncData");
			JButtonSyncData.setBounds(new Rectangle(140, 0, 58, 33));
			JButtonSyncData
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
  	    			 jLabelStatus.setText("");
          	        final LoadData load = new LoadData();
          	        load.endValue = CameraTakePictureNumber * CameraNum;

          	        final JProgressView lb = new JProgressView("sync data");
          	        lb.setLoadObj(load);
          	        lb.startLoad();
          	        
          	        //start
          	        Thread tl = new Thread()
          	         {
          	        	 LoadData lobj = load;
               		 int recieve_port = 12124;
               		 int port = 12121;

               		 public void run()
          	             {
                   		   try {
        			           //Open Socket Waiting
        		        	    ServerSocket serverSkt = new ServerSocket(recieve_port);
        			           while (CameraTakePictureNumber > count)
        			             {
        				          	 //prepare directory
        				    	     String fileName = root_path + "/" + count + "/" + Main.IMEI  + "-" + count  + ".jpg" ;
        				    	     File directory = new File(root_path + "/" + count);

        			            	  BufferedInputStream inputStream;
        			            	  BufferedOutputStream outputStream;
        				    	        
        					         if (!directory.exists()) 
        						               directory.mkdirs();

        				    	      System.out.println("fileName: " + fileName);
        					            
        					          for (int i=0; i<CameraNum; i++)
        					           {
        					        	   if (syncT[i] == false) continue;
        					        	   
        			            		  //Send message for request
        			            		  Socket client = new Socket();
        			            		  InetSocketAddress isa = new InetSocketAddress(JTextF[i].getText() , port);
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
        						          lobj.currentProeess++;
        				           	    }
        					          count++;
        						}

         	           	   serverSkt.close();
	          	    		   lb.stopLoad();
	          	    		   lb.clear();
	          	    			jLabelStatus.setText("Sync Picture Success.");
        					} catch (Exception ec) {
        					}
          	             }
          	        };
          	        
          	       tl.start();
          	       
				}
			});
		}
		return JButtonSyncData;
	}	
	public JMenuItem getJMenuItemAbout() {
		if (jMenuItemAbout == null) {
			jMenuItemAbout = new JMenuItem();
			jMenuItemAbout.setText("About");
			jMenuItemAbout.setMnemonic(java.awt.event.KeyEvent.VK_A);
			jMenuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
					java.awt.event.KeyEvent.VK_A, java.awt.Event.ALT_MASK,
					false));
			jMenuItemAbout
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getJDialogAbout();
							Dimension dlgSize = getJDialogAbout()
									.getPreferredSize();
							Dimension frmSize = getSize();
							Point loc = getLocation();
							getJDialogAbout()
									.setLocation(
											(frmSize.width - dlgSize.width) / 2
													+ loc.x,
											(frmSize.height - dlgSize.height)
													/ 2 + loc.y);
							jDialogAbout.show();
						}
					});
		}
		return jMenuItemAbout;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTabbedPane1(), gridBagConstraints2);
		}
		return jContentPane;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.addTab("", getJPanel9());
		}
		return jTabbedPane1;
	}

	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 6;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.BOTH;
			gridBagConstraints8.gridy = 5;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 4;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 0;
			jPanel9 = new JPanel();
			jPanel9.setLayout(new GridBagLayout());
			jPanel9.add(getJTextField(), gridBagConstraints3);
			jPanel9.add(getJTextField1(), gridBagConstraints4);
			jPanel9.add(getJTextField2(), gridBagConstraints5);
			jPanel9.add(getJTextField3(), gridBagConstraints6);
			jPanel9.add(getJTextField4(), gridBagConstraints7);
			jPanel9.add(getJTextField5(), gridBagConstraints8);
			jPanel9.add(getJTextField6(), gridBagConstraints9);
		}
		return jPanel9;
	}

	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		}
		return jTextField3;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
		}
		return jTextField4;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
		}
		return jTextField5;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		}
		return jTextField6;
	}

	private JDialog getJDialogAbout() {
		if (jDialogAbout == null) {
			jDialogAbout = new JDialog(this);
			jDialogAbout.setTitle("About MonitorCamera");
			jDialogAbout.setSize(new Dimension(236, 141));
			jDialogAbout.setContentPane(getJContentPane1());
		}
		return jDialogAbout;
	}

	private JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 1;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints13.gridy = 1;
			jLabelAddress2 = new JLabel();
			jLabelAddress2.setText("");
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints12.gridy = 0;
			jLabelAddress1 = new JLabel();
			jLabelAddress1.setText("");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 1;
			jLabelName2 = new JLabel();
			jLabelName2.setText("");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 0;
			jLabelName1 = new JLabel();
			jLabelName1.setText("");
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(new GridBagLayout());
			jContentPane1.add(jLabelName1, gridBagConstraints10);
			jContentPane1.add(jLabelName2, gridBagConstraints11);
			jContentPane1.add(jLabelAddress1, gridBagConstraints12);
			jContentPane1.add(jLabelAddress2, gridBagConstraints13);
		}
		return jContentPane1;
	}

	public static void main(String[] args) 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       splasher.setLocation(
                (screenSize.width - 400) / 2,
                (screenSize.height - 344) / 2);
       splasher.setSize(new Dimension(344,400));
       splasher.show();
       
		Properties p = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("profile.properties");
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		int CameraNum = Integer.valueOf(p.getProperty("CameraNum"));
		String root_path = p.getProperty("PicturePath");
        
		Main app = new Main(CameraNum, root_path);
		app.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		Dimension frameSize = app.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		app.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		app.setVisible(true);
	}
}