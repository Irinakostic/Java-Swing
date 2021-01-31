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

import korisnici.Admin;
import net.miginfocom.swing.MigLayout;
import korisnici.Korisnik.Pol;
import servisAutomobila.ServisA;

public class AdminForma extends JFrame {
	
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
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisA citanje;
	private Admin admin;
	
	public AdminForma(ServisA citanje,Admin admin) {
		this.citanje = citanje;
		this.admin = admin;
		if(admin == null) {
			setTitle("Dodavanje admina");
		}else {
			setTitle("Izmena podataka - " + admin.getKorisnickoIme());
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
		
		if(admin != null) {
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
					
					
					if(admin == null) {
						Admin novi = new Admin(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, false, plata);
						citanje.dodajAdmina(novi);
					}else {
						admin.setId(id);
						admin.setIme(ime);
						admin.setPrezime(prezime);
						admin.setJMBG(jmbg);
						admin.setPol(pol);
						admin.setAdresa(adresa);
						admin.setBrMobilnog(telefon);
						admin.setKorisnickoIme(korisnickoIme);
						admin.setLozinka(lozinka);
						admin.setPlata(plata);
						
					}
					citanje.snimiAdmine();
					AdminForma.this.dispose();
					AdminForma.this.setVisible(false);
				}
				
			}
		});
	}
	private void popuniPolja() {
		txtId.setText(admin.getId());
		txtIme.setText(admin.getIme());
		txtPrezime.setText(admin.getPrezime());
		txtJmbg.setText(String.valueOf(admin.getJMBG()));
		cbPol.setSelectedItem(admin.getPol());
		txtAdresa.setText(admin.getAdresa());
		txtTelefon.setText(String.valueOf(admin.getBrMobilnog()));
		txtKorisnickoIme.setText(admin.getKorisnickoIme());
		pfLozinka.setText(admin.getLozinka());
		txtPlata.setText(Double.toString(admin.getPlata()));
		
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		if(txtId.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}else if (admin == null) {
			String id = txtId.getText().trim();
			Admin pronadjen = citanje.pronadjiAdmina(id);
			if(pronadjen != null) {
				poruka += "- Admin sa tim id-om vec postoji!\n";
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
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}else if(admin == null){
			String korIme = txtKorisnickoIme.getText().trim();
			Admin pronadjeni = citanje.nadjiAdmina(korIme);
			if(pronadjeni != null) {
				poruka += "- Admin sa tim korisnickim imenom vec postoji!\n";
				ok = false;
			}
		}

		if(txtPlata.getText().trim().equals("")) {
			poruka += "- Unesite platu\n";
			ok = false;
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
	
		return ok;
		
		
		
	}

}
