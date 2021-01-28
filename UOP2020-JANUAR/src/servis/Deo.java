package servis;

public class Deo {
	
	private int id;
	private Marka marka;
	private Model model;
	private String nazivDela;
	private float cena;
	private boolean obrisan;
	
	
	

	public Deo(int id, Marka marka, Model model, String nazivDela, float cena, boolean obrisan) {
		super();
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.nazivDela = nazivDela;
		this.cena = cena;
		this.obrisan = obrisan;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getNazivDela() {
		return nazivDela;
	}


	public void setNazivDela(String nazivDela) {
		this.nazivDela = nazivDela;
	}


	public float getCena() {
		return cena;
	}


	public void setCena(float cena) {
		this.cena = cena;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	

}
