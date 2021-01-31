package guiFromeZaDodavanjeiIzmene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import korisnici.Musterija;
import net.miginfocom.swing.MigLayout;
import servis.Automobil;
import servis.Automobil.VrstaGoriva;
import servis.Marka;
import servis.Model;
import servisAutomobila.ServisA;


public class AutomobilForma extends JFrame {
	
	private JLabel lblId = new JLabel("Id");
	private JTextField txtId = new JTextField(20);
	private JLabel lblVlasnik= new JLabel("Vlasnik");
	private JComboBox<String>cbVlasnik= new JComboBox<String>();
	private JLabel lblMarka = new JLabel("Marka");
	private JComboBox<Marka>cbMarka = new JComboBox<Marka>(Marka.values());
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<Model>cbModel = new JComboBox<Model>(Model.values());
	private JLabel lblGodProizvodnje = new JLabel("Godina proizvodnje");
	private JTextField txtGodProizvodnje = new JTextField(20);
	private JLabel lblZapremina = new JLabel("Zapremina");
	private JTextField txtZapremina= new JTextField(20);
	private JLabel lblSnaga= new JLabel("Snaga");
	private JTextField txtSnaga = new JTextField(20);
	private JLabel lblVrstaGoriva = new JLabel("VrstaGoriva");
	private JComboBox<VrstaGoriva>cbVrstaGoriva = new JComboBox<VrstaGoriva>(VrstaGoriva.values());
	
	


	
	

	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisA citanje;
	private Automobil auto;
	private Musterija vlasnik;
	
	public AutomobilForma(ServisA citanje,Automobil auto) {
		this.citanje = citanje;
		this.auto = auto;
		if(auto == null) {
			setTitle("Dodavanje automobila");
		}else {
			setTitle("Izmena podataka - "+auto.getMarka()+" "+auto.getModel());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2","[][]","[][][][][][][]20[]");
		setLayout(layout);
		for (Musterija musterija : citanje.sveNeobrisaneMusterije()) {
			cbVlasnik.addItem(String.valueOf(musterija.getIme().concat(musterija.getPrezime())));
		}
		
		
		
		
		if(auto != null) {
			popuniPolja();
		}
		
		add(lblId);
		add(txtId);
		add(lblVlasnik);
		add(cbVlasnik);
		add(lblSnaga);
		add(txtSnaga);
		add(lblGodProizvodnje);
		add(txtGodProizvodnje);
		add(lblZapremina);	
		add(txtZapremina);
		add(lblVrstaGoriva);
		add(cbVrstaGoriva);
		add(lblMarka);
		add(cbMarka);
		add(lblModel);
		add(cbModel);
	
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
						String idVlasnika = cbVlasnik.getSelectedItem().toString();
						String idVlasnika2 = citanje.vlasnikImePrezime(idVlasnika);
						Musterija vlasnik = citanje.pronadjiMusteriju(idVlasnika2);
						Marka marka = (Marka)cbMarka.getSelectedItem();
						Model model = (Model)cbModel.getSelectedItem();
						int godiste = Integer.parseInt(txtGodProizvodnje.getText().trim());
						int snaga = Integer.parseInt(txtSnaga.getText().trim());
						int zapremina = Integer.parseInt(txtZapremina.getText().trim());
						VrstaGoriva gorivo = (VrstaGoriva)cbVrstaGoriva.getSelectedItem();
						
						
						if(auto == null) {
							Automobil novi = new Automobil(id, vlasnik, marka, model, godiste, snaga, zapremina, gorivo, false);
							citanje.dodajAutomobil(novi);
						}else {
							auto.setId(id);
							auto.setVlasnik(vlasnik);
							auto.setMarka(marka);
							auto.setModel(model);
							auto.setGodProizvodnje(godiste);
							auto.setSnagaMotora(snaga);
							auto.setZapreminaMotora(zapremina);
							auto.setVrstaGoriva(gorivo);
							
							
							
						}
						citanje.snimiAutobobile();
						AutomobilForma.this.dispose();
						AutomobilForma.this.setVisible(false);
					}
				} catch (NumberFormatException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
	}
	private void popuniPolja() {
		
		txtId.setText(auto.getId());
		Musterija musterija = citanje.pronadjiVlasnikaA(auto);
		cbVlasnik.setSelectedItem(String.valueOf(musterija.getId()));
		cbMarka.setSelectedItem(auto.getMarka());
		cbModel.setSelectedItem(auto.getModel());
		txtGodProizvodnje.setText(String.valueOf(auto.getGodProizvodnje())); 
		txtSnaga.setText(String.valueOf(auto.getSnagaMotora()));
		txtZapremina.setText(String.valueOf(auto.getZapreminaMotora()));
		cbVrstaGoriva.setSelectedItem(auto.getVrstaGoriva());
	
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		if(txtId.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}else if (auto == null) {
			String id = txtId.getText().trim();
			Automobil pronadjen = citanje.pronadjiAutomobil(id);
			if(pronadjen != null) {
				poruka += "- Auto sa tim id-om vec postoji!\n";
				ok = false;
			}
			
		}
	
		if(txtGodProizvodnje.getText().trim().equals("")) {
			poruka += "- Unesite godiste\n";
			ok = false;
	
		}
		if(txtSnaga.getText().trim().equals("")) {
			poruka += "- Unesite snagu\n";
			ok = false;
		}
		if(txtZapremina.getText().trim().equals("")) {
			poruka += "- Unesite zapreminu\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
		
		
		
	
	}

}
