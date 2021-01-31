package servis;

import korisnici.Musterija;

public class Automobil {
	
	
	
	public enum VrstaGoriva {
		BMB98, //0
		BMB95, //1
		EVRODIZEL //2
}

	
	
	private  String id;
	private Musterija vlasnik;
	private Marka marka;
	private Model model;
	private int godProizvodnje;
	private int zapreminaMotora;
	private int snagaMotora;
	private VrstaGoriva vrstaGoriva;
	private boolean obrisan;
	
	
	

	public Automobil( String id, Musterija vlasnik, Marka marka, Model model, int godProizvodnje, int zapreminaMotora,
			int snagaMotora, VrstaGoriva vrstaGoriva, boolean obrisan) {
		super();
		this.id = id;
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godProizvodnje = godProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.vrstaGoriva = vrstaGoriva;
		this.obrisan = obrisan;
	}


	public  String getId() {
		return id;
	}


	public void setId( String id) {
		this.id = id;
	}


	public Musterija getVlasnik() {
		return vlasnik;
	}


	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
	}


	public Marka getMarka() {
		return marka;
	}


	public void setMarka(Marka marka) {
		this.marka = marka;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public int getGodProizvodnje() {
		return godProizvodnje;
	}


	public void setGodProizvodnje(int godProizvodnje) {
		this.godProizvodnje = godProizvodnje;
	}


	public int getZapreminaMotora() {
		return zapreminaMotora;
	}


	public void setZapreminaMotora(int zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}


	public int getSnagaMotora() {
		return snagaMotora;
	}


	public void setSnagaMotora(int snagaMotora) {
		this.snagaMotora = snagaMotora;
	}


	public VrstaGoriva getVrstaGoriva() {
		return vrstaGoriva;
	}


	public void setVrstaGoriva(VrstaGoriva vrstaGoriva) {
		this.vrstaGoriva = vrstaGoriva;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
	

}
