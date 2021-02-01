package servisAutomobila;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import korisnici.Admin;
import korisnici.Korisnik.Pol;
import korisnici.Musterija;
import korisnici.Serviser;
import korisnici.Serviser.Specijalizacija;
import servis.Automobil;
import servis.Automobil.VrstaGoriva;
import servis.Deo;
import servis.Marka;
import servis.Model;

import servis.ServisAutomobila;
import servis.ServisAutomobila.StatusServisa;
import servis.ServisnaKnjizica;



public class ServisA {
	
	private ArrayList<Admin>admini;
	private ArrayList<Musterija>musterije;
	private ArrayList<Serviser>serviseri;
	private ArrayList<Automobil>automobili;
	private ArrayList<Deo>delovi;
	private ArrayList<ServisAutomobila>servisi;
	private ArrayList<ServisnaKnjizica>knjizice;
	
	
	
	public ServisA() {
		this.admini = new ArrayList<Admin>();
		this.musterije = new ArrayList<Musterija>();
		this.serviseri = new ArrayList<Serviser>();
		this.automobili = new ArrayList<Automobil>();
		this.delovi = new ArrayList<Deo>();
		this.servisi = new ArrayList<ServisAutomobila>();
		this.knjizice = new ArrayList<ServisnaKnjizica>();
	}
	

	/////////ADMIN
	
	public ArrayList<Admin> getAdmin() {
		return admini;
	}
	

	public void dodajAdmina(Admin admin) {	
		this.admini.add(admin);
	}
	
	

	public void obrisiAdmina(Admin admin) {
		this.admini.remove(admin);
	}
	

