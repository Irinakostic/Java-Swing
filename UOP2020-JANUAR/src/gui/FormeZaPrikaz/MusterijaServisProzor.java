package gui.FormeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import guiFromeZaDodavanjeiIzmene.MusterijaServisDodavanjeIzmene;
import korisnici.Musterija;
import servis.Automobil;
import servis.ServisAutomobila;
import servisAutomobila.ServisA;


public class MusterijaServisProzor extends JFrame {
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable servisTabela;
	private ServisA citanje;
	private Musterija ulogovanaMusterija;
	private ServisAutomobila servis;
	
	
	public MusterijaServisProzor(ServisA citanje,Musterija ulogovanaMusterija) {
		this.citanje = citanje;
		this.ulogovanaMusterija = ulogovanaMusterija;
		setTitle("Servisi");
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
		
		add(mainToolBar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[]{"Auto","Opis"};
		Automobil a1 = citanje.vlasnikAuta(ulogovanaMusterija);
		System.out.println(a1);
		ServisAutomobila s1 = citanje.autoIzServisa(a1);
		ArrayList<ServisAutomobila>pronadjeni = new ArrayList<ServisAutomobila>();
		pronadjeni.add(s1);
		System.out.println(s1);
		Object[][] sadrzaj = new Object[pronadjeni.size()][zaglavlja.length];
		
		for(int i=0; i<pronadjeni.size();i++) {
			ServisAutomobila s2 = pronadjeni.get(i);
			sadrzaj[i][0] = s2.getAutomobilSA().getMarka()+ " "+s2.getAutomobilSA().getModel();
			sadrzaj[i][1] = s2.getOpis();
	
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		servisTabela = new JTable(tableModel);
		
		servisTabela.setRowSelectionAllowed(true);
		servisTabela.setColumnSelectionAllowed(false);
		servisTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisTabela.setDefaultEditor(Object.class, null);
		servisTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(servisTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int red = servisTabela.getSelectedRow();
					if(red == -1) {
						JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						String opis  = tableModel.getValueAt(red, 1).toString();
						ServisAutomobila servis = citanje.nadjiServis(opis);
					
						
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis?", opis + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							servis.setObrisan(true);
							tableModel.removeRow(red);
							citanje.snimiServise();
						}
					}
					
				}
			});
			btnEdit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					MusterijaServisDodavanjeIzmene sf = new MusterijaServisDodavanjeIzmene(citanje,servis,ulogovanaMusterija );
					sf.setVisible(true);
					
				}
			});
		
		}

}
