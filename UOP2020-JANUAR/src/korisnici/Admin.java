package korisnici;

public class Admin extends Korisnik {
	
	private float plata;


	public Admin(int id, String ime, String prezime, int jMBG, Pol pol, String adresa, int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan, float plata) {
		super(id, ime, prezime, jMBG, pol, adresa, brMobilnog, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
	}

	public float getPlata() {
		return plata;
	}

	public void setPlata(float plata) {
		this.plata = plata;
	}

	
	
	
	
	
	

}
