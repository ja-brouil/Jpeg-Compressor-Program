package com.jb.compression;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RateListener implements ChangeListener{

	private float rateValue;
	private MainMenuFrame mainMenuFrame;
	
	public RateListener(MainMenuFrame mainMenuFrame) {
		this.mainMenuFrame = mainMenuFrame;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		rateValue = (float) mainMenuFrame.getRateSlider().getValue() / 100f;
		
		mainMenuFrame.getRateLabel().setText("Rate: " + String.valueOf(rateValue));
	}
}
