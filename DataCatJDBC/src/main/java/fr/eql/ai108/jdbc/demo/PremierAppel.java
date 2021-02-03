package fr.eql.ai108.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class PremierAppel {

	public static void main(String[] args) {

		
		//Pour ce connecter a une base de donnees on a besoin : 
		
		//Utilisateur de notre bdd
		String username ="root";
		//Password de notre bdd
		String password = "khaled";
		//URL de notre BDD avec quelques parametres:  adresse//port //nom base // time zone pour pas avoir un décalage horraire//parametres de securité
		
		String url = "jdbc:mysql://127.0.0.1:3306/cat_db?"
		+"serverTimezone=Europe/Paris&verifyServerCertificate=false"
	    +"&useSSL=false";
		
		String driver ="com.mysql.cj.jdbc.Driver";		
		Connection  cnx =null;
		//Charger en mémoire le driver
		try {
			//Charger en memoire le driver
			Class.forName(driver);
			//Connection à la BDD
			 cnx = DriverManager.getConnection(url, username, password);		
			System.out.println("Connexion OK");
			//insertion premier chat ///requette sql
			String req = "INSERT INTO cat (name,race,birth) VALUES ('Muta','Ragdoll','2017-09-02')";
			//L'objet statement encapsule notre requête SQL (pour encapsuler l'objet )
			Statement stmt = cnx.createStatement();
			//On execute notre statement en lui transmettant la requête (encapsuler notre requette)
			
			int success = stmt.executeUpdate(req);
			
			System.out.println(success ==1 ? "Insert ok": "Bah ca marche pas!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
