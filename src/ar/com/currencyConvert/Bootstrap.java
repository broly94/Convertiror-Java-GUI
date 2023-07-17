package ar.com.currencyConvert;

import ar.com.currencyConvert.interfaces.Coins;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import ar.com.currencyConvert.CountryInformation;
import ar.com.currencyConvert.GUI.App;

public class Bootstrap {
		
	public static void main(String[] args) {
		
		App app = new App();
		app.setVisible(true);
		
		app.btnConvertir.addActionListener(arg0 -> {
			System.out.println("Convert");
			if(app.txtMonto.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Ingrese un monto para convertir", "¡Error!", JOptionPane.ERROR_MESSAGE);
			} else {
				app.clickConvert();
				double monto = Double.parseDouble(app.txtMonto.getText());
				CurrencyConvert currencyConvert = new CurrencyConvert(app.terms.get(1).toString(), app.terms.get(0).toString(), monto);
				DecimalFormat df = new DecimalFormat("#.00");
				if(Math.round(currencyConvert.HttpResponse() * 100) / 100 == 0) {
					JOptionPane.showMessageDialog(null, app.terms.get(0) + " $" +  monto + " = " + app.terms.get(1).toString() + " $ 0" + df.format(currencyConvert.HttpResponse()), "¡Conversión exitosa!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, app.terms.get(0) + " $" +  monto + " = " + app.terms.get(1).toString() + " $" + df.format(currencyConvert.HttpResponse()), "¡Conversión exitosa!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		
		
		
		
		
		
	}
}
