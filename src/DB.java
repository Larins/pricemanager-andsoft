
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class DB {
//Articles attributes.

	// Common attributes.
	String name;
	String origin;
	LocalDate date_ini;
	LocalDate date_fin;
	long delay_days;

	// Article attributes.
	double price;

	// Promotion attributes.
	long duration_days;
	double discount; //Promotion modifier as fraction of 1. If it is 1.2 would be an increase on price of +20%, and if it is 0.8 would be a decrease (discount) of price of -20% (final price is 80% of original).

	// Program attributes.
	int m;
	DecimalFormat df = new DecimalFormat("###.##");

//Insertions to DB on ARTICLES table.
	public void insertar_art(String art_nom, String art_ori, String art_dest, LocalDate art_date_ini, long art_delay_days, LocalDate art_date_fin, double art_price) {
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
	public void insertar_prom(String prom_nom, String prom_ori, String prom_dest, LocalDate prom_date_ini, long prom_duration_days, long prom_delay_days, LocalDate prom_date_fin, double prom_discount) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Promotion insertion query.
			consulta.executeUpdate("INSERT INTO promos (name, origin, destination, date_ini, duration_days, delay_days, date_fin, discount) VALUES (" 
					+ "'" + prom_nom 
					+ "'" + "," + "'" + prom_ori 
					+ "'" + "," + "'" + prom_dest 
					+ "'" + "," + "'" + prom_date_ini
					+ "'" + "," + "'" + prom_duration_days
					+ "'" + "," + "'" + prom_delay_days
					+ "'" + "," + "'" + prom_date_fin
					+ "'" + "," + "'" + prom_discount
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
			ResultSet resultado = consulta.executeQuery("SELECT * FROM articles INNER JOIN promos P;");
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
				System.out.print("\n\t\tDELAY DAYS: " + resultado.getLong("delay_days"));
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
	
	public void selectArticleNames() {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery("SELECT name FROM articles;");
			// Printing query result.
			// Title.
			System.out.println("ARTICLE NAMES:");
			while (resultado.next()) {
				// All results.
				System.out.print("\n\t\tNAME: " + resultado.getString("name"));
				System.out.print("\n");
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
				System.out.print("\n\t\tSTART DATE: " + resultado.getDate("date_ini"));
				System.out.print("\n\t\tDELAY DAYS: " + resultado.getLong("delay_days"));
				System.out.print("\n\t\tDURATION DAYS: " + resultado.getLong("duration_days"));
				System.out.print("\n\t\tEND DATE: " + resultado.getDate("date_fin"));
				System.out.print("\n\t\tDISCOUNT: " + resultado.getDouble("discount"));
				System.out.println("\n");
			}
			// Closing DB connection.
			conexion.close();
		} catch (Exception ex) {
			// Printing query error.
			ex.printStackTrace();
		}
	}

	public void selectPrices(String question_name, LocalDate question_date) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery(
							"SELECT article, original_price, discount, increment, original_price*discount*increment AS final_price, delay_days, article_date_ini, article_date_fin "
							+ "FROM "
							+ "(SELECT "
							+ "	A.name AS article, "
							+ "    A.price AS original_price, "
							+ "    CASE "
							+ "    	WHEN MIN(P.discount) > 1 THEN 1 "
							+ "        ELSE MIN(P.discount) "
							+ "    END AS discount, "
							+ "    CASE "
							+ "    	WHEN MAX(P.discount) < 1 THEN 1 "
							+ "        ELSE MAX(P.discount) "
							+ "    END AS increment, "
							+ "    A.delay_days AS delay_days, "
							+ "    A.date_ini AS article_date_ini, "
							+ "    A.date_fin AS article_date_fin "
							+ "FROM articles A "
							+ "LEFT JOIN promos P ON ("
							+ "                	(P.date_ini BETWEEN A.date_ini AND A.date_fin OR P.date_fin BETWEEN A.date_ini AND A.date_fin)"
							+ "					OR (A.date_ini BETWEEN P.date_ini AND P.date_fin OR A.date_fin BETWEEN P.date_ini AND P.date_fin)"
							+ "					AND (A.delay_days = P.delay_days OR P.delay_days IS NULL)"
							+ "        	)"
							+ "WHERE A.name = '" + question_name + "'"
							+ "AND ((A.date_ini BETWEEN '" + question_date + "' AND '" + question_date + "' OR A.date_fin BETWEEN '" + question_date + "' AND '" + question_date + "')"
							+ "OR (P.date_ini BETWEEN '" + question_date + "' AND '" + question_date + "' OR P.date_fin BETWEEN '" + question_date + "' AND '" + question_date + "'))"
							+ "GROUP BY article, original_price, delay_days, article_date_ini, article_date_fin) AS Z;"
							);
			// Printing query result.
			// Title.
			System.out.println("PRICE OF THE ARTICLE\n\tAt chosen date:" + question_date + "\n\tWith chosen name: " + question_name);
				if (resultado.next() == false) {
					// Printing message if query result is null.
					System.out.println("\t\tArticles not found with the chosen name and date\n");
				} else {
					do {
					// Printing query result if not null.
				System.out.println("\t\tFINAL PRICE: " + resultado.getDouble("final_price") + "€");
				System.out.println("\t\t\tORIGINAL PRICE: " + resultado.getDouble("original_price") + "€");
				System.out.println("\t\t\tDISCOUNTS APPLIED: " + df.format((resultado.getDouble("discount")-1)*100) + "% (modifier: " + resultado.getDouble("discount")+")");
				System.out.println("\t\t\tINCREMENTS APPLIED: " + df.format((resultado.getDouble("increment")-1)*100) + "% (modifier: " + resultado.getDouble("increment")+")");
				System.out.println("\n");
				 } while (resultado.next());
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
							"[1] Add article\n" 
							+ "[2] Get articles\n" 
							+ "[3] Add promotion\n" 
							+ "[4] Get promotions\n"
							+ "[5] Get price of article at chosen date\n" 
							+ "[6] Get article names\n" 
							+ "[7] Set Christmas period\n"
							+ "[8] Set Clearance period\n"
							+ "[0] Exit\n" + "Choose an option"));
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
				long art_delay_days = Long.parseLong(JOptionPane.showInputDialog("Set delay days for the article"));
				LocalDate art_date_fin = art_date_ini.plusDays(art_delay_days); //Not mandatory so it is automatically set.
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
				long prom_delay_days = Long.parseLong(JOptionPane.showInputDialog("Set delay days for the promo"));
				long prom_duration_days = Long.parseLong(JOptionPane.showInputDialog("Set delay days for the promo"));
				LocalDate prom_date_fin = prom_date_ini.plusDays(prom_duration_days); //Not mandatory so it is automatically set.
				double prom_discount = Double.parseDouble(JOptionPane.showInputDialog("Set increase of price as decimal\nValue for increase of 20% of price should be 1.2\nValue for discount of 20% should be 0.8"));
				insertar_prom(prom_nom, prom_ori, prom_dest, prom_date_ini, prom_duration_days, prom_delay_days, prom_date_fin, prom_discount);
				JOptionPane.showMessageDialog(null, "Promo saved");
				break;
			case 4:// Promotions list.
				selectPromos();
				break;
			case 5:// Article list, only names
				String question_name = JOptionPane.showInputDialog("Write a article name date to search");
				LocalDate question_date = LocalDate.parse(JOptionPane.showInputDialog("Write a date to search"));
				selectPrices(question_name, question_date);
				break;
			case 6:// Article price by name and date_ini. Por ahora: article name by date_ini
				selectArticleNames();
				break;
			case 7:// Set Christmas period. It's a type of promotion, but instead of asking user for duration_days and setting date_fin automatically, it works the other way around.
				prom_date_ini = LocalDate.parse(JOptionPane.showInputDialog("Set start date for the promo"));
				prom_date_fin = LocalDate.parse(JOptionPane.showInputDialog("Set end date for the promo"));
				prom_duration_days =  prom_date_ini.until(prom_date_fin, ChronoUnit.DAYS);
				prom_discount = 1.2; //User cannot choose, Christmas period modifier is always an increase of 20% on original price.
				prom_nom = "Xmas " + prom_date_ini + " to " + prom_date_fin; //Clearance promo name is automatically set.
				insertar_prom(prom_nom, null, null, prom_date_ini, prom_duration_days, 0, prom_date_fin, prom_discount);
				JOptionPane.showMessageDialog(null, "Promo saved");
				break;
			case 8:// Set Clearance period.
				prom_date_ini = LocalDate.parse(JOptionPane.showInputDialog("Set start date for the promo"));
				prom_duration_days = Long.parseLong(JOptionPane.showInputDialog("Set delay days for the promo"));
				prom_date_fin = prom_date_ini.plusDays(prom_duration_days); //Not mandatory so it is automatically set.
				prom_discount = 0.5; //User cannot choose, Clearance period modifier is always an discount of 50% on original price.
				prom_nom = "Clearance " + prom_date_ini + " to " + prom_date_fin; //Clearance promo name is automatically set.
				insertar_prom(prom_nom, null, null, prom_date_ini, prom_duration_days, 0, prom_date_fin, prom_discount);
				JOptionPane.showMessageDialog(null, "Promo saved");
				break;
			case 0:// Exit.
				JOptionPane.showMessageDialog(null, "Bye!");
				break;
			// Option choice error.
			default:
				JOptionPane.showMessageDialog(null, "Invalid, try a valid option");
				break;
			}
		} while (m != 0);
		return m;
	}
}

