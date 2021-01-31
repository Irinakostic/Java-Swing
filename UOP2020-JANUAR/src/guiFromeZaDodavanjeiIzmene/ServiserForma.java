package guiFromeZaDodavanjeiIzmene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import korisnici.Korisnik.Pol;
import korisnici.Serviser;
import korisnici.Serviser.Specijalizacija;
import net.miginfocom.swing.MigLayout;
import servisAutomobila.ServisA;


public class ServiserForma extends JFrame {
	private JLabel lblId = new JLabel("Id");
	private JTextField txtId = new JTextField(20);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJmbg = new JLabel("JMBG");
	private JTextField txtJmbg = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol>cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTel = new JLabel("Telefon");
	private JTextField txtTelefon = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JComboBox<Specijalizacija>cbSpecijalizacija = new JComboBox<Specijalizacija>(Specijalizacija.values());
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisA citanje;
	private Serviser serviser;
	
	public ServiserForma(ServisA citanje,Serviser serviser) {
		this.citanje = citanje;
		this.serviser = serviser;
		if(serviser == null) {
			setTitle("Dodavanje servisera");
		}else {
			setTitle("Izmena podataka - "+serviser.getKorisnickoIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2","[][]","[][][][][][][][][]20[]");
		setLayout(layout);
		
		if(serviser != null) {
			popuniPolja();
		}
		add(lblId);
		add(txtId);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblJmbg);
		add(txtJmbg);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrojTel);
		add(txtTelefon);
		add(lblPlata);
		add(txtPlata);
		add(lblSpecijalizacija);
		add(cbSpecijalizacija);
		add(lblPol);
		add(cbPol);
		add(new JLabel());
		add(btnOk,"split 2");
		add(btnCancel);
		
	}
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtId.getText().trim();
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					int jmbg = Integer.parseInt(txtJmbg.getText().trim());
					Pol pol = (Pol)cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					int telefon = Integer.parseInt(txtTelefon.getText().trim());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Float plata = Float.parseFloat(txtPlata.getText().trim());
					Specijalizacija specijalizacija = (Specijalizacija)cbSpecijalizacija.getSelectedItem();
					
					
					if(serviser == null) {
						Serviser novi = new Serviser(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, false,plata, specijalizacija);
						citanje.dodajServisera(novi);
					}else {
						serviser.setId(id);
						serviser.setIme(ime);
						serviser.setPrezime(prezime);
						serviser.setJMBG(jmbg);
						serviser.setPol(pol);
						serviser.setAdresa(adresa);
						serviser.setBrMobilnog(telefon);
						serviser.setKorisnickoIme(korisnickoIme);
						serviser.setLozinka(lozinka);
						serviser.setPlata(plata);
						serviser.setSpecijalizacija(specijalizacija);
						
					}
					citanje.snimiServisere();
					ServiserForma.this.dispose();
					ServiserForma.this.setVisible(false);
				}
				
			}
		});
	}
	private void popuniPolja() {
		txtId.setText(serviser.getId());
		txtIme.setText(serviser.getIme());
		txtPrezime.setText(serviser.getPrezime());
		txtJmbg.setText(String.valueOf(serviser.getJMBG()));
		cbPol.setSelectedItem(serviser.getPol());
		txtAdresa.setText(serviser.getAdresa());
		txtTelefon.setText(String.valueOf(serviser.getBrMobilnog()));
		txtKorisnickoIme.setText(serviser.getKorisnickoIme());
		pfLozinka.setText(serviser.getLozinka());
		txtPlata.setText(Double.toString(serviser.getPlata()));
		cbSpecijalizacija.setSelectedItem(serviser.getSpecijalizacija());
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		if(txtId.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}else if (serviser == null) {
			String id = txtId.getText().trim();
			Serviser pronadjen = citanje.pronadjiServisera(id);
			if(pronadjen != null) {
				poruka += "- Serviser sa tim id-om vec postoji!\n";
				ok = false;
			}		
		}

		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}else if(serviser == null){
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Serviser pronadjeni = citanje.nadjiServisera(korisnickoIme);
			if(pronadjeni != null) {
				poruka += "- Serviser sa tim korisnickim imenom vec postoji!\n";
				ok = false;
			}
		}
		if(txtJmbg.getText().trim().equals("")) {
			poruka += "- Unesite jmbg\n";
			ok = false;
		}
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}
		if(txtTelefon.getText().trim().equals("")) {
			poruka += "- Unesite telefon\n";
			ok = false;
		}
		if(txtPlata.getText().trim().equals("")) {
			poruka += "- Unesite platu\n";
			ok = false;
		}
		String sifra = new String(pfLozinka.getPassword()).trim();
		if(sifra.equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
		
		
	}

}
