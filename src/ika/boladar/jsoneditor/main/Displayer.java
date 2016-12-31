package ika.boladar.jsoneditor.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Displayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private FileManager fileManager = new FileManager(this);
	private TableModel tableModel;

	private void CreateWindow() {
		this.setTitle("JSON EDITOR");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	private void createTable(ArrayList<StockRecord> tableData) {

		JTable table = new JTable( tableModel = new TableModel(tableData));
		fileManager.setTableModel(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		System.out.println("CREATING JTABLE");

		this.add(scrollPane);
		updateTableContent();
	}
	
	public void updateTableContent(){
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private JMenu createFileMenuBar() {
		JMenu fileMenuBar = new JMenu("File");
		fileMenuBar.setMnemonic(KeyEvent.VK_F);

		JMenuItem fileNew = new JMenuItem("New", null); // JmenuItem(<"name">,<icon>);
		JMenuItem fileOpen = new JMenuItem("Open", null);
		JMenuItem fileClose = new JMenuItem("Close", null);
		JMenuItem fileSave = new JMenuItem("Save", null);

		//////////////////////////////////////////////////

		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit application");

		fileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		/////////////////////////////////////////////////////////

		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.setToolTipText("Open FIle");

		fileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				createTable(fileManager.openFile());
			}
		});

		///////////////////////////////////////////////////////

		fileNew.setMnemonic(KeyEvent.VK_N);
		fileNew.setToolTipText("New File");

		fileNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				System.out.println("NEW FILE __ TO DO");
				
				createTable(new ArrayList<StockRecord>());
				
				/*JSONArray array = new JSONArray();
				
				JSONObject obj = new JSONObject();
				obj.put("name", "pierogi");
				obj.put("price", new Double(34.5));
				obj.put("quantity", new Long(40));
				obj.put("expirationDate", null);
				
				array.add(obj);
				
				try {
					FileWriter writer = new FileWriter("test.json");
					writer.write(array.toJSONString());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/	
			}
		});

		//////////////////////////////////////////////////////

		fileSave.setMnemonic(KeyEvent.VK_S);
		fileSave.setToolTipText("Save File");

		fileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileManager.save();
			}
		});

		////////////////////////////////////////////////////

		fileMenuBar.add(fileNew);
		fileMenuBar.add(fileSave);
		fileMenuBar.add(fileOpen);
		fileMenuBar.add(fileClose);

		return fileMenuBar;
	}

	private void menuHandler() {
		JMenuBar menuBar = new JMenuBar();
		// ImageIcon icon = new ImageIcon("");

		menuBar.add(createFileMenuBar());
		setJMenuBar(menuBar);
	}

	public Displayer getDisplayerClass(){
		return this;
	}
	
	public Displayer() {
		CreateWindow();
		menuHandler();
		
		JToolBar horizontalToolbar = new JToolBar();
		
		java.net.URL imageURL = this.getClass().getClassLoader().getResource("Icons/newRecord.png");
		ImageIcon createRecordIcon = new ImageIcon(imageURL);
		
		JButton createRecord = new JButton(createRecordIcon);
		horizontalToolbar.add(createRecord);
		
		 createRecord.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	            	
	            	//JTextField test = new JTextField();
	            	
	            	updateTableContent();
	       		 	
	            	StockRecordDialog srd = new StockRecordDialog(tableModel,getDisplayerClass() );
	            	srd.setVisible(true);
	            }
	        });
	 
		 add(horizontalToolbar,	BorderLayout.NORTH);

	}
	
}
