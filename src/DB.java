
import java.sql.*;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class DB {
//Articles attributes.

	// Common attributes.
	String name;
	String origin;
	LocalDate date_ini;
	LocalDate date_fin;

	// Article attributes.
	int delay_days;
	double price;

	// Promotion attributes.
	int duration_days;

	// Program attributes.
	int m;

//Insertions to DB on ARTICLES table.
	public void insertar_art(String art_nom, String art_ori, String art_dest, LocalDate art_date_ini, int art_delay_days, LocalDate art_date_fin, double art_price) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article insertion query.
			consulta.executeUpdate("INSERT INTO articles (name, origin, destination, date_ini, delay_days, date_fin, price) VALUES ("
					+ "'" + art_nom 
					+ "'" + "," + "'" + art_ori 
					+ "'" + "," + "'" + art_dest 
					+ "'" + "," + "'" + art_date_ini 
					+ "'" + "," + "'" + art_delay_days 
					+ "'" + "," + "'" + art_date_fin 
					+ "'" + "," + "'" + art_price 
					+ "'" + ")");
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	// Insertions to DB on PROMOS table.
	public void insertar_prom(String prom_nom, String prom_ori, String prom_dest, LocalDate prom_date_ini, int prom_duration_days, LocalDate prom_date_fin) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Promotion insertion query.
			consulta.executeUpdate("INSERT INTO promos (name, origin, destination, date_ini, duration_days, date_fin, date_fin) VALUES (" 
					+ "'" + prom_nom 
					+ "'" + "," + "'" + prom_ori 
					+ "'" + "," + "'" + prom_dest 
					+ "'" + "," + "'" + prom_date_ini
					+ "'" + "," + "'" + prom_duration_days
					+ "'" + "," + "'" + prom_date_fin
					+ "'" + ")");
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	public void selectArticles() {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery("SELECT * FROM articles;");
			// Printing query result.
			// Title.
			System.out.println("ARTICLE LIST:\n");
			while (resultado.next()) {
				// All results.
				System.out.print("\tID: " + resultado.getInt("id"));
				System.out.print("\n\t\tNAME: " + resultado.getString("name"));
				System.out.print("\n\t\tORIGIN: " + resultado.getString("origin"));
				System.out.print("\n\t\tDESTINATION: " + resultado.getString("destination"));
				System.out.print("\n\t\tDATE: " + resultado.getDate("date_ini"));
				System.out.print("\n\t\tDELAY DAYS: " + resultado.getInt("delay_days"));
				System.out.print("\n\t\tDATE: " + resultado.getDate("date_fin"));
				System.out.println("\n\t\tDELAY DAYS: " + resultado.getDouble("price"));
				System.out.println("\n");
			}
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	public void selectPromos() {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery("SELECT * FROM promos;");
			// Printing query result.
			// Title.
			System.out.println("PROMOTIONS LIST:\n");
			while (resultado.next()) {
				// All results.
				System.out.print("\tID: " + resultado.getInt("id"));
				System.out.print("\n\t\tNAME: " + resultado.getString("name"));
				System.out.print("\n\t\tORIGIN: " + resultado.getString("origin"));
				System.out.print("\n\t\tDESTINATION: " + resultado.getString("destination"));
				System.out.print("\n\t\tDATE: " + resultado.getDate("date_ini"));
				System.out.print("\n\t\tDURATION DAYS: " + resultado.getInt("duration_days"));
				System.out.print("\n\t\tDATE: " + resultado.getDate("date_fin"));
				System.out.println("\n");
			}
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	public void selectPrices(String question) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery(
					"SELECT A.id AS art_id, P.id AS prom_id, A.name, A.date_ini FROM articles A INNER JOIN promos P ON P.date_ini = A.date_ini AND A.date_ini = '"
							+ question + "';");
			// Printing query result.
			// Title.
			System.out.println("BLABLABLA (shoud be get price when developed):\n");
			while (resultado.next()) {
				// All results.
				System.out.print("\n\t\tART ID: " + resultado.getString("art_id"));
				System.out.print("\n\t\tPROM ID: " + resultado.getString("prom_id"));
				System.out.print("\n\t\tNAME: " + resultado.getString("name"));
				System.out.println("\n\t\tDATE: " + resultado.getDate("date_ini"));
				System.out.println("\n");
			}
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	// Options menu.
	public int menu(int m) {
		m = 0;
		boolean valid;
		do {
			do {

				valid = true;
				try {
					m = Integer.parseInt(JOptionPane.showInputDialog(
							// Option can be added here if later defined on its own case.
							"[1] Add article\n" + "[2] Get articles\n" + "[3] Add promotion\n" + "[4] Get promotions\n"
									+ "[5] Get prices (in progress)\n" + "[6] Exit\n" + "Choose an option"));
				} catch (NumberFormatException e) {
					// Returns message panel if input is not a valid option.
					JOptionPane.showMessageDialog(null, "Try again\nEnter a number from 1 to 6");
					valid = false;
				}

			} while (!valid);
			// switch(m){
			switch (m) {
			case 1:// Add article.
				String art_nom = JOptionPane.showInputDialog("Write a name for the article");
				String art_ori = JOptionPane.showInputDialog("Write an origin for the article");
				String art_dest = JOptionPane.showInputDialog("Write a destination for the article");
				LocalDate art_date_ini = LocalDate.parse(JOptionPane.showInputDialog("Set start date for the article"));
				LocalDate art_date_fin = LocalDate.parse(JOptionPane.showInputDialog("Set end date for the article"));
				int art_delay_days = Integer.parseInt(JOptionPane.showInputDialog("Set delay days for the article"));
				double art_price = Double.parseDouble(JOptionPane.showInputDialog("Set price for the article"));
				insertar_art(art_nom, art_ori, art_dest, art_date_ini, art_delay_days, art_date_fin, art_price);
				JOptionPane.showMessageDialog(null, "Article saved");
				break;
			case 2:// Article list.
				selectArticles();
				break;
			case 3:// Add promotion.
				String prom_nom = JOptionPane.showInputDialog("Write a name for the promo");
				String prom_ori = JOptionPane.showInputDialog("Write an origin for the promo");
				String prom_dest = JOptionPane.showInputDialog("Write a destination for the promo");
				LocalDate prom_date_ini = LocalDate.parse(JOptionPane.showInputDialog("Set start date for the promo"));
				int prom_duration_days = Integer.parseInt(JOptionPane.showInputDialog("Set duration days for the promo"));
				LocalDate prom_date_fin = LocalDate.parse(JOptionPane.showInputDialog("Set end date for the promo"));
				insertar_prom(prom_nom, prom_ori, prom_dest, prom_date_ini, prom_duration_days, prom_date_fin);
				JOptionPane.showMessageDialog(null, "Promo saved");
				break;
			case 4:// Promotions list.
				selectPromos();
				break;
			case 5:// Article price by name and date_ini. Por ahora: article name by date_ini
				String question = JOptionPane.showInputDialog("Write a start date to search");
				selectPrices(question);
				break;
			case 6:// Exit.
				JOptionPane.showMessageDialog(null, "Bye!");
				break;
			// Option choice error.
			default:
				JOptionPane.showMessageDialog(null, "Invalid, try a valid option");
				break;
			}
		} while (m != 6);
		return m;
	}
}
