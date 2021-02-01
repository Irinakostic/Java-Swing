package guiFromeZaDodavanjeiIzmene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Musterija;
import korisnici.Serviser;
import net.miginfocom.swing.MigLayout;
import servis.Automobil;
import servis.Deo;
import servis.ServisAutomobila;
import servis.ServisAutomobila.StatusServisa;
import servisAutomobila.ServisA;



public class MusterijaServisDodavanjeIzmene extends JFrame {

	private JLabel lblId = new JLabel("Id");
	private JTextField txtId = new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblAuto= new JLabel("Auto");
	private JComboBox<String>cbAuto= new JComboBox<String>();
	private JLabel lblServis= new JLabel("Servis");
	private JComboBox<String>cbServis= new JComboBox<String>();
	private JLabel lblServiser= new JLabel("Serviser");
	private JComboBox<String>cbServiser= new JComboBox<String>();
	private JLabel lblTermin = new JLabel("Termin");
	private JTextField txtTermin = new JTextField(20);
	private JLabel lblStatus = new JLabel("StatusServisa");
	private JComboBox<StatusServisa>cbStatus = new JComboBox<StatusServisa>();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisA citanje;
	private ServisAutomobila servis;
	private Musterija prijavljenaMusterija;
	
	public MusterijaServisDodavanjeIzmene( ServisA citanje,ServisAutomobila servis,Musterija prijavljenaMusterija) {
		this.citanje = citanje;
		this.servis = servis;
		this.prijavljenaMusterija = prijavljenaMusterija;
		if(servis == null) {
			setTitle("Dodavanje servisa");
		}else {
			setTitle("Izmena podataka - "+servis.getOpis());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2","[][]","[][][][]20[]");
		setLayout(layout);
		
		for (Automobil auto : citanje.sviNeobrisaniAutomobili()) {
				if(prijavljenaMusterija.getId().equals(auto.getVlasnik().getId())) {
					cbAuto.addItem(String.valueOf(auto.getId()));
				}
				
		}
		for (ServisAutomobila servis : citanje.sviNeobrisaniServisi()) {
			cbServis.addItem(String.valueOf(servis.getOpis()));
		}
		for (Serviser serviser : citanje.sviNeobrisaniServiseri()) {
			cbServiser.addItem(String.valueOf(serviser.getIme().concat(serviser.getPrezime())));
		}
		if(servis != null) {
			popuniPolja();
		}
		add(lblId);	
		add(txtId);
	
		add(lblAuto);
		add(cbAuto);
		add(lblServiser);
		add(cbServiser);
		add(txtTermin);
		add(lblTermin);
		add(lblStatus);
		add(cbStatus);
		add(lblOpis);	
		add(txtOpis);
		add(new JLabel());
		add(btnOk,"split 2");
		add(btnCancel);
		
	}
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(validacija()) {
						String id = txtId.getText().trim();
						String idAuto = cbAuto.getSelectedItem().toString();
						Automobil automobil = citanje.pronadjiAutomobil(idAuto);
						String serviserKo = cbServiser.getSelectedItem().toString();
						String serviserKo2 = citanje.nadjiImePrezimeServiseraId(serviserKo);
						Serviser serviser = citanje.pronadjiServisera(serviserKo2);
						int termin = Integer.parseInt(txtTermin.getText().trim());
						String opis = txtOpis.getText().trim();		
						StatusServisa status = (StatusServisa)cbStatus.getSelectedItem();
						
						ArrayList<Deo>listaDelova = new ArrayList<Deo>();
						
						if(servis == null) {
							ServisAutomobila novi = new ServisAutomobila(id, automobil, serviser, termin, opis, status, false);
							citanje.dodajServis(novi);
						}else {
							servis.setId(id);
							servis.setAutomobilSA(automobil);
							servis.setServiserSA(serviser);
							servis.setTermin(termin);
							servis.setOpis(opis);
							servis.setStatusServisa(status);
						}
						citanje.snimiServise();
						MusterijaServisDodavanjeIzmene.this.dispose();
						MusterijaServisDodavanjeIzmene.this.setVisible(false);
					}
				} catch (NumberFormatException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
	}
	private void popuniPolja() {
		txtOpis.setText(servis.getOpis());			
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		if(txtId.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}else if (servis == null) {
			String id = txtId.getText().trim();
			ServisAutomobila pronadjen = citanje.pronadjiServis(id);
			if(pronadjen != null) {
				poruka += "- Servis sa tim id-om vec postoji!\n";
				ok = false;
			}		
		}
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite opis\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
