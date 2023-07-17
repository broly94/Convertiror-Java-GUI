package ar.com.currencyConvert.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.currencyConvert.interfaces.Coins;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class App extends JFrame {

	private JPanel contentPane;
	public  JTextField txtMonto = new JTextField();
	private JComboBox comboOf = new JComboBox();
	private JComboBox comboTo = new JComboBox();
	public  JButton btnConvertir = new JButton("Convertir");
	public  String firstCountryOf;
	public  String firstCountryTo;
	private String termOf = null;
	private String termTo = null;
	public ArrayList<String> terms = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 207);
		this.setLocationRelativeTo(null);
		this.initialize();
	}
	
	public void initialize() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboOf.setBounds(10, 32, 152, 32);
		contentPane.add(comboOf);
		
		
		comboTo.setBounds(297, 32, 152, 32);
		contentPane.add(comboTo);
		
		this.addItemsCombo();
		this.selectItemOf();
		this.selectItemTo();
		
		JLabel lblNewLabel_1 = new JLabel("Convertir a...");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_1.setBounds(172, 35, 117, 23);
		contentPane.add(lblNewLabel_1);
		
		txtMonto.setHorizontalAlignment(SwingConstants.CENTER);
		txtMonto.setFont(new Font("Verdana", Font.BOLD, 14));
		txtMonto.setBounds(172, 92, 117, 32);
		contentPane.add(txtMonto);
		txtMonto.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese el monto");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 14));
		lblNewLabel_2.setBounds(10, 97, 152, 23);
		contentPane.add(lblNewLabel_2);
		
		//Button action
		btnConvertir.setFont(new Font("Verdana", Font.BOLD, 12));
		btnConvertir.setBounds(319, 93, 101, 32);
		contentPane.add(btnConvertir);
		
		JLabel lblNewLabel = new JLabel("Peso");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(21, 12, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Peso");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_3.setBounds(304, 12, 46, 14);
		contentPane.add(lblNewLabel_3);
	}
	
	public String convertArg(String term) {
		for(Coins coin: Coins.values()) {
			if(coin.getLabel().equals(term)) {
				return coin.getKey();
			}
		}
		return "no se encontro el termino";
	}
		
	public void clickConvert() {
		terms.removeAll(terms);
		
		if(this.termOf != null) {
			terms.add(this.convertArg(this.termOf));
		} else {
			terms.add(this.convertArg(this.firstCountryOf));
		}
		
		if(this.termTo != null) {
			terms.add(this.convertArg(this.termTo));
		} else {
			terms.add(convertArg(this.firstCountryTo));
		}
	}
	
	public void addItemsCombo() {
		for (Coins coin : Coins.values()) {
	        comboOf.addItem(coin.getLabel());
	    }

		this.firstCountryOf = comboOf.getItemAt(0).toString();

	    for (Coins coin : Coins.values()) {
	        if (!coin.getLabel().equals(firstCountryOf)) {
	            comboTo.addItem(coin.getLabel());
	        }
	    }
	    
	    this.firstCountryTo = comboTo.getItemAt(0).toString();
	}
	
	public void selectItemTo() {
		this.comboTo.addActionListener(e -> {
			String selected = (String) this.comboTo.getSelectedItem();
			this.termTo = selected;
			this.convertArg(this.termTo);
		});
	}
	
	public void selectItemOf() {
		
		final String[] previousSelected = {null};
		
	    comboOf.addActionListener( e -> {
	    	
	        String selected = (String) this.comboOf.getSelectedItem();
	   	        
	        if(selected == firstCountryOf) {
	        	comboTo.removeItem(firstCountryOf);
	        } else {
	        	comboTo.addItem(firstCountryOf);
	        }
	       
	        
	        if (selected != null) {
	            if (previousSelected[0] != null) {
	                comboTo.addItem(previousSelected[0]);
	            }
	            comboTo.removeItem(selected);
	            previousSelected[0] = selected;
	            
	        }
	        
	        this.quitarDuplicados(comboTo);
	        
	        this.termOf = selected;
	        
	        this.convertArg(this.termOf);
	        
	    });
	    
	}
	
	public boolean quitarDuplicados(JComboBox comboBox) {
	    for (int i = 0; i < comboBox.getItemCount(); i++) {
	        Object item = comboBox.getItemAt(i);
	        for (int j = i + 1; j < comboBox.getItemCount(); j++) {
	            if (item.equals(comboBox.getItemAt(j))) {
	            	comboBox.removeItem(comboBox.getItemAt(j));
	                return true; // Se encontró un elemento duplicado
	            }
	        }
	    }
	    return false; // No se encontraron elementos duplicados
	}
	
	public ArrayList<String> getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms.add(terms);
	}
}
