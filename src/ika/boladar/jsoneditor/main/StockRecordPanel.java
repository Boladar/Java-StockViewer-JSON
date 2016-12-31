package ika.boladar.jsoneditor.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class StockRecordPanel extends JPanel implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JLabel expirationDateLabel;
	
	private static String nameString = "Name: ";
	private static String priceString = "Price: ";
	private static String quantityString = "Quantity: ";
	private static String expirationDateString = "Date Of Expiration: ";
	
	private JFormattedTextField nameField;
	private JFormattedTextField priceField;
	private JFormattedTextField quantityField;
	private JFormattedTextField expirationDateField;
	
	private JFormattedTextField createAndSetupTextField(JFormattedTextField textField, NumberFormat format){
		textField = new JFormattedTextField(format);
		textField.setColumns(10);
		textField.addPropertyChangeListener("value", this);
		return textField;
	}
	
	public String getNameFieldText(){
		return nameField.getText();
	}
	
	public Double getPriceFieldText(){
		String priceFieldText = priceField.getText();
		String changed;		
		
		for(int i = 0; i < priceFieldText.length(); i++){
			char c = priceFieldText.charAt(i);
			if(c == ','){
				changed = priceFieldText.replace(',', '.');
				return Double.parseDouble(changed);
			}
		}	
		return Double.parseDouble(priceFieldText);
	}
	
	public String getQuantityFieldText(){
		return quantityField.getText();
	}
	
	public String getExpiraitonDateFieldText(){
		return expirationDateField.getText();
	}
	
	public StockRecordPanel(){
		
		nameLabel = new JLabel(nameString);
		priceLabel = new JLabel(priceString);
		quantityLabel = new JLabel(quantityString);
		expirationDateLabel = new JLabel(expirationDateString);
		
		nameField = createAndSetupTextField(nameField, null);		
		nameField.setValue(new String("Name"));
	
		//NumberFormat format = new DecimalFormat("######.00##");
		/*NumberFormat format = new DecimalFormat
		format.setRoundingMode(RoundingMode.HALF_UP);*/
		
		System.out.println("DefaultLocale: " + DecimalFormat.getInstance(getDefaultLocale()));
		NumberFormat priceFormat = DecimalFormat.getInstance(getDefaultLocale());
		
		
		priceField = createAndSetupTextField(priceField, priceFormat);
		priceField.setValue(new Double(10.54));
		
		
		quantityField = createAndSetupTextField(quantityField, null);
		quantityField.setValue(new Long(10));
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY", getDefaultLocale());
		System.out.println(dateFormat.getCalendar());
		//expirationDateField = createAndSetupTextField(expirationDateField, null);
		
		expirationDateField = new JFormattedTextField(dateFormat);
		expirationDateField.setColumns(10);
		expirationDateField.addPropertyChangeListener("value", this);
		expirationDateField.setValue( new Date());
		
		nameLabel.setLabelFor(nameField);
		priceLabel.setLabelFor(priceField);
		quantityLabel.setLabelFor(quantityField);
		expirationDateLabel.setLabelFor(expirationDateField);
		
		JPanel labelPanel = new JPanel(new GridLayout(0,1));
		labelPanel.add(nameLabel);
		labelPanel.add(priceLabel);
		labelPanel.add(quantityLabel);
		labelPanel.add(expirationDateLabel);
		
		JPanel fieldPanel = new JPanel(new GridLayout(0,1));
		fieldPanel.add(nameField);
		fieldPanel.add(priceField);
		fieldPanel.add(quantityField);
		fieldPanel.add(expirationDateField);
		
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		add(labelPanel,BorderLayout.LINE_START);
		add(fieldPanel, BorderLayout.LINE_END);
		
		
		System.out.println("CREATING STOCKRECORDPANEL");
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
