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

import guiFromeZaDodavanjeiIzmene.KnjiziceForma;
import servis.ServisnaKnjizica;
import servisAutomobila.ServisA;


public class KnjiziceProzor extends JFrame {
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjiziceTabela;
	private ServisA citanje;
	
	public KnjiziceProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Servisne Knjizice");
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
		
		String[] zaglavlja = new String[]{"Id","Auto","Servisi",};
		
		Object[][] sadrzaj = new Object[citanje.sveNeobrisaneKnjizice().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sveNeobrisaneKnjizice().size();i++) {
			ServisnaKnjizica knjizica = citanje.sveNeobrisaneKnjizice().get(i);
			sadrzaj[i][0] = knjizica.getId();
			sadrzaj[i][1] = knjizica.getAutomobilSK().getMarka()+ " "+knjizica.getAutomobilSK().getModel();
			sadrzaj[i][2] = knjizica.getListaServisa();
		
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		knjiziceTabela = new JTable(tableModel);
		
		knjiziceTabela.setRowSelectionAllowed(true);
		knjiziceTabela.setColumnSelectionAllowed(false);
		knjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjiziceTabela.setDefaultEditor(Object.class, null);
		knjiziceTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(knjiziceTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					ServisnaKnjizica knjizica = citanje.pronanjiKnjizicu(id);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete knjnizicu?", knjizica.getAutomobilSK().getMarka() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						knjizica.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiSKnjizice();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				KnjiziceForma sk = new KnjiziceForma(citanje, null);
				sk.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id  = tableModel.getValueAt(red, 0).toString();
					ServisnaKnjizica knjizica = citanje.pronanjiKnjizicu(id);
				
				
					if(knjizica == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje knjizice sa tim id-om", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						KnjiziceForma sk = new KnjiziceForma(citanje, knjizica);
						sk.setVisible(true);
					
					}
				}
				}
			});
		}

}
