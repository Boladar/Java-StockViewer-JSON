package ika.boladar.jsoneditor.main;

import java.util.Date;

import org.json.simple.JSONObject;

public class StockRecord {
	

	private String name;
	private double price;
	private long quantity;
	private Date expirationDate;
	
	public StockRecord(String name, double price, long quantity, Date expirationDate){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
	}
	
	public StockRecord(String name, double price, long quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public JSONObject translateToJSONObject(){
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("price", price);
		obj.put("quantity", quantity);
		obj.put("expirationDate", expirationDate);
		
		return obj;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public void printRecordValuables(){
		System.out.println(name);
		System.out.println(quantity);
		System.out.println(price);
		System.out.println(expirationDate);
	}
	
}
