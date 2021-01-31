package korisnici;

public class Admin extends Korisnik {
	
	private double plata;


	public Admin( String id, String ime, String prezime, int jMBG, Pol pol, String adresa, int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan, double plata) {
		super(id, ime, prezime, jMBG, pol, adresa, brMobilnog, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
	}


	public double getPlata() {
		return plata;
	}


	public void setPlata(double plata) {
		this.plata = plata;
	}

	
	
	
	
	
	
	

}
