package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import korisnici.Serviser;
import servisAutomobila.ServisA;

public class ServiserPocetna extends JFrame {
	
private JMenuBar mainMenu = new JMenuBar();
	
	private ServisA citanje;
	private Serviser prijavljeniServiser;
	
	
	public ServiserPocetna(ServisA citanje,Serviser prijavljeniServiser) {
		this.citanje = citanje;
		this.prijavljeniServiser = prijavljeniServiser;
		setTitle("Prijavljen korisnik: "+prijavljeniServiser.getIme()+" "+prijavljeniServiser.getPrezime());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initMenu();
		initActions();
	}
	private void initActions() {
		
		
	}
	public void initMenu() {
		setJMenuBar(mainMenu);
	}
	

}
