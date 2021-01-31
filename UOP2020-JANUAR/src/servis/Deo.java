package servis;

public class Deo {
	

	
	private  String id;
	private Marka marka;
	private Model model;
	private String nazivDela;
	private double cena;
	private boolean obrisan;
	
	
	

	public Deo( String id, Marka marka, Model model, String nazivDela, double cena, boolean obrisan) {
		super();
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.nazivDela = nazivDela;
		this.cena = cena;
		this.obrisan = obrisan;
	}


	public  String getId() {
		return id;
	}


	public void setId( String id) {
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



	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	

}
