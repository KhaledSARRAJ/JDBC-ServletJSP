package fr.eql.ai108.jdbc.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;

public class TestResultSet {

	public static void main(String[] args) {
DataSource ds = new CatDataSource();
		
	Connection cnx = null;	
	try {
		cnx = ds.getConnection();
		String req = "SELECT id, name, race , birth FROM cat";
		Statement stmt = cnx.createStatement();
		//on recupere dans un objet resultset le resultat de l'exécution de notre query
		ResultSet rs = stmt.executeQuery(req);
		//on itère sur le resultSet tant qu'il reste des lignes de résultat
		while (rs.next()) {
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String race = rs.getString("race");
			//gerer les dates
			LocalDate birth = rs.getDate("birth").toLocalDate();
			
			StringBuilder sb = new StringBuilder();
			sb.append("id: ").append(id.toString()).append("\tname:").append(name).append("\trace: ").append(race).append("\tbirth: ").append(birth);
			System.out.println(sb);
		
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

}
