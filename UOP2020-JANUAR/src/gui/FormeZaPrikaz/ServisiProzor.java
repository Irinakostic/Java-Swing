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

import guiFromeZaDodavanjeiIzmene.ServisForma;
import servis.Deo;
import servis.ServisAutomobila;
import servisAutomobila.ServisA;


public class ServisiProzor extends JFrame {
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable servisiTabela;
	private ServisA citanje;
	
	public ServisiProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Servisi");
		setSize(500, 500);
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
		
		String[] zaglavlja = new String[]{"Id","Auto","Serviser","Termin","Opis","Delovi","Status servisa","Delovi"};
		
		Object[][] sadrzaj = new Object[citanje.sviNeobrisaniServisi().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sviNeobrisaniServisi().size();i++) {
			
			ServisAutomobila servis = citanje.sviNeobrisaniServisi().get(i);
			Deo deo = citanje.pronadjiDeoS(servis);
			
			sadrzaj[i][0] = servis.getId();
			sadrzaj[i][1] = servis.getAutomobilSA().getMarka()+" "+servis.getAutomobilSA().getModel();
			sadrzaj[i][2] = servis.getServiserSA().getIme()+" "+servis.getServiserSA().getPrezime();
			sadrzaj[i][3] = servis.getTermin();
			sadrzaj[i][4] = servis.getOpis();
			sadrzaj[i][5] = servis.getStatusServisa();
			sadrzaj[i][6] = deo == null ? "--" : deo.getNazivDela();
	
		
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		servisiTabela = new JTable(tableModel);
		
		servisiTabela.setRowSelectionAllowed(true);
		servisiTabela.setColumnSelectionAllowed(false);
		servisiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisiTabela.setDefaultEditor(Object.class, null);
		servisiTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(servisiTabela);
		add(scrollPane,BorderLayout.CENTER);
		}
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					ServisAutomobila servis = citanje.pronadjiServis(id);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis?", servis.getOpis() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						servis.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiServise();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServisForma sf = new ServisForma(citanje, null);
				sf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = servisiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					ServisAutomobila servis = citanje.pronadjiServis(id);
				
				
					if(servis == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje servisa sa tim opisom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ServisForma sf = new ServisForma(citanje, servis);
						sf.setVisible(true);
					}
				}
			}
		});
	}

}
