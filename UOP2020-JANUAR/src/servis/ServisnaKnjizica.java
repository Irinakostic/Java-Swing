package servis;

import java.util.ArrayList;

public class ServisnaKnjizica {
	
	private int id;
	private Automobil automobilSK;
	private ArrayList<ServisAutomobila>listaServisa;
	private boolean obrisan;

	
	public ServisnaKnjizica(int id, Automobil automobilSK, ArrayList<ServisAutomobila> listaServisa,
			boolean obrisan) {
		super();
		this.id = id;
		this.automobilSK = automobilSK;
		this.listaServisa = listaServisa;
		this.obrisan = obrisan;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Automobil getAutomobilSK() {
		return automobilSK;
	}


	public void setAutomobilSK(Automobil automobilSK) {
		this.automobilSK = automobilSK;
	}


	public ArrayList<ServisAutomobila> getListaServisa() {
		return listaServisa;
	}


	public void setListaServisa(ArrayList<ServisAutomobila> listaServisa) {
		this.listaServisa = listaServisa;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	

}
