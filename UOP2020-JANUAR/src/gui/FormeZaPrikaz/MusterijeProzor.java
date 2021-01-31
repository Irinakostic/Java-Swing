package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import guiFromeZaDodavanjeiIzmene.MusterijaForma;
import korisnici.Musterija;
import servisAutomobila.ServisA;


public class MusterijeProzor extends JFrame {
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable musterijeTabela;
	private ServisA citanje;
	
	public MusterijeProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Musterije");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions(); 
	}
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		add(mainToolBar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[]{"Id","Ime","Prezime","JMBG","Pol","Adresa","Broj telefona","Korisnicko ime","Lozinka","Bodovi"};
		
		Object[][] sadrzaj = new Object[citanje.sveNeobrisaneMusterije().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sveNeobrisaneMusterije().size();i++) {
			Musterija musterija = citanje.sveNeobrisaneMusterije().get(i);
			sadrzaj[i][0] = musterija.getId();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJMBG();
			sadrzaj[i][4] = musterija.getPol();
			sadrzaj[i][5] = musterija.getAdresa();
			sadrzaj[i][6] = musterija.getBrMobilnog();
			sadrzaj[i][7] = musterija.getKorisnickoIme();
			sadrzaj[i][8] = musterija.getLozinka();
			sadrzaj[i][9] = musterija.getBrSakupljenihBodova();
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		musterijeTabela = new JTable(tableModel);
		
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		musterijeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(musterijeTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Musterija musterija = citanje.nadjiMusteriju(korisnickoIme);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete musteriju?", korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						musterija.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiMusterije();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MusterijaForma mf = new MusterijaForma(citanje, null);
				mf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Musterija musterija = citanje.nadjiMusteriju(korisnickoIme);
					if(musterija == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje musterije sa tim korisickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						MusterijaForma mf = new MusterijaForma(citanje, musterija);
						mf.setVisible(true);
					}
				}
			}
		});
	}

}
