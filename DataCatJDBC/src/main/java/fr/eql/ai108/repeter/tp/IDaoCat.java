package fr.eql.ai108.repeter.tp;

import java.util.List;

public interface IDaoCat {
	void AddCat(Cat cat);
	List<Cat> GetAll();
	List<Cat> FindByName(String name);
}
	
