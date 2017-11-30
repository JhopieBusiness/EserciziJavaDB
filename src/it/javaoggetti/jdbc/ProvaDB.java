package it.javaoggetti.jdbc;

import java.sql.*;

public class ProvaDB {

	public static void main(String[] args) {

		// 1. caricare il driver jdbc(try/catch)
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. collegarsi ad un database
			//    (oggetto Connection)
			
			try {
				Connection c = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/libretto_esame","root","root"
						);

				// 3. ottenere una sessione (oggetto Statement) dal DB

				Statement s = c.createStatement();
				
				// 4. eseguire le query (e eventualmente ottenerne i risultati)

				ResultSet rs = s.executeQuery("SELECT * FROM studenti;");
				
				// 5. scorrere i risultati
				
				while(rs.next()) {
					System.out.println("Row "+rs.getRow());
					for(int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
						System.out.println(rs.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rs.getString(i));								// stampa il contenuto della cella i
					}
				}
				
				// 4b. eseguo una query INSERT, UPDATE, DELETE
				
				int numeroRighe = s.executeUpdate("INSERT...");
				
				// 4c. eseguo una query senza risultati
				
				s.execute("CREATE...");
				
				// 4d1. preparo una query...
				
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO marca(nome,nazionalita) VALUES (?,?)"
						);
				// 4d2. popolo i wildcard (?)
				ps.setString(1, "Sony");
				ps.setString(2, "JAP");
				// 4d3. eseguo la query
				ps.executeQuery();
				
				// ... ancora e ancora... magari in ciclo?
				ps.setString(1, "Olympus");
				ps.setString(2, "JAP");
				ps.executeQuery();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}
