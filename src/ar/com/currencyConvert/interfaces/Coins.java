package ar.com.currencyConvert.interfaces;

public enum Coins {
	ARG("Argentino", "ARS"),
	CO("Colombiano", "COP"),
	BRA("Brazilero", "BRL"),
	PE("Peruano", "PEN"), 
	USD("Estadounidense", "USD"),
	PAB("Panameño", "PAB"),
	PYG("Paraguayo", "PYG"),
	CPL("Chilena", "CPL");
	
	private final String label;
	private final String key;
	
    Coins(String label, String key) {
        this.label = label;
        this.key = key;
    }

    public String getLabel() {
        return label;
    }
    
    public String getKey() {
    	return key;
    }
}
