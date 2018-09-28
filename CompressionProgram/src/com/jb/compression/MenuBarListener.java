package com.jb.compression;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

// About
public class MenuBarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Select a file or folder to import JPEGs or PNGs.\nSelect an export folder for the compressed files.\nSet the compression rate. 1 = highest quality and least compressed 0 = least quality and most compressed.");
	}
}

// Exit
class MenuExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
}
