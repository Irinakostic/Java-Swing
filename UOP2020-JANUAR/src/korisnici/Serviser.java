package korisnici;

public class Serviser extends Korisnik {
	
	public enum Specijalizacija {
		
		AUTOMEHANICAR, //0
		AUTOELEKTRICAR, //1
		VULKANIZER, //2
		LIMAR //3
}
	
	private double plata;
	private Specijalizacija specijalizacija;


	public Serviser( String id, String ime, String prezime, int jMBG, Pol pol, String adresa,int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan, double plata, Specijalizacija specijalizacija) {
		super(id, ime, prezime, jMBG, pol, adresa, brMobilnog, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}


	

	public double getPlata() {
		return plata;
	}




	public void setPlata(double plata) {
		this.plata = plata;
	}




	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}


	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
	
	
	
}
