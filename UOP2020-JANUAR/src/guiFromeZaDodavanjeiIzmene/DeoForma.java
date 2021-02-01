package guiFromeZaDodavanjeiIzmene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import servis.Deo;
import servis.Marka;
import servis.Model;
import servis.ServisAutomobila;
import servisAutomobila.ServisA;

public class DeoForma extends JFrame {
	
	private JLabel lblId = new JLabel("Id");
	private JTextField txtId = new JTextField(20);
	private JLabel lblMarka = new JLabel("Marka");
	private JComboBox<Marka>cbMarka = new JComboBox<Marka>(Marka.values());
	private JLabel lblModel = new JLabel("Model");
	private JComboBox<Model>cbModel = new JComboBox<Model>(Model.values());
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblCena = new JLabel("Cena");
	private JTextField txtCena = new JTextField(20);

	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private ServisA citanje;
	private Deo deo;
	
	public DeoForma(ServisA citanje,Deo deo) {
		this.citanje = citanje;
		this.deo = deo;
		if(deo == null) {
			setTitle("Dodavanje delova");
		}else {
			setTitle("Izmena podataka - "+deo.getNazivDela());
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
		
		
		
		
		if(deo != null) {
			popuniPolja();
		}
		
		add(lblId);
		add(txtId);
		add(lblNaziv);
		add(txtNaziv);
		
		add(lblMarka);
		add(cbMarka);
		add(lblModel);
		add(cbModel);
		add(lblCena);	
		add(txtCena);
	
	
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
						String naziv = txtNaziv.getText().trim();
						Marka marka = (Marka)cbMarka.getSelectedItem();
						Model model = (Model)cbModel.getSelectedItem();
						Double cena = Double.parseDouble(txtCena.getText().trim());
					
						if(deo == null) {
							Deo novi = new Deo(id, marka, model, naziv, cena, false, new ArrayList<ServisAutomobila>());
							citanje.dodajDeo(novi);
						}else {
							deo.setId(id);
							deo.setMarka(marka);
							deo.setModel(model);
							deo.setNazivDela(naziv);
							deo.setCena(cena);		
						}
						citanje.snimiDelove();
						DeoForma.this.dispose();
						DeoForma.this.setVisible(false);
					}
				} catch (NumberFormatException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
	}
	private void popuniPolja() {
		txtId.setText(deo.getId());	
		cbMarka.setSelectedItem(deo.getMarka());
		cbModel.setSelectedItem(deo.getModel());
		txtNaziv.setText(deo.getNazivDela());
		txtCena.setText(String.valueOf(deo.getCena())); 
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite greske u unosu:\n";
		
		if(txtId.getText().trim().equals("")) {
			poruka += "- Unesite id\n";
			ok = false;
		}else if (deo != null) {
			String id = txtId.getText().trim();
			Deo pronadjen = citanje.pronadjiDeo(id);
			if(pronadjen != null) {
				poruka += "- Deo sa tim id-om vec postoji!\n";
				ok = false;
			}		
		}
		if(txtCena.getText().trim().equals("")) {
			poruka += "- Unesite cenu\n";
			ok = false;
	
		}
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Unesite naziv\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
