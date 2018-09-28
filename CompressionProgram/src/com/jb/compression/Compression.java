package com.jb.compression;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Compression {
	
	// GUI access
	private MainMenuFrame mainMenuFrame;

	// Location of the files
	private File importFolder = null;
	private File exportLocation = null;
	private String exportLocationPath;
	private File[] listOfImageFiles;
	
	// Timers
	private int count;
	private float timeNow;
	private float timeEnd;
	
	public Compression(MainMenuFrame mainMenuFrame) {
		this.mainMenuFrame = mainMenuFrame;
	}
	
	public void compressFiles() throws IOException {
		
		// Prevent if import folder and exportLocation is null
		if (importFolder == null && exportLocation == null) {
			mainMenuFrame.getTextArea().setText("");
			mainMenuFrame.getTextArea().append("Missing import and export files/location!");
			return;
		} else if (importFolder == null && exportLocation != null) {
			mainMenuFrame.getTextArea().setText("");
			mainMenuFrame.getTextArea().append("Missing import file location!");
			return;
		} else if (exportLocation == null &&  importFolder != null) {
			mainMenuFrame.getTextArea().setText("");
			mainMenuFrame.getTextArea().append("Missing export file location!");
			return;
		}
		
		// Stream Data
		OutputStream oStream = null;
		ImageOutputStream imageOutputStream = null;
		Iterator<ImageWriter> writers = null;
		ImageWriter writer = null;
		
				try {
					// Get Import Location
					if (importFolder.isDirectory()) {
						listOfImageFiles = importFolder.listFiles();
					} else {
						listOfImageFiles = new File[1];
						listOfImageFiles[0] = importFolder;
					}
					
					// Set Export location
					exportLocationPath = exportLocation.getCanonicalPath() + "/";
					
					// Stats
					count = 0;
					timeNow = (float) System.currentTimeMillis();
					
					for (int i = 0; i < listOfImageFiles.length; i++) {
						// Which image are we processing
						File image = listOfImageFiles[i];
						
						// Skip PNG files
						String extension = image.getName().substring(image.getName().lastIndexOf("."), image.getName().length());
						if (extension.endsWith(".jpg") || extension.endsWith(".jpeg")) {
							mainMenuFrame.getTextArea().append("\nProcessing image: " + image.getName());
							mainMenuFrame.getTextArea().update(mainMenuFrame.getTextArea().getGraphics());
							
							// Set Image
							BufferedImage bufferedImage = null;
							try {
								bufferedImage = ImageIO.read(image);
							} catch (Exception e) {
								mainMenuFrame.getTextArea().append("\nError processing image: " + image.getName());
								mainMenuFrame.getTextArea().append("\n" + e.getMessage());
							}
							
							// Skip if the image failed or is damaged
							if (bufferedImage == null) {
								continue;
							} else {
								// Set Image Name
								File compressedFileImage = new File(exportLocationPath + image.getName());
								oStream = new FileOutputStream(compressedFileImage);
								
								// Iterator over all images
								writers = ImageIO.getImageWritersByFormatName("jpg");
								writer = (ImageWriter) writers.next();
								
								// Set Image Output Stream
								imageOutputStream = ImageIO.createImageOutputStream(oStream);
								writer.setOutput(imageOutputStream);
								
								// Set Image Parameters
								ImageWriteParam param = writer.getDefaultWriteParam();
								param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
								float rateSlider = (float) mainMenuFrame.getRateSlider().getValue() / 100f ;
								param.setCompressionQuality(rateSlider); 
								writer.write(null, new IIOImage(bufferedImage, null, null), param);
								
								// Close Streams
								oStream.close();
								imageOutputStream.close();
								writer.dispose();
								count++;
							}
							
						} else {
							continue;
						}
					}
					
					// Finished
					mainMenuFrame.getTextArea().append("\nCompressed " + count + " jpegs.");
					timeEnd = (float) System.currentTimeMillis();
					mainMenuFrame.getTextArea().append("\nProcess took: " + (timeEnd - timeNow) + " seconds.");
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					oStream.close();
					imageOutputStream.close();
					writer.dispose();
				}
			
		
		
	}
	
	
	// Mutator Access
	public String getImportLocation() {
		return importFolder.getAbsolutePath();
	}
	
	public void setImportLocation(File file) {
		importFolder = file;
	}
	
	public void setExportLocation(File file) {
		exportLocation = file;
	}
	
	public String getExportLocation() {
		return exportLocation.getAbsolutePath();
	}
}
