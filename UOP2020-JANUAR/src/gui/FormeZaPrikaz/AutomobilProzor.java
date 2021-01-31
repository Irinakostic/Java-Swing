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

import guiFromeZaDodavanjeiIzmene.AutomobilForma;
import servis.Automobil;
import servisAutomobila.ServisA;

public class AutomobilProzor  extends JFrame{
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable autoTabela;
	private ServisA citanje;
	
	public AutomobilProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Automobili");
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
		
		String[] zaglavlja = new String[]{"Id","Vlasnik","Marka","Model","Godina proizvodnje","Zapremina motora","Snaga motora","Vrsta goriva"};
		
		Object[][] sadrzaj = new Object[citanje.sviNeobrisaniAutomobili().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sviNeobrisaniAutomobili().size();i++) {
			
				Automobil auto = citanje.sviNeobrisaniAutomobili().get(i);
				
				sadrzaj[i][0] = auto.getId();
				sadrzaj[i][1] = auto.getVlasnik().getIme()+ " "+ auto.getVlasnik().getPrezime();
				sadrzaj[i][2] = auto.getMarka();
				sadrzaj[i][3] = auto.getModel();
				sadrzaj[i][4] = auto.getGodProizvodnje();
				sadrzaj[i][5] = auto.getZapreminaMotora();
				sadrzaj[i][6] = auto.getSnagaMotora();
				sadrzaj[i][7] = auto.getVrstaGoriva();
			
			
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		autoTabela = new JTable(tableModel);
		
		autoTabela.setRowSelectionAllowed(true);
		autoTabela.setColumnSelectionAllowed(false);
		autoTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		autoTabela.setDefaultEditor(Object.class, null);
		autoTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(autoTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = autoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					Automobil auto = citanje.pronadjiAutomobil(id);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete auto?", auto.getMarka() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						auto.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiAutobobile();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AutomobilForma af = new AutomobilForma(citanje, null);
				af.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = autoTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					Automobil auto = citanje.pronadjiAutomobil(id);
				
				
					if(auto == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje admina sa tom snagom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						AutomobilForma af = new AutomobilForma(citanje, auto);
						af.setVisible(true);
					}
				}
			}
		});
	}

}
