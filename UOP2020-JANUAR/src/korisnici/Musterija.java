package korisnici;

public class Musterija extends Korisnik {
	
	private int brSakupljenihBodova;



	public Musterija(int id, String ime, String prezime, int jMBG, Pol pol, String adresa, int brMobilnog,
			String korisnickoIme, String lozinka, boolean obrisan, int brSakupljenihBodova) {
		super(id, ime, prezime, jMBG, pol, adresa, brMobilnog, korisnickoIme, lozinka, obrisan);
		this.brSakupljenihBodova = brSakupljenihBodova;
	}

	public int getBrSakupljenihBodova() {
		return brSakupljenihBodova;
	}

	public void setBrSakupljenihBodova(int brSakupljenihBodova) {
		this.brSakupljenihBodova = brSakupljenihBodova;
	}
	
	

}
