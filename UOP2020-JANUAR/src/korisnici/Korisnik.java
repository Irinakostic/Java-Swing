package korisnici;

public abstract class Korisnik {
	
	private int id;
	private String ime;
	private String prezime;
	private int JMBG;
	private Pol pol;
	private String adresa;
	private int brMobilnog;
	private String korisnickoIme;
	private String lozinka;
	boolean obrisan;
	
	
	


	
	public Korisnik(int id, String ime, String prezime, int jMBG, Pol pol, String adresa, int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		JMBG = jMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brMobilnog = brMobilnog;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public int getJMBG() {
		return JMBG;
	}


	public void setJMBG(int jMBG) {
		JMBG = jMBG;
	}


	public Pol getPol() {
		return pol;
	}


	public void setPol(Pol pol) {
		this.pol = pol;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public int getBrMobilnog() {
		return brMobilnog;
	}


	public void setBrMobilnog(int brMobilnog) {
		this.brMobilnog = brMobilnog;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	

	public boolean isObrisan() {
		return obrisan;
	}



	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}



	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", pol=" + pol
				+ ", adresa=" + adresa + ", brMobilnog=" + brMobilnog + ", korisnickoIme=" + korisnickoIme
				+ ", lozinka=" + lozinka + ", toString()=" + super.toString() + "]";
	}

	
	
}
