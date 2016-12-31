package ika.boladar.jsoneditor.main;

import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private ArrayList<StockRecord>data = new ArrayList<StockRecord>();
	
	public TableModel(ArrayList<StockRecord>data){
		this.data = data;
	}
	
	public TableModel(){
		
	}
	
	private String[] columnName = {
			"Name",
			"Price",
			"Quantity",
			"Expiration Date",		
	};
	
	
	
	@Override
	public boolean isCellEditable(int row , int column) {
		// TODO Auto-generated method stub
		if(row  == 1 ){
			return false;
		}else
			return true;
	}

	@Override
	public String getColumnName(int column){
		return columnName[column];
		
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int row , int column) {
		// TODO Auto-generated method stub
		Object atribute = null;
		StockRecord record = data.get(row);
		switch(column){
		case 0: atribute = record.getName(); break;
		case 1: atribute = record.getPrice(); break;
		case 2: atribute = record.getQuantity(); break;
		case 3: atribute = record.getExpirationDate(); break;
		}
		return atribute;
	}
	
	public StockRecord getRecordAt(int row){
		return data.get(row);
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		StockRecord record = data.get(row);
		DateFormat format = new SimpleDateFormat("dd.MM.YYYY");
		switch(col){
		case 0: record.setName((String) value);; break;
		case 1: record.setPrice(Double.parseDouble((String) value)); break;
		case 2: record.setQuantity(Integer.parseInt((String) value)); break;
		case 3: if((String)value != null){
					try {
						record.setExpirationDate(format.parse((String) value) );
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		//fireTableCellUpdated(row, col); //nie wiem po co to ma tutaj byc
	}
	
	public ArrayList<StockRecord> getData(){
		return data;
	}
	
	public void addRecord(String name, double price, long quantity, Date expirationDate){
		StockRecord record = new StockRecord(name, price, quantity, expirationDate);
		this.data.add(record);
	}

}
