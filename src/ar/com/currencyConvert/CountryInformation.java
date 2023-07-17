package ar.com.currencyConvert;

import ar.com.currencyConvert.interfaces.Coins;

public class CountryInformation {
	
	private static Coins[] values = Coins.values();
	
    private static String[] coinValues = new String[values.length];
    
    
	public static String[] returnValues() {
		try {
			
			for (int i = 0; i < values.length; i++) {
				coinValues[i] = "De Euro a peso " + values[i].getLabel();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coinValues;
	}
}
