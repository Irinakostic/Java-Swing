package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import korisnici.Admin;
import korisnici.Musterija;
import korisnici.Serviser;
import net.miginfocom.swing.MigLayout;
import servisAutomobila.ServisA;;

public class Login extends JFrame {
	
	private JLabel lblDobroDosli = new JLabel("Dobrodosli! Molimo vas prijavite se.");
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JButton btnAdmin = new JButton("Admin prijava");
	private JButton btnServiser = new JButton("Serviser prijava");
	private JButton btnMusterija = new JButton("Musterija prijava");
	private JButton btnIzlaz = new JButton("Izlaz");
	
	private ServisA citanje;
	
	public Login(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Login");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Object[]option = {"Da","Ne","Nazad"};
				Login prijava = new Login(citanje);
				int izbor = JOptionPane.showOptionDialog(prijava, "Da li zelite da izadjete?", "Izlaz",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option,option[0]);
				if(izbor==JOptionPane.YES_OPTION) {
					Login.this.setVisible(false);
					Login.this.dispose();
				}
			}
			
		});
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
		setLayout(mig);
		add(lblDobroDosli,"span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnAdmin,"split 2");
		add(btnServiser);
		add(new JLabel());
		add(btnMusterija,"split 2");
		add(btnIzlaz);
		
		
	}
	public void initActions() {
		btnIzlaz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Login.this.dispose();
				Login.this.setVisible(false);
			}
		});
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfLozinka.getPassword()).trim();
				
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu", "Greska",JOptionPane.WARNING_MESSAGE);
					}else{
						Admin prijavljenA = citanje.loginA(korisnickoIme, sifra);
						
						if(prijavljenA == null) {
							JOptionPane.showMessageDialog(null, "Pogresni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
						}else{
							Login.this.dispose();
							Login.this.setVisible(false);
							GlavniProzor gp = new GlavniProzor(citanje, prijavljenA);
							gp.setVisible(true);
						}
					}
			};
		});
		btnMusterija.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfLozinka.getPassword()).trim();
				
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu", "Greska",JOptionPane.WARNING_MESSAGE);
					}else{
						Musterija prijavljenM = citanje.loginM(korisnickoIme, sifra);
						
						if(prijavljenM == null) {
							JOptionPane.showMessageDialog(null, "Pogresni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
						}else{
							Login.this.dispose();
							Login.this.setVisible(false);
							MusterijaPocetna mp = new MusterijaPocetna(citanje, prijavljenM);
							mp.setVisible(true);
						}
					}
			};
		});
		btnServiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfLozinka.getPassword()).trim();
				
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu", "Greska",JOptionPane.WARNING_MESSAGE);
					}else{
						Serviser prijavljenS = citanje.loginS(korisnickoIme, sifra);
						
						if(prijavljenS == null) {
							JOptionPane.showMessageDialog(null, "Pogresni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
						}else{
							Login.this.dispose();
							Login.this.setVisible(false);
							ServiserPocetna sp = new ServiserPocetna(citanje, prijavljenS);
							sp.setVisible(true);
						}
					}
			};
		});
		
	}

}
