package fr.eql.ai108.jdbc.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

public class PreparedStatementWithOption {

	public static void main(String[] args) {

		DataSource ds = new CatDataSource();
		Connection cnx = null;
		//On récupere de l'utilisateur les infos d'un chat à persister
		String name = "Garfield";
		String race = "Gros chat orange rayé";
		String birth = "05/10/1965";
		
		try {
			cnx = ds.getConnection();
			cnx.setAutoCommit(false);//pas 
			String req = "INSERT INTO cat (name, race,birth) VALUES (?,?,?)";
			PreparedStatement pstmt = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, name);
			pstmt.setString(2, race);
			//Recette pour obtenir une Sql Date à partir d'une chaine au format dd/MM/yyyy
//formater notre date souhaiter (récupperer une date à partir d'une chaine caractére
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(birth, formatter);
Date sqlBirth = Date.valueOf(date);
			pstmt.setDate(3, sqlBirth);
			int success = pstmt.executeUpdate();
			System.out.println(success ==1 ? "Insert Ok": "Marche pas");
			ResultSet rsKey =pstmt.getGeneratedKeys();
			if(rsKey.next()) {
				System.out.println(rsKey.getInt(1));
			}
			cnx.commit(); // pour enregistrer
			cnx.close();
			
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

}
