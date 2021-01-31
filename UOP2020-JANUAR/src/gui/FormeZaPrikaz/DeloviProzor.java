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

import guiFromeZaDodavanjeiIzmene.DeoForma;
import servis.Deo;
import servisAutomobila.ServisA;


public class DeloviProzor extends JFrame{
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable deloviTabela;
	private ServisA citanje;

	public DeloviProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Delovi");
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
		
		String[] zaglavlja = new String[]{"Id","Marka","Model","Naziv","Cena"};
		
		Object[][] sadrzaj = new Object[citanje.sviNeobrisaniDelovi().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sviNeobrisaniDelovi().size();i++) {
			Deo deo = citanje.sviNeobrisaniDelovi().get(i);
			sadrzaj[i][0] = deo.getId();
			sadrzaj[i][1] = deo.getMarka();
			sadrzaj[i][2] = deo.getModel();
			sadrzaj[i][3] = deo.getNazivDela();
			sadrzaj[i][4] = deo.getCena();
		
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		deloviTabela = new JTable(tableModel);
		
		deloviTabela.setRowSelectionAllowed(true);
		deloviTabela.setColumnSelectionAllowed(false);
		deloviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		deloviTabela.setDefaultEditor(Object.class, null);
		deloviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(deloviTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv  = tableModel.getValueAt(red, 3).toString();
					Deo deo = citanje.nadjiDeo(naziv);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete deo?", deo.getNazivDela() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						deo.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiDelove();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DeoForma df = new DeoForma(citanje, null);
				df.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = deloviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv  = tableModel.getValueAt(red, 3).toString();
					Deo deo = citanje.nadjiDeo(naziv);
				
				
					if(deo == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje dela sa tim nazivom!", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						DeoForma df = new DeoForma(citanje, deo);
						df.setVisible(true);
					}
				}
			}
		});
	}
}
