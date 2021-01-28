package korisnici;

public class Serviser extends Korisnik {
	
	private float plata;
	private Specijalizacija specijalizacija;


	public Serviser(int id, String ime, String prezime, int jMBG, Pol pol, String adresa, int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan, float plata, Specijalizacija specijalizacija) {
		super(id, ime, prezime, jMBG, pol, adresa, brMobilnog, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}


	public float getPlata() {
		return plata;
	}


	public void setPlata(float plata) {
		this.plata = plata;
	}


	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}


	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	
	
	
}
