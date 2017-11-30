package it.javaoggetti.jdbc;

import java.sql.*;
import java.util.Scanner;

	public class LibrettoUniversitario1 {

		public static void main(String[] args) {
			
			// 1. caricare il driver jdbc(try/catch)
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				// 2. collegarsi ad un database
				//(oggetto Connection)
				try {
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/libretto_esame","root","root");
					
					//TABELLA CORSO
					PreparedStatement psCorso = c.prepareStatement("INSERT INTO corso(nome) VALUES (?)");
					
					psCorso.setString(1, "Ingegneria Gestionale");
					psCorso.execute();
					
					psCorso.setString(1, "Ingegneria Elettronica");
					psCorso.execute();
					
					psCorso.setString(1, "Ingegneria Informatica");
					psCorso.execute();
					
					psCorso.setString(1, "Ingegneria Civile");
					psCorso.execute();
					
					
					//TABELLA STUDENTE
					PreparedStatement psStudente = c.prepareStatement("INSERT INTO studente(nome,cognome,corso) VALUES (?,?,?)");
					
					psStudente.setString(1, "Daniele");
					psStudente.setString(2, "Musacchio");
					psStudente.setString(3, "1");
					psStudente.execute();
					
					psStudente.setString(1, "Pietro");
					psStudente.setString(2, "Amendola");
					psStudente.setString(3, "2");
					psStudente.execute();
					
					psStudente.setString(1, "Gianpiero");
					psStudente.setString(2, "Errigo");
					psStudente.setString(3, "4");
					psStudente.execute();
					
					psStudente.setString(1, "Walter");
					psStudente.setString(2, "Ceravolo");
					psStudente.setString(3, "3");
					psStudente.execute();
					
					psStudente.setString(1, "Davide");
					psStudente.setString(2, "Speciale");
					psStudente.setString(3, "1");
					psStudente.execute();
					
					psStudente.setString(1, "Davide");
					psStudente.setString(2, "Cosenza");
					psStudente.setString(3, "2");
					psStudente.execute();
									
					
					//TABELLA ESAME
					PreparedStatement psEsame = c.prepareStatement("INSERT INTO esame(cfu,nome,corso) VALUES (?,?,?)");
					
					psEsame.setString(1, "12");
					psEsame.setString(2, "Appello Fisica");
					psEsame.setString(3, "1");
					psEsame.execute();
					
					psEsame.setString(1, "8");
					psEsame.setString(2, "Appello Fisica");
					psEsame.setString(3, "3");
					psEsame.execute();
					
					psEsame.setString(1, "6");
					psEsame.setString(2, "Appello Fisica");
					psEsame.setString(3, "2");
					psEsame.execute();
					
					psEsame.setString(1, "8");
					psEsame.setString(2, "Appello Elettronica");
					psEsame.setString(3, "2");
					psEsame.execute();
					
										
					//TABELLA APPELLO
					PreparedStatement psAppello = c.prepareStatement("INSERT INTO appello(data,esame) VALUES (?,?)");
					
					psAppello.setString(1, "2017-09-05");
					psAppello.setString(2, "1");
					psAppello.execute();
					
					psAppello.setString(1, "2017-04-15");
					psAppello.setString(2, "2");
					psAppello.execute();
					
					psAppello.setString(1, "2017-02-18");
					psAppello.setString(2, "3");
					psAppello.execute();
					
					psAppello.setString(1, "2017-12-22");
					psAppello.setString(2, "4");
					psAppello.execute();
					
					psAppello.setString(1, "2018-01-05");
					psAppello.setString(2, "1");
					psAppello.execute();
					
										
					//TABELLA VOTO
					PreparedStatement psVoto = c.prepareStatement("INSERT INTO voto(valore,studente,appello) VALUES (?,?,?)");
					
					psVoto.setString(1, "18");
					psVoto.setString(2, "4");
					psVoto.setString(3, "2");
					psVoto.execute();
					
					psVoto.setString(1, "20");
					psVoto.setString(2, "2");
					psVoto.setString(3, "1");
					psVoto.execute();
					
					psVoto.setString(1, "27");
					psVoto.setString(2, "2");
					psVoto.setString(3, "3");
					psVoto.execute();
					
					psVoto.setString(1, "24");
					psVoto.setString(2, "1");
					psVoto.setString(3, "4");
					psVoto.execute();
					
									
					// LEGGIAMO DA DB E STAMPIAMO A VIDEO
					
					// ottenGO una sessione (oggetto Statement) dal DB
					Statement s = c.createStatement();
					
					// 4. eseguire le query (e eventualmente ottenerne i risultati)
					ResultSet rs = s.executeQuery("SELECT * FROM studente");
					System.out.println("TABELLA STUDENTE");
					while(rs.next()) {
						System.out.println("Row "+rs.getRow());
						for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							System.out.println(rs.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rs.getString(i));								// stampa il contenuto della cella i
						}
					}
					
					// 4. eseguire le query (e eventualmente ottenerne i risultati)
					ResultSet rsS = s.executeQuery("SELECT * FROM esame");
					System.out.println("TABELLA ESAME");
					while(rsS.next()) {
						System.out.println("Row "+rsS.getRow());
						for(int i = 1; i <= rsS.getMetaData().getColumnCount(); i++) {
							System.out.println(rsS.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rsS.getString(i));								// stampa il contenuto della cella i
						}
					}
					
										
					ResultSet rs1 = s.executeQuery("select studente.corso, studente.nome, sum(cfu) from esame join studente on studente.corso = esame.corso where studente.nome='Pietro' group by corso,nome");
					System.out.println("**********************************************************");
					System.out.println("Somma Cfu Pietro");
					while(rs1.next()) {
						System.out.println("Row "+rs1.getRow());
						for(int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) {
							System.out.println(rs1.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rs1.getString(i));								// stampa il contenuto della cella i
						}
					}
					
					ResultSet rs2 = s.executeQuery("select * from appello join esame on appello.esame=esame.id join studente on esame.corso=studente.corso where appello.data<now() and studente.nome='Walter'");
					System.out.println("**********************************************************");
					System.out.println("Date di appelli già eseguiti per un determionato studente");
					while(rs2.next()) {
						System.out.println("Row "+rs2.getRow());
						for(int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
							System.out.println(rs2.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rs2.getString(i));								// stampa il contenuto della cella i
						}
					}
					
					ResultSet rs3 = s.executeQuery("select * from esame join studente on studente.corso=esame.corso where studente.nome='Walter'");
					System.out.println("**********************************************************");
					System.out.println("Tutti gli appelli effettuati di un determinato studente");
					while(rs3.next()) {
						System.out.println("Row "+rs3.getRow());
						for(int i = 1; i <= rs3.getMetaData().getColumnCount(); i++) {
							System.out.println(rs3.getMetaData().getColumnLabel(i)+": "	// stampa il nome della colonna i
									+rs3.getString(i));								// stampa il contenuto della cella i
						}
					}
								
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
