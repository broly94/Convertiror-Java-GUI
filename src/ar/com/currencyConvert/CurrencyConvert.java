package ar.com.currencyConvert;

import ar.com.currencyConvert.GUI.App;
import ar.com.currencyConvert.interfaces.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class CurrencyConvert{

	public String access_token = "80e809405b7e435442990ecf96bdf947";

	public String currencyToCovert;
	
	public String conversionCurrency;
	
	public double amount;
	
	public double exchangeRate;
	
	public CurrencyConvert(String conversionCurrency, String currencyToConvert, double amount) {
		this.conversionCurrency = conversionCurrency;
		this.currencyToCovert = currencyToConvert;
		this.amount = amount;
	}
		
	public double exchangeRate(double currency, double toConvert) {
		return currency / toConvert;
	}
	
	public double HttpResponse() {
		try {
			
			String uriCurrency = "http://api.exchangeratesapi.io/v1/latest?access_key=" + this.access_token + "&symbols=" + this.conversionCurrency;
			String uriToConvert = "http://api.exchangeratesapi.io/v1/latest?access_key=" + this.access_token + "&symbols=" + this.currencyToCovert;
			
			// Se establece la conexion y se le pasa la url a consultar
			HttpURLConnection connectionCurrency = (HttpURLConnection) new URL(uriCurrency).openConnection();
			HttpURLConnection connectionToConvert = (HttpURLConnection) new URL(uriToConvert).openConnection();
			
			// Se le asigna un metodo a la cadena de conexion
			connectionCurrency.setRequestMethod("GET");
			connectionToConvert.setRequestMethod("GET");
			
			// Se crea un BufferReader que sirve para leer datos de una entrada, sea de un input o una conexion
			BufferedReader readerCurrency = new BufferedReader(new InputStreamReader(connectionCurrency.getInputStream()));
			BufferedReader readerToConvert = new BufferedReader(new InputStreamReader(connectionToConvert.getInputStream()));
			
			// Leemos el reader
			String responseCurrency = readerCurrency.readLine();
			String responseToConvert = readerToConvert.readLine();
			readerCurrency.close();
			readerToConvert.close();
			
			// Parseamos los datos obtenidos a un formato JSON
			JSONObject responseJsonCurrency = new JSONObject(responseCurrency);
			double quantityMoneyCurrency = (Double) responseJsonCurrency.getJSONObject("rates").get(this.conversionCurrency);
			
			JSONObject responseJsonToConvert = new JSONObject(responseToConvert);
			double quantityMoneyToConvert = (Double) responseJsonToConvert.getJSONObject("rates").get(this.currencyToCovert);
			
			this.exchangeRate = this.exchangeRate(quantityMoneyCurrency, quantityMoneyToConvert);

			return exchangeRate * this.amount;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return exchangeRate * this.amount;
	}

}
