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
import guiFromeZaDodavanjeiIzmene.ServiserForma;
import korisnici.Serviser;
import servisAutomobila.ServisA;



public class ServiseriProzor extends JFrame{
	
	private JToolBar mainToolBar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable serviseriTabela;
	private ServisA citanje;
	
	public ServiseriProzor(ServisA citanje) {
		this.citanje = citanje;
		setTitle("Serviseri");
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
		
		String[] zaglavlja = new String[]{"Id","Ime","Prezime","JMBG","Pol","Adresa","Broj telefona","Korisnicko ime","Lozinka","Plata","Specijalizacija"};
		
		Object[][] sadrzaj = new Object[citanje.sviNeobrisaniServiseri().size()][zaglavlja.length];
		
		for(int i=0; i<citanje.sviNeobrisaniServiseri().size();i++) {
			Serviser serviser = citanje.sviNeobrisaniServiseri().get(i);
			sadrzaj[i][0] = serviser.getId();
			sadrzaj[i][1] = serviser.getIme();
			sadrzaj[i][2] = serviser.getPrezime();
			sadrzaj[i][3] = serviser.getJMBG();
			sadrzaj[i][4] = serviser.getPol();
			sadrzaj[i][5] = serviser.getAdresa();
			sadrzaj[i][6] = serviser.getBrMobilnog();
			sadrzaj[i][7] = serviser.getKorisnickoIme();
			sadrzaj[i][8] = serviser.getLozinka();
			sadrzaj[i][9] = serviser.getPlata();
			sadrzaj[i][10] = serviser.getSpecijalizacija();
		}
		tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
		serviseriTabela = new JTable(tableModel);
		
		serviseriTabela.setRowSelectionAllowed(true);
		serviseriTabela.setColumnSelectionAllowed(false);
		serviseriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseriTabela.setDefaultEditor(Object.class, null);
		serviseriTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(serviseriTabela);
		add(scrollPane,BorderLayout.CENTER);
	}
	private void initActions() {
	btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme  = tableModel.getValueAt(red, 7).toString();
					Serviser serviser = citanje.nadjiServisera(korisnickoIme);
				
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis?", korisnickoIme + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						serviser.setObrisan(true);
						tableModel.removeRow(red);
						citanje.snimiServisere();
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServiserForma sf = new ServiserForma(citanje, null);
				sf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 3).toString();
					Serviser serviser = citanje.nadjiServisera(korisnickoIme);
					if(serviser == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenje servisera sa tim korisickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ServiserForma sf = new ServiserForma(citanje, serviser);
						sf.setVisible(true);
					}
				}
			}
		});
	}
	

}
