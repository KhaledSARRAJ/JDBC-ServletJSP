package fr.eql.ai108.repeter.tp;

import java.time.LocalDate;
import java.util.List;

public class ProgrammePrincipale {

	public static void main(String[] args) {
		IDaoCat dao = new DaoCat();
		//Cat cat = new Cat(null, "Duchesse", "Chat blanc", LocalDate.now());
		Cat cat1 = new Cat(null, "Egyptienne", "Chat Bronzé", LocalDate.now());	
		dao.AddCat(cat1);
	   
		List<Cat>cats = dao.GetAll();
		
		for (Cat cat2 : cats) {
			System.out.println(cats.toString());
		}
		List<Cat> founds = dao.FindByName("Muta");
		for (Cat cat3 : founds) {
			System.out.println(cat3.toString());
		}
	}

}
