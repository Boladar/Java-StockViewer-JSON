package ika.boladar.jsoneditor.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileManager {
	private FileFilter JSON_FILTER = new FileNameExtensionFilter("STOCK File","json");  
	private JFileChooser fileChooser = new JFileChooser();
	private JFrame parent;
	private TableModel tableModel;
	
	
	public FileManager(JFrame parent){
		this.parent = parent;
		setupFileChooser();
	}
	
	private void setupFileChooser(){
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//Disable all files option
		fileChooser.setAcceptAllFileFilterUsed(true);
		
		// Set JSON file Filter
		fileChooser.setFileFilter(JSON_FILTER);
	}
	
	public ArrayList<StockRecord> openFile(){
		ArrayList<StockRecord>data = new ArrayList<StockRecord>();
		
		if(fileChooser.showOpenDialog(parent) == JFileChooser .APPROVE_OPTION){
			System.out.println("getCurrentDirectory:" + fileChooser.getCurrentDirectory());
			System.out.println("getSelectedFile():" + fileChooser.getSelectedFile());
			
			JSONParser parser = new JSONParser();

			
			
				try {
					JSONArray array = (JSONArray) parser.parse(new FileReader(fileChooser.getSelectedFile()));
					
					for(Object obj : array){
						JSONObject record = (JSONObject) obj;
					
						String name = (String) record.get("name");							
						double price = (double) record.get("price");					
						long quantity = (long) record.get("quantity");
						Date expirationDate = (Date) record.get("expirationDate");
			
						StockRecord stockRecord = new StockRecord(name, price, quantity, expirationDate);
						data.add(stockRecord);
						
					}
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		else {
			System.out.println("No file selected");
		}
		return data;
	}
	
	public void save(){
		writeToFile(tableModel.getData());
	}
	
	public void writeToFile(ArrayList<StockRecord>data){
		
		
		if(fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION){
			
			JSONArray array = new JSONArray();
			
			for(StockRecord record : data){
				JSONObject obj = record.translateToJSONObject();
				record.printRecordValuables();
				array.add(obj);
				
			}	
			
			try {
				FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
				writer.write(array.toJSONString());
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	 
	}	
	public void setTableModel( TableModel tableModel){
		this.tableModel = tableModel;
	}
}
