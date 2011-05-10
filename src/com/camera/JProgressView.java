package com.camera;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JProgressView extends JFrame implements Runnable{
    private boolean isRun;
    private JProgressBar progressBar;
    private LoadData loadObj;
    private JLabel percent;
   
    public JProgressView() {
        LoadBarInit();
    }
   
    public JProgressView(String title) {
        super(title);
        LoadBarInit();
    }
   
    public void LoadBarInit(){
        Container  c = getContentPane();
        JPanel progressPanel = new JPanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
        progressPanel.setLayout(null);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);

        progressBar.setBounds(0,25,200,20);
        progressPanel.add(progressBar);
        progressBar.setOpaque(false);

        percent = new JLabel("0%");
        percent.setBounds(90, 0, 40, 20);

        progressPanel.add(percent);
        c.add(progressPanel);
       
        setLocation(
                (screenSize.width - 200) / 2,
                (screenSize.height - 100) / 2);
        setSize(200,100);
        setResizable(false);
        
        //setAlwaysOnTop(true);
       // setDefaultCloseOperation(HIDE_ON_CLOSE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
   
    public void setLoadObj(LoadData lobj){
        loadObj = lobj;
    }
   
    public void startLoad(){
        isRun = true;
        Thread load = new Thread(this);
        load.start();
    }
   
    public void stopLoad(){
        isRun = false;
    }
   
    public void run(){
        int value = loadObj.currentProeess;
        int end = loadObj.endValue;
        
        while(isRun && (value < end)){
            value = loadObj.currentProeess;
            int percentValue = (value * 100)/end;
            progressBar.setValue(percentValue);
            percent.setText(percentValue+"%");
            Thread.yield();
        }
        progressBar.setValue(100);
    }
   
    public void clear(){
        loadObj = null;
        setVisible(false);
    }
}
