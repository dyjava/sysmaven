package com.sys.applet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/** 
 * 引导页面
 * by dyong 2010-8-18
 */
public class Main implements ActionListener{
	boolean packFrame = false ;
	public Main(){
    	jbInit();
    	
    	JFrame frame = new LoginFrame();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
	}
	
	private void jbInit() {
		ConstService.initService() ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(
        	new Runnable(){
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                new Main();
            }
            }
        );
    }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
