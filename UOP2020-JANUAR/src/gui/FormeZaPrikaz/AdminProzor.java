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

import guiFromeZaDodavanjeiIzmene.AdminForma;
import korisnici.Admin;
import servisAutomobila.ServisA;

public class AdminProzor extends JFrame {
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable adminTabela;
	private ServisA citanje;
	
	public AdminProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Admini");
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
		
		String[] zaglavlja = new String[]{"Id","Ime","Prezime","JMBG","Pol","Adresa","Broj telefona","Korisnicko ime","Lozinka","Plata"};
		Object[][] sadrzaj = new Object[citanje.sviNeobrisaniAdmini().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sviNeobrisaniAdmini().size();i++) {
			Admin admin = citanje.sviNeobrisaniAdmini().get(i);
			sadrzaj[i][0] = admin.getId();
			sadrzaj[i][1] = admin.getIme();
			sadrzaj[i][2] = admin.getPrezime();
			sadrzaj[i][3] = admin.getJMBG();
			sadrzaj[i][4] = admin.getPol();
			sadrzaj[i][5] = admin.getAdresa();
			sadrzaj[i][6] = admin.getBrMobilnog();
			sadrzaj[i][7] = admin.getKorisnickoIme();
			sadrzaj[i][8] = admin.getLozinka();
			sadrzaj[i][9] = admin.getPlata();
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		adminTabela = new JTable(tableModel);
		
		adminTabela.setRowSelectionAllowed(true);
		adminTabela.setColumnSelectionAllowed(false);
		adminTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminTabela.setDefaultEditor(Object.class, null);
		adminTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Admin admin = citanje.nadjiAdmina(korisnickoIme);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete admina?", korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						admin.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiAdmine();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminForma af = new AdminForma(citanje, null);
				af.setVisible(true);
				
			}
			
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 7).toString();
					Admin admin = citanje.nadjiAdmina(korisnickoIme);
					if(admin == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje admina sa tim korisickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						AdminForma af = new AdminForma(citanje, admin);
						af.setVisible(true);
					}
				}
			}
		});
	}

}