	public Admin nadjiAdmina(String korisnickoIme) {
		for (Admin admin : admini) {
			if (admin.getKorisnickoIme().equals(korisnickoIme)) {
				return admin;
			}
		}
		return null;
	}
	
	
	public Admin loginA(String korisnickoIme, String lozinka) {
		for(Admin admin : admini) {
			if(admin.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					admin.getLozinka().equals(lozinka) && !admin.isObrisan()) {
				return admin;
			}
		}
		return null;
	}

	
	public ArrayList<Admin> sviNeobrisaniAdmini() {
		ArrayList<Admin> neobrisani = new ArrayList<Admin>();
		for (Admin admin : admini) {
			if(!admin.isObrisan()) {
				neobrisani.add(admin);
			}
		}
		return neobrisani;

	
	}
	
	
	public Admin pronadjiAdmina( String id) {	
		for (Admin admin : this.sviNeobrisaniAdmini()) {
			if(admin.getId().equals(id)) {
				return admin;
			}
		}return null;
	}
	
	
public ArrayList<Admin> ucitavanjeAdmina(){
		
		try {
			File file = new File("src/fajlovi/admini.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				int jmbg = Integer.parseInt(lineSplit[3]);
				int IntPol = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[IntPol];
				String adresa = lineSplit[5];
				int broj = Integer.parseInt(lineSplit[6]);
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				boolean obrisan = Boolean.parseBoolean(lineSplit[10]);
				Double plata = Double.parseDouble(lineSplit[9]);
			
				Admin admin = new Admin(id, ime, prezime, jmbg, pol, adresa, broj, korisnickoIme, lozinka,obrisan,plata);
				admini.add(admin);
			
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja admina!");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return admini;
		
	}

public void snimiAdmine() {
	try {
	
		File file = new File("src/fajlovi/admini.txt");
		String content = "";
		for (Admin admin : admini) {
			content += admin.getId() + "|" 
					+ admin.getIme() + "|"
					+ admin.getPrezime() + "|" 
					+ admin.getJMBG() + "|"
					+ admin.getPol().ordinal() + "|" 
					+ admin.getAdresa() + "|"
					+ admin.getBrMobilnog() + "|" 
					+ admin.getKorisnickoIme() + "|" 
					+ admin.getLozinka() + "|"
					+ admin.isObrisan() +"|"
					+ admin.getPlata()+ "\n";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		
		writer.close();
	} catch (IOException e) {
		System.out.println("Greska prilikom snimanja admina.");
	}
}
	

	//////MUSTERIJA

	public ArrayList<Musterija> getMusterija() {
		return musterije;
	}
	

	public void dodajMusteriju(Musterija musterija) {	
		this.musterije.add(musterija);
	}
	
	

	public void obrisiMusteriju(Musterija musterija) {
		this.musterije.remove(musterija);
	}
	

	public Musterija nadjiMusteriju(String korisnickoIme) {
		for (Musterija musterija : musterije) {
			if (musterija.getKorisnickoIme().equals(korisnickoIme)) {
				return musterija;
			}
		}
		return null;
	}
	
	
	public Musterija loginM(String korisnickoIme, String lozinka) {
		for(Musterija musterija : musterije) {
			if(musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					musterija.getLozinka().equals(lozinka) && !musterija.isObrisan()) {
				return musterija;
			}
		}
		return null;
	}

	
	public ArrayList<Musterija> sveNeobrisaneMusterije() {
		ArrayList<Musterija> neobrisani = new ArrayList<Musterija>();
		for (Musterija musterija : musterije) {
			if(!musterija.isObrisan()) {
				neobrisani.add(musterija);
			}
		}
		return neobrisani;

	
	}
	
	public Musterija pronadjiMusteriju( String id) {	
		for (Musterija musterija : this.sveNeobrisaneMusterije()) {
			if(musterija.getId().equals(id)) {
				return musterija;
			}
		}return null;
	}
  
	
	public Automobil vlasnikAuta(Musterija prijavljenaMusterija) {
		for(Automobil auto : sviNeobrisaniAutomobili()) {
			if(prijavljenaMusterija.getId().equals(auto.getVlasnik().getId())) {
				return auto;
			}
		}return null;
	}
	
	public ServisAutomobila autoIzServisa (Automobil a1) {
		for(ServisAutomobila s1 : sviNeobrisaniServisi()) {
			if(s1.getAutomobilSA().getId().equals(a1.getId())) {
				return s1;
			}		
		}return null;	
	}
	
	public String nadjiImePrezimeServiseraId(String imeIPrezime) {
		for(Serviser serviser : sviNeobrisaniServiseri()) {
			String postojeceImeIprezime = serviser.getIme().concat(serviser.getPrezime()).toLowerCase();
			if(imeIPrezime.toLowerCase().equals(postojeceImeIprezime)) {
				return serviser.getId();
			}
			
		}return null;
	}
	
	
public ArrayList<Musterija> ucitavanjeMusterije(){
		
		try {
			File file = new File("src/fajlovi/musterije.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				int jmbg = Integer.parseInt(lineSplit[3]);
				int IntPol = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[IntPol];
				String adresa = lineSplit[5];
				int broj = Integer.parseInt(lineSplit[6]);
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				boolean obrisan = Boolean.parseBoolean(lineSplit[9]);
				int brojBodova = Integer.parseInt(lineSplit[10]);
			
				Musterija musterija = new Musterija(id, ime, prezime, jmbg, pol, adresa, broj, korisnickoIme, lozinka,obrisan,brojBodova);
				musterije.add(musterija);
			
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja musterija!");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return musterije;
		
	}


public void snimiMusterije() {
	try {
	
		File file = new File("src/fajlovi/musterije.txt");
		String content = "";
		for (Musterija musterija : musterije) {
			content += musterija.getId() + "|" 
					+ musterija.getIme() + "|"
					+ musterija.getPrezime() + "|" 
					+ musterija.getJMBG() + "|"
					+ musterija.getPol().ordinal() + "|" 
					+ musterija.getAdresa() + "|"
					+ musterija.getBrMobilnog() + "|"
					+ musterija.getKorisnickoIme() + "|" 
					+ musterija.getLozinka() + "|"
					+ musterija.isObrisan() +"|"
					+ musterija.getBrSakupljenihBodova()+ "\n";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		
		writer.close();
	} catch (IOException e) {
		System.out.println("Greska prilikom snimanja musterija.");
	}
}
	

	/////////////SERVISER
	
	public ArrayList<Serviser> getServiser() {
		return serviseri;
	}
	

	public void dodajServisera(Serviser serviser) {	
		this.serviseri.add(serviser);
	}
	
	

	public void obrisiServisera(Serviser serviser) {
		this.serviseri.remove(serviser);
	}
	

	public Serviser nadjiServisera(String korisnickoIme) {
		for (Serviser serviser : serviseri) {
			if (serviser.getKorisnickoIme().equals(korisnickoIme)) {
				return serviser;
			}
		}
		return null;
	}
	
	
	public Serviser loginS(String korisnickoIme, String lozinka) {
		for(Serviser serviser : serviseri) {
			if(serviser.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					serviser.getLozinka().equals(lozinka) && !serviser.isObrisan()) {
				return serviser;
			}
		}
		return null;
	}

	
	public ArrayList<Serviser> sviNeobrisaniServiseri() {
		ArrayList<Serviser> neobrisani = new ArrayList<Serviser>();
		for (Serviser serviser : serviseri) {
			if(!serviser.isObrisan()) {
				neobrisani.add(serviser);
			}
		}
		return neobrisani;

	
	}
	
	
	public Serviser pronadjiServisera( String id) {	
		for (Serviser serviser : this.sviNeobrisaniServiseri()) {
			if(serviser.getId().equals(id)) {
				return serviser;
			}
		}return null;
	}
	
	
public ArrayList<Serviser> ucitavanjeServisera(){
		
		try {
			File file = new File("src/fajlovi/serviseri.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				int jmbg = Integer.parseInt(lineSplit[3]);
				int IntPol = Integer.parseInt(lineSplit[4]);
				Pol pol = Pol.values()[IntPol];
				String adresa = lineSplit[5];
				int broj = Integer.parseInt(lineSplit[6]);
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				boolean obrisan = Boolean.parseBoolean(lineSplit[11]);
				Double plata = Double.parseDouble(lineSplit[9]);
				int IntSpecijalizacija = Integer.parseInt(lineSplit[10]);
				Specijalizacija specijalizacija = Specijalizacija.values()[IntSpecijalizacija];
			
				Serviser serviser = new Serviser(id, ime, prezime, jmbg, pol, adresa, broj, korisnickoIme, lozinka,obrisan,plata,specijalizacija);
				serviseri.add(serviser);
			
			}
			reader.close();
			
		} catch (IOException e) {
			System.out.println("Greska prilikom citanja servisera!");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return serviseri;
		
	}

public void snimiServisere() {
	try {
	
		File file = new File("src/fajlovi/serviseri.txt");
		String content = "";
		for (Serviser serviser : serviseri) {
			content += serviser.getId() + "|" 
					+ serviser.getIme() + "|"
					+ serviser.getPrezime() + "|"
					+ serviser.getJMBG() + "|"
					+ serviser.getPol().ordinal() + "|" 
					+ serviser.getAdresa() + "|"
					+ serviser.getBrMobilnog() + "|" 
					+ serviser.getKorisnickoIme() + "|"
					+ serviser.getLozinka() + "|"
					+ serviser.isObrisan() +"|"
					+ serviser.getPlata() +"|" 
					+ serviser.getSpecijalizacija().ordinal() + "\n";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		
		writer.close();
	} catch (IOException e) {
		System.out.println("Greska prilikom snimanja servisera.");
	}
}
	
//////////AUTOMOBILI

public ArrayList<Automobil> getAutomobili() {
	return automobili;
}

public void dodajAutomobil(Automobil auto) {	
	this.automobili.add(auto);
}

public ArrayList<Automobil> sviNeobrisaniAutomobili() {
	ArrayList<Automobil> neobrisani = new ArrayList<Automobil>();
	for (Automobil auto : automobili) {
		if(!auto.isObrisan()) {
			neobrisani.add(auto);
		}
	}
	return neobrisani;
}

public Automobil pronadjiAutomobil(String id) {
	for (Automobil automobil : this.sviNeobrisaniAutomobili()) {
		if(automobil.getId().equals(id)) {
			return automobil;
		}
	}
	return null;

}

public Musterija pronadjiVlasnikaA(Automobil a) {
	for (Musterija musterija : musterije) {
		if (a.getVlasnik().getId().equals(musterija.getId())) {
			return musterija;
		}
	}
	return null;
}

public String vlasnikImePrezime(String imePrezime) {
	for(Musterija musterija : sveNeobrisaneMusterije()) {
		String aktivnoImePrezime = musterija.getIme().concat(musterija.getPrezime()).toLowerCase();
		if(imePrezime.toLowerCase().equals(aktivnoImePrezime)) {
			return musterija.getId();
		}
		
	}return null;
}


public ArrayList<Automobil>ucitajAutomobile(){
	
	try {
		
		File file = new File("src/fajlovi/automobili.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while((line = reader.readLine()) !=null) {
			String[]lineSplit = line.split("\\|");
			
			String id = lineSplit[0];
			String idVlasnik = lineSplit[1];
			Musterija vlasnik =(Musterija) pronadjiMusteriju(idVlasnik);
			int intMarka = Integer.parseInt(lineSplit[2]);
			Marka marka = Marka.values()[intMarka];
			int intModel = Integer.parseInt(lineSplit[3]);
			Model model = Model.values()[intModel];
			int godProizvodnje = Integer.parseInt(lineSplit[4]);
			int zapreminaMotora = Integer.parseInt(lineSplit[5]);
			int snagaMotora = Integer.parseInt(lineSplit[6]);
			int intVrstaGoriva = Integer.parseInt(lineSplit[7]);
			VrstaGoriva gorivo = VrstaGoriva.values()[intVrstaGoriva];
			boolean obrisan = Boolean.parseBoolean(lineSplit[8]);
			
			Automobil auto = new Automobil(id, vlasnik, marka, model, godProizvodnje, zapreminaMotora,snagaMotora, gorivo,obrisan);
			if(vlasnik != null) {
				auto.setVlasnik(vlasnik);
			}
			
			automobili.add(auto);
		}
		reader.close();
	}
		
	 catch (IOException e) {
		System.out.println("Greska prilikom citanja automobila!");
	}catch(NumberFormatException n) {
		n.printStackTrace();
	}
	return automobili;
}

public void snimiAutobobile() {
	try {
		
		File file = new File("src/fajlovi/automobili.txt");
		String content = "";
		for (Automobil auto : automobili) {
					content += auto.getId() + "|" 
							+ auto.getVlasnik().getId() + "|" 
							+ auto.getMarka().ordinal()+ "|" 
							+ auto.getModel().ordinal() + "|" 
							+ auto.getGodProizvodnje() + "|"
							+ auto.getZapreminaMotora() + "|" 
							+ auto.getSnagaMotora() + "|"
							+ auto.getVrstaGoriva().ordinal() + "|"
							+ auto.isObrisan() + "\n";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		writer.close();
	
	} catch (IOException e) {
		System.out.println("Greska prilikom snimanja automobila.");
	}catch (NullPointerException e) {
		e.printStackTrace();
	}
}

////////////////////DELOVI

	public void dodajDeo(Deo deo) {	
		this.delovi.add(deo);
		}
	
	
	public Deo pronadjiDeo(String id) {
		for (Deo deo : delovi) {
			if(deo.getId().equals(id)) {
				return deo;
			}
		}return null;
	}
	
	
	public Deo nadjiDeo(String naziv) {	
		for (Deo deo : delovi) {
			if (deo.getNazivDela().equals(naziv)) {
				return deo;
			}
		}
		return null;
	}
	
	
	public ArrayList<Deo> sviNeobrisaniDelovi() {
		ArrayList<Deo> neobrisani = new ArrayList<Deo>();
		for (Deo deo : delovi) {
			if(!deo.isObrisan()) {
				neobrisani.add(deo);
			}
		}
		return neobrisani;
	}
	
	public Deo pronadjiDeoS(ServisAutomobila sa) {
		for (Deo deo: sviNeobrisaniDelovi()) {
			if (deo.getListaServisa().contains(sa)) {
				return deo;
			}
		}
		return null;
	}
	
public  ArrayList<Deo>ucitajDelove(){
		
		try {
			File file = new File("src/fajlovi/delovi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				int intMarka = Integer.parseInt(lineSplit[1]);
				Marka marka = Marka.values()[intMarka];
				int intModel = Integer.parseInt(lineSplit[2]);
				Model model = Model.values()[intModel];
				String naziv = lineSplit[3];
				Double cena = Double.parseDouble(lineSplit[4]);
				boolean obrisan = Boolean.parseBoolean(lineSplit[5]);
				ArrayList<ServisAutomobila> listaServisa = new ArrayList<ServisAutomobila>();
				Deo deo = new Deo(id, marka, model, naziv, cena,obrisan,listaServisa);
				delovi.add(deo);
				
			}
			reader.close();
		}
		 catch (IOException e) {
			System.out.println("Greska prilikom citanja delova!");
		}catch (NumberFormatException n) {
			n.printStackTrace();
		}
		return delovi;
	}

public void snimiDelove() {
	try {

		File file = new File("src/fajlovi/delovi.txt");
		String content = "";
		for (Deo deo : delovi) {
			content += deo.getId() + "|" 
					+ deo.getMarka().ordinal()+ "|"
					+ deo.getModel().ordinal() + "|" 
					+ deo.getNazivDela()+ "|"
					+ deo.getCena()+"|"
					+ deo.isObrisan()+ "\n";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(content);
		writer.close();
	} catch (IOException e) {
		System.out.println("Greska prilikom snimanja delova.");
	}
}


///////SERVISI AUTOMOBILA

	public void dodajServis(ServisAutomobila servis) {	
	this.servisi.add(servis);
}


	public ServisAutomobila pronadjiServis(String id) {
	for (ServisAutomobila servis : servisi) {
		if(servis.getId().equals(id)) {
			return servis;
		}
	}return null;
}
	
	
	public ArrayList<ServisAutomobila> sviNeobrisaniServisi() {
		ArrayList<ServisAutomobila> neobrisani = new ArrayList<ServisAutomobila>();
		for (Deo deo : delovi) {
			for (ServisAutomobila servis : deo.getListaServisa()) {
				if(!servis.isObrisan()) {
					neobrisani.add(servis);
			}
		}
	}
		return neobrisani;
	}
	
	public ServisAutomobila nadjiServis(String opis) {	
		for (ServisAutomobila servis : servisi) {
			if (servis.getOpis().equals(opis)) {
				return servis;
			}
		}
		return null;
	}

	/*	public Kompozicija pronadjiKompoziciju(String naziv) {
		for (Disk disk : diskovi) {
			for(Kompozicija kompozicija : disk.getKompozicije()) {
				if(kompozicija.getNaziv().equals(naziv)) {
					return kompozicija;
				}
			}
		}
		return null;
	}*/
public ArrayList<ServisAutomobila>ucitajServise(){
		
		try {
			File file = new File("src/fajlovi/servisi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				String intAutomobil = lineSplit[1];
				Automobil auto =(Automobil) pronadjiAutomobil(intAutomobil);
				String intServiser = lineSplit[2];
				Serviser serviser = (Serviser)pronadjiServisera(intServiser);
				int termin = Integer.parseInt(lineSplit[3]);
				String opis = lineSplit[4];
				int intStatusServisa = Integer.parseInt(lineSplit[5]);
				StatusServisa statusServisa = StatusServisa.values()[intStatusServisa];
				String deoID = lineSplit[7];
				Deo d = (Deo) pronadjiDeo(deoID);
				boolean obrisan = Boolean.parseBoolean(lineSplit[6]);
				ServisAutomobila servis = new ServisAutomobila(id, auto, serviser, termin , opis,statusServisa, obrisan);
				if(auto==null) {
					servis.setAutomobilSA(auto);
				}
				if(serviser!=null) {
					servis.setServiserSA(serviser);
				}
				if (d != null) {
					d.getListaServisa().add(servis);}
				servisi.add(servis);
			}
			reader.close();
		}
		 catch (IOException e) {
			System.out.println("Greska prilikom citanja servisa automobila!");
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return servisi;
	}




	public void snimiServise() {
		try {
			
			File file = new File("src/fajlovi/servisi.txt");
			String content = "";
			for (Deo deo : delovi) {
				for (ServisAutomobila servis : deo.getListaServisa()) {
				content += servis.getId() + "|"
						+ servis.getAutomobilSA().getId()+ "|"
						+ servis.getServiserSA().getId() + "|"
						+ servis.getTermin()+ "|"
						+ servis.getOpis()+"|" 
						+ servis.getStatusServisa()+"|" 
						+ servis.isObrisan() +  "\n";
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisa.");
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

////////////SERVISNE KNJIZICE
	
	public void dodajKnjizice(ServisnaKnjizica servisnaKnjizica) {	
		this.knjizice.add(servisnaKnjizica);
	}

	
	public ArrayList<ServisnaKnjizica> sveNeobrisaneKnjizice() {
		ArrayList<ServisnaKnjizica> neobrisani = new ArrayList<ServisnaKnjizica>();
		for (ServisnaKnjizica knjizica : knjizice) {
			if(!knjizica.isObrisan()) {
				neobrisani.add(knjizica);
			}
		}
		return neobrisani;
	}
	
	public ServisnaKnjizica pronanjiKnjizicu(String id) {
		for(ServisnaKnjizica knjizica : this.sveNeobrisaneKnjizice()) {
			if(knjizica.getId().equals(id)) {
				return knjizica;
			}
		}return null;
	}

	
	
public ArrayList<ServisnaKnjizica>ucitajKnjizice(){
		
		try {
			File file = new File("src/fajlovi/knjizice.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) !=null) {
				String[]lineSplit = line.split("\\|");
				
				String id = lineSplit[0];
				String intAutomobil = lineSplit[1];
				Automobil auto = (Automobil)pronadjiAutomobil(intAutomobil);
				ArrayList<ServisAutomobila>servisi = new ArrayList<ServisAutomobila>();
				boolean obrisan = Boolean.parseBoolean(lineSplit[2]);
				ServisnaKnjizica knjizica = new ServisnaKnjizica(id, auto, servisi,obrisan);
				if(auto!=null) {
					knjizica.setAutomobilSK(auto);
				}
				knjizice.add(knjizica);
				
				
				
			}
			reader.close();
		}
		 catch (IOException e) {
			System.out.println("Greska prilikom citanja knjizica!");
		}
		return knjizice;
		
	}

	public void snimiSKnjizice() {
		try {
			
			File file = new File("src/fajlovi/knjizice.txt");
			String content = "";
			for (ServisnaKnjizica servisnaKnjizica : knjizice) {
				content += servisnaKnjizica.getId() + "|" 
						+ servisnaKnjizica.getAutomobilSK().getId()+ "|"
						+ servisnaKnjizica.getListaServisa() 
						+ "|"+servisnaKnjizica.isObrisan()+
						"\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja servisnih knjizica.");
		}
	}
	

}
