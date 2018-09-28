package com.jb.compression;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainMenuFrame extends JFrame {

	// Main Frame
	private static final long serialVersionUID = 123151L;
	private JPanel mainMenu;

	// GUI Components
	private JButton processImagesButton;
	private JSlider rateSlider;

	private JLabel compressionRateLabel;
	private JLabel rateLabel;
	private JLabel importLabel;
	private JLabel lblSelectFolderTo;

	private JButton importButton;
	private JButton exportButton;
	private JButton cancelProcessButton;
	
	private JFileChooser fileChooser;
	private JFileChooser fileChooserDirectory;
	
	private JTextArea textArea;
	
	// Compression
	private Compression compressionEngine;
	private JMenuBar menuBar_1;
	private JMenu mnAbout;
	private JMenu mnExit;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmHelp;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame() {
		// Main Frame
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 759);
		mainMenu = new JPanel();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		
		// Menu Bar
		menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		mnAbout = new JMenu("About");
		menuBar_1.add(mnAbout);
		
		mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new MenuBarListener());
		mnAbout.add(mntmHelp);
		
		mnExit = new JMenu("Exit");
		menuBar_1.add(mnExit);
		
		mntmNewMenuItem = new JMenuItem("Quit Program");
		mntmNewMenuItem.addActionListener(new MenuExitListener());
		mnExit.add(mntmNewMenuItem);
		setContentPane(mainMenu);

		// Process Images Button
		processImagesButton = new JButton("Process Images");
		processImagesButton.setBounds(118, 385, 244, 67);
		processImagesButton.setBackground(Color.LIGHT_GRAY);
		processImagesButton.setFont(new Font("Helvetica", Font.PLAIN, 13));
		ProcessImagesButton processImagesButtonListener = new ProcessImagesButton(this);
		processImagesButton.addActionListener(processImagesButtonListener);

		// Rate Slider
		rateSlider = new JSlider();
		rateSlider.setPaintLabels(true);
		rateSlider.setBounds(272, 343, 190, 30);
		rateSlider.addChangeListener(new RateListener(this));

		// Labels
		compressionRateLabel = new JLabel("Compression Rate");
		compressionRateLabel.setBounds(118, 343, 114, 16);

		rateLabel = new JLabel("Rate 0.5");
		rateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rateLabel.setBounds(547, 343, 148, 16);

		importLabel = new JLabel("Select Folder or File to import");
		importLabel.setHorizontalAlignment(SwingConstants.CENTER);
		importLabel.setBounds(203, 117, 516, 16);

		lblSelectFolderTo = new JLabel("Select Folder to export");
		lblSelectFolderTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectFolderTo.setBounds(203, 185, 473, 16);

		// Import | Export Buttons
		importButton = new JButton("Import");
		importButton.setBounds(79, 112, 86, 29);
		importButton.addActionListener(new ImportButtonListener(this));

		exportButton = new JButton("Export");
		exportButton.setBounds(79, 180, 85, 29);
		exportButton.addActionListener(new ExportButtonListener(this));
		
		// Cancel Button
		cancelProcessButton = new JButton("Cancel Process");
		cancelProcessButton.setBounds(394, 385, 264, 67);
		cancelProcessButton.addActionListener(new CancelProcessButton(this, processImagesButtonListener));
		
		textArea = new JTextArea(10, 50);
		//textArea.setBounds(67, 474, 609, 219);
		textArea.setVisible(true);
		textArea.setEditable(false);
		GroupLayout gl_mainMenu = new GroupLayout(mainMenu);
		gl_mainMenu.setHorizontalGroup(
			gl_mainMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainMenu.createSequentialGroup()
					.addContainerGap(115, Short.MAX_VALUE)
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.TRAILING)
						.addComponent(importButton)
						.addComponent(compressionRateLabel)
						.addComponent(exportButton))
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainMenu.createSequentialGroup()
							.addGap(28)
							.addComponent(rateSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(rateLabel))
						.addGroup(gl_mainMenu.createSequentialGroup()
							.addGap(114)
							.addComponent(lblSelectFolderTo))
						.addGroup(gl_mainMenu.createSequentialGroup()
							.addGap(95)
							.addComponent(importLabel)))
					.addGap(211))
				.addGroup(gl_mainMenu.createSequentialGroup()
					.addGap(131)
					.addComponent(processImagesButton, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(cancelProcessButton, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(Alignment.LEADING, gl_mainMenu.createSequentialGroup()
					.addGap(64)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_mainMenu.setVerticalGroup(
			gl_mainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainMenu.createSequentialGroup()
					.addGap(123)
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.BASELINE)
						.addComponent(importButton)
						.addComponent(importLabel))
					.addGap(69)
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectFolderTo)
						.addComponent(exportButton))
					.addGap(70)
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(compressionRateLabel)
						.addComponent(rateSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rateLabel))
					.addGap(49)
					.addGroup(gl_mainMenu.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelProcessButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(processImagesButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		mainMenu.setLayout(gl_mainMenu);
		
		// File Chooser
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		fileChooserDirectory = new JFileChooser();
		fileChooserDirectory.setMultiSelectionEnabled(false);
		fileChooserDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// Start the compression engine
		compressionEngine = new Compression(this);
	}
	
	// Mutator Access
	public JPanel getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(JPanel mainMenu) {
		this.mainMenu = mainMenu;
	}

	public JButton getProcessImagesButton() {
		return processImagesButton;
	}

	public void setProcessImagesButton(JButton processImagesButton) {
		this.processImagesButton = processImagesButton;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JSlider getRateSlider() {
		return rateSlider;
	}

	public void setRateSlider(JSlider rateSlider) {
		this.rateSlider = rateSlider;
	}

	public JLabel getCompressionRateLabel() {
		return compressionRateLabel;
	}

	public void setCompressionRateLabel(JLabel compressionRateLabel) {
		this.compressionRateLabel = compressionRateLabel;
	}

	public JLabel getRateLabel() {
		return rateLabel;
	}

	public void setRateLabel(JLabel rateLabel) {
		this.rateLabel = rateLabel;
	}

	public JLabel getImportLabel() {
		return importLabel;
	}

	public void setImportLabel(JLabel importLabel) {
		this.importLabel = importLabel;
	}

	public JLabel getLblSelectFolderTo() {
		return lblSelectFolderTo;
	}

	public void setLblSelectFolderTo(JLabel lblSelectFolderTo) {
		this.lblSelectFolderTo = lblSelectFolderTo;
	}

	public JButton getImportButton() {
		return importButton;
	}

	public void setImportButton(JButton importButton) {
		this.importButton = importButton;
	}

	public JButton getExportButton() {
		return exportButton;
	}

	public void setExportButton(JButton exportButton) {
		this.exportButton = exportButton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public JFileChooser getJFileChooser() {
		return fileChooser;
	}
	
	public JFileChooser getJFileChooserDirectory() {
		return fileChooserDirectory;
	}
	
	public Compression getCompressionEngine() {
		return compressionEngine;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
