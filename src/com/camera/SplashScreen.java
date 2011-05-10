package com.camera;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends Window 
{
        JLabel jLabel1 = new JLabel();
        JLabel jLabelDWN = new JLabel();
        public SplashScreen() 
        {
                super(new Frame());
                try {
                        jbInit();
                }
                catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private void jbInit() throws Exception {
                this.setSize(new Dimension(400, 200));
                this.setVisible(false);
                //this.setVisible(true);
                jLabel1.setDoubleBuffered(true);
                
                jLabel1.setPreferredSize(new Dimension(400,190));
                jLabelDWN.setDoubleBuffered(true);
                jLabelDWN.setPreferredSize(new Dimension(400,10));
                jLabelDWN.setOpaque(true);
                jLabel1.setIcon(new ImageIcon("images/LOGO.jpg"));
                jLabelDWN.setIcon(new ImageIcon("images/LOGO.jpg"));
                this.add(jLabel1, BorderLayout.CENTER);
                this.add(jLabelDWN, BorderLayout.SOUTH);
        }
}