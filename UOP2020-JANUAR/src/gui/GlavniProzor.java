package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.FormeZaPrikaz.AdminProzor;
import gui.FormeZaPrikaz.AutomobilProzor;
import gui.FormeZaPrikaz.DeloviProzor;
import gui.FormeZaPrikaz.KnjiziceProzor;
import gui.FormeZaPrikaz.MusterijeProzor;
import gui.FormeZaPrikaz.ServiseriProzor;
import gui.FormeZaPrikaz.ServisiProzor;
import korisnici.Admin;
import servisAutomobila.ServisA;;

public class GlavniProzor extends JFrame{
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu servisMeni = new JMenu("Servis automobila");
	private JMenuItem autoItem = new JMenuItem("Automobili");
	private JMenuItem servisItem = new JMenuItem("Servisi");
	private JMenuItem deloviItem = new JMenuItem("Delovi");
	private JMenuItem knjiziceItem = new JMenuItem("Servisne Knjizice");
	
	private JMenu korisniciMenu = new JMenu("Korisnici");
	private JMenuItem adminItem = new JMenuItem("Admini");
	private JMenuItem musterijaItem = new JMenuItem("Musterije");
	private JMenuItem serviserItem = new JMenuItem("Serviseri");
	
	private ServisA citanje;
	private Admin prijavljenAdmin;
	
	public GlavniProzor(ServisA citanje,Admin prijavljenAdmin) {
		this.citanje = citanje;
		this.prijavljenAdmin = prijavljenAdmin;
		setTitle("Prijavljen korisnik: "+prijavljenAdmin.getIme()+" "+prijavljenAdmin.getPrezime());
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisMeni);
		mainMenu.add(korisniciMenu);
		servisMeni.add(autoItem);
		servisMeni.add(servisItem);
		servisMeni.add(deloviItem);
		servisMeni.add(knjiziceItem);
		korisniciMenu.add(adminItem);
		korisniciMenu.add(musterijaItem);
		korisniciMenu.add(serviserItem);
		
	}
	private void initActions() {
		musterijaItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MusterijeProzor mp = new MusterijeProzor(citanje);
				mp.setVisible(true);
			}
		} );
		adminItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminProzor ap = new AdminProzor(citanje);
				ap.setVisible(true);
				
			}
		});
		serviserItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiseriProzor sp = new ServiseriProzor(citanje);
				sp.setVisible(true);
				
			}
		});
		autoItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobilProzor ap = new AutomobilProzor(citanje);
				ap.setVisible(true);
				
			}
		});
		deloviItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeloviProzor dp = new DeloviProzor(citanje);
				dp.setVisible(true);
				
			}
		});
		servisItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisiProzor sp = new ServisiProzor(citanje);
				sp.setVisible(true);
				
			}
		});
		knjiziceItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjiziceProzor kp = new KnjiziceProzor(citanje);
				kp.setVisible(true);
				
			}
		});
	}

}
