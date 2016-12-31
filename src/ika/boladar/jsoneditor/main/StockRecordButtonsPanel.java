package ika.boladar.jsoneditor.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class StockRecordButtonsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JDialog recordDialog;
	private StockRecordPanel recordPanel;
	private Displayer displayer;
	private TableModel tableModel;
	private JButton buttonCreate;
	private JButton buttonCancel;
	
	
	public StockRecordButtonsPanel(JDialog StockRecordDialog, StockRecordPanel recordPanel, TableModel tableModel, Displayer displayer){
		this.recordDialog = StockRecordDialog;
		this.recordPanel = recordPanel;
		this.tableModel = tableModel;
		this.displayer = displayer;
		buttonCreate = new JButton();
		buttonCancel = new JButton();
		
		add(buttonCreate, BorderLayout.WEST);
		add(buttonCancel,BorderLayout.EAST);
				
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				recordDialog.setVisible(false);
			}
		});
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("get price field: " + recordPanel.getPriceFieldText());
				System.out.println("get price field(casted to double):" + recordPanel.getPriceFieldText());
				
				tableModel.addRecord(recordPanel.getNameFieldText(),recordPanel.getPriceFieldText(),
						 Long.parseLong(recordPanel.getQuantityFieldText()),null);
				//update the table 
				displayer.updateTableContent();
			}
		});
	}
	
}
