package ika.boladar.jsoneditor.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

public class StockRecordDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private TableModel tableModel;
	private Displayer displayer;
	
	public StockRecordDialog(TableModel tableModel, Displayer displayer){
		this.tableModel = tableModel;
		this.displayer = displayer;
		
		setTitle("dialog");
	    setModalityType(ModalityType.APPLICATION_MODAL);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setSize(300, 200);
	    
	    Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
	    
		StockRecordPanel recordPanel = new StockRecordPanel();
		
	    add( recordPanel ,BorderLayout.NORTH);
	    add(new StockRecordButtonsPanel(this, recordPanel, tableModel, displayer), BorderLayout.SOUTH);
	    
	}

}
