package main;

import gui.Login;

import servisAutomobila.ServisA;

public class Main {

	public static void main(String[] args) {
		ServisA citanje = new ServisA();
		citanje.ucitavanjeAdmina();
		citanje.ucitavanjeMusterije();
		citanje.ucitavanjeServisera();
		citanje.ucitajAutomobile();
		citanje.ucitajDelove();
		citanje.ucitajServise();
		citanje.ucitajKnjizice();
		
		
		Login loginProzor = new Login(citanje);
		loginProzor.setVisible(true);

	}

}
