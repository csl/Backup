package com.camera;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ProfileDialog extends JFrame 
{
	private JPanel jPanelSmtpSet = null;
	private JPanel jPanelPopSet = null;
	private JPanel getJPanelConfigure = null;
	private JPanel jPanelButton = null;
	private JPanel jContentPane = null;
	private JPanel jPanelUserInfo = null;

	private JLabel jLabelCameraCount = null;
	private JLabel jLabelPicturePath = null;
	
	protected JTextField jTextFieldCameraCount = null;
	protected JTextField jTextFieldPicturePath = null;
	
	private JButton jButtonOK = null;
	private JButton jButtonCancel = null;
	
	private Main my;

	public ProfileDialog(Main myd) 
	{
		initialize();
		loadProperties();
		my = myd;
	}

	public void initialize() {
		this.setSize(300, 150);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Configure");
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			jContentPane = new JPanel();
			jContentPane.setLayout(gridLayout);
			jContentPane.add(getJPanelConfigure(), null);
		}
		return jContentPane;
	}

	public JPanel getJPanelConfigure() {
		if (getJPanelConfigure == null) {

			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.fill = GridBagConstraints.NONE;
			gridBagConstraints9.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints9.gridwidth = 1;
			gridBagConstraints9.ipadx = 0;
			gridBagConstraints9.ipady = 0;
			gridBagConstraints9.weightx = 0.0;
			gridBagConstraints9.anchor = GridBagConstraints.CENTER;
			gridBagConstraints9.gridy = 2;
			
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.ipadx = 180;
			gridBagConstraints8.ipady = 14;
			gridBagConstraints8.anchor = GridBagConstraints.CENTER ;
			gridBagConstraints8.weightx = 0.0;
			gridBagConstraints8.gridwidth = 1;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridheight = 1;
			gridBagConstraints8.gridx = 0;
			getJPanelConfigure = new JPanel();
			getJPanelConfigure.setLayout(new GridBagLayout());
			getJPanelConfigure.add(getJPanelUserInfo(), gridBagConstraints8);
			getJPanelConfigure.add(getJPanelButton(), gridBagConstraints9);
			//getJPanelConfigure.add(getJPanelSmtpSet(), gridBagConstraints10);
			//getJPanelConfigure.add(getJPanelPopSet(), gridBagConstraints15);
		}
		return getJPanelConfigure;
	}

	public JPanel getJPanelUserInfo() {
		if (jPanelUserInfo == null) {
			jLabelCameraCount = new JLabel();
			jLabelCameraCount.setText("CameraCount:");
			jLabelPicturePath = new JLabel();
			jLabelPicturePath.setText("PicturePath:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 5;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 5;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.insets = new Insets(0, 0, 2, 4);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 4;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(0, 0, 2, 4);
			jPanelUserInfo = new JPanel();
			TitledBorder titledBorderUserInfo = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"User Information");
			jPanelUserInfo.setBorder(titledBorderUserInfo);
			jPanelUserInfo.setLayout(new GridBagLayout());
			jPanelUserInfo.add(jLabelCameraCount, gridBagConstraints4);
			jPanelUserInfo.add(getJTextFieldCameraCount(), gridBagConstraints5);
			jPanelUserInfo.add(jLabelPicturePath, gridBagConstraints6);
			jPanelUserInfo.add(getjFieldPicturePath(), gridBagConstraints7);
		}
		return jPanelUserInfo;
	}

	public JTextField getJTextFieldCameraCount() {
		if (jTextFieldCameraCount == null) {
			jTextFieldCameraCount = new JTextField();
		}
		return jTextFieldCameraCount;
	}

	public JTextField getjFieldPicturePath() {
		if (jTextFieldPicturePath == null) {
			jTextFieldPicturePath = new JTextField();
		}
		return jTextFieldPicturePath;
	}

	public JPanel getJPanelButton() {
		if (jPanelButton == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(1);
			gridLayout2.setVgap(0);
			gridLayout2.setHgap(10);
			jPanelButton = new JPanel();
			jPanelButton.setLayout(gridLayout2);
			jPanelButton.add(getJButtonOK(), null);
			jPanelButton.add(getJButtonCancel(), null);
		}
		return jPanelButton;
	}

	public JButton getJButtonOK() {
		if (jButtonOK == null) {
			jButtonOK = new JButton();
			jButtonOK.setText("OK(S)");
			jButtonOK.setMnemonic(java.awt.event.KeyEvent.VK_S);
			jButtonOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveProperties();
					dispose();
				}
			});
		}
		return jButtonOK;
	}

	public JButton getJButtonCancel() {
		if (jButtonCancel == null) {
			jButtonCancel = new JButton();
			jButtonCancel.setText("Cancel");
			jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cancel();
				}
			});
		}
		return jButtonCancel;
	}
	
	public void saveProperties() {
		try {
			Properties p = new Properties();

			FileOutputStream os = new FileOutputStream("profile.properties");

            p.setProperty("CameraNum", jTextFieldCameraCount.getText());
            p.setProperty("PicturePath", jTextFieldPicturePath.getText());
            
            my.CameraNum = Integer.valueOf(jTextFieldCameraCount.getText());
            my.root_path = jTextFieldPicturePath.getText();
			  my.initialize();
			p.store(os, null);
			os.close();
        } catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void loadProperties() {
		try {
			Properties p = new Properties();
			
			File fp = new File("profile.properties");
			
			if (!fp.exists())
			{
				fp.createNewFile();
			}

			FileInputStream in = new FileInputStream("profile.properties");

			p.load(in);

			jTextFieldCameraCount.setText(p.getProperty("CameraNum")) ;
			jTextFieldPicturePath.setText(p.getProperty("PicturePath"));
			
		    in.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
    public void actionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == jButtonOK) {
            saveProperties();
			  my.initialize();
        }
        else if (evt.getSource() == jButtonCancel) {
            cancel();
        }
    }
	
	public void cancel() {
        dispose();
    }

	private JPanel getJPanelSmtpSet() {
		if (jPanelSmtpSet == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.anchor = GridBagConstraints.CENTER;
			gridBagConstraints2.insets = new Insets(0, 0, 2, 4);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.insets = new Insets(0, 0, 2, 4);
			jPanelSmtpSet = new JPanel();
			TitledBorder titledBorderSmtp = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"SMTP");
			jPanelSmtpSet.setBorder(titledBorderSmtp);
			jPanelSmtpSet.setLayout(new GridBagLayout());
			//jPanelSmtpSet.add(jLabelSmtpHost, gridBagConstraints);
			//jPanelSmtpSet.add(getJTextFieldSmtpHost(), gridBagConstraints1);
			//jPanelSmtpSet.add(jLabelSmtpPort, gridBagConstraints2);
			//jPanelSmtpSet.add(getJTextFieldSmtpPort(), gridBagConstraints3);
		}
		return jPanelSmtpSet;
	}

	/**
	 * This method initializes jPanelPopSet	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelPopSet() {
		if (jPanelPopSet == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints14.gridx = 1;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.insets = new Insets(0, 0, 2, 4);
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.insets = new Insets(0, 0, 2, 0);
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(0, 0, 2, 4);
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			jPanelPopSet = new JPanel();
			TitledBorder titledBorderPop = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"POP3");
			jPanelPopSet.setBorder(titledBorderPop);
			jPanelPopSet.setLayout(new GridBagLayout());
			//jPanelPopSet.add(jLabelPopHost, gridBagConstraints11);
			//jPanelPopSet.add(getJTextFieldPopHost(), gridBagConstraints12);
			//jPanelPopSet.add(jLabelPopPort, gridBagConstraints13);
			//jPanelPopSet.add(getJTextFieldPopPort(), gridBagConstraints14);
		}
		return jPanelPopSet;
	}

}