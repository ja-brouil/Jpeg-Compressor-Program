package com.jb.compression;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ProcessImagesButton extends ButtonListeners{
	

	public ProcessImagesButton(MainMenuFrame mainMenuFrame) {
		super(mainMenuFrame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		mainMenuFrame.getTextArea().setText("");
		try {
			mainMenuFrame.getCompressionEngine().compressFiles();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}

class ImportButtonListener extends ButtonListeners{ 
	
	public ImportButtonListener(MainMenuFrame mainMenuFrame) {
		super(mainMenuFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		returnValue = mainMenuFrame.getJFileChooser().showOpenDialog(mainMenuFrame);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			mainMenuFrame.getCompressionEngine().setImportLocation(mainMenuFrame.getJFileChooser().getSelectedFile());
			mainMenuFrame.getImportLabel().setText(mainMenuFrame.getCompressionEngine().getImportLocation());
		}
	}
	
}

class ExportButtonListener extends ButtonListeners{

	public ExportButtonListener(MainMenuFrame mainMenuFrame) {
		super(mainMenuFrame);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		returnValue = mainMenuFrame.getJFileChooserDirectory().showOpenDialog(mainMenuFrame);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			mainMenuFrame.getCompressionEngine().setExportLocation(mainMenuFrame.getJFileChooserDirectory().getSelectedFile());
			mainMenuFrame.getLblSelectFolderTo().setText(mainMenuFrame.getCompressionEngine().getExportLocation());
		}
	}
	
}

class CancelProcessButton extends ButtonListeners{
	
	private ProcessImagesButton processImagesButton;
	
	public CancelProcessButton(MainMenuFrame mainMenuFrame, ProcessImagesButton processImagesButton) {
		super(mainMenuFrame);
		this.processImagesButton = processImagesButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}

abstract class ButtonListeners implements ActionListener{
	
	protected MainMenuFrame mainMenuFrame;
	protected int returnValue;
	
	public ButtonListeners(MainMenuFrame mainMenuFrame) {
		this.mainMenuFrame = mainMenuFrame;
	}	
}