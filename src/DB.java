
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
	public void insertar_art(String art_nom, String art_ori, String art_dest, long art_delay_days, double art_price) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article insertion query.
			consulta.executeUpdate("INSERT INTO articles (name, origin, destination, delay_days, price) VALUES ("
					+ "'" + art_nom 
					+ "'" + "," + "'" + art_ori 
					+ "'" + "," + "'" + art_dest 
					+ "'" + "," + "'" + art_delay_days 
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
				System.out.print("\n\t\tDELAY DAYS: " + resultado.getLong("delay_days"));
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

	public void selectPrices(String question_name, String question_ori, String question_dest, Long question_delay, LocalDate question_date) {
		try {
			// MySQL connectors.
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager", "root", "");
			// SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			// Article list query.
			ResultSet resultado = consulta.executeQuery(
							"SELECT A.id, A.name AS chosen_article, '" + question_date + "' AS chosen_date, A.origin, A.destination, A.delay_days AS delay_article, Z.delay_days AS delay_promo, A.price AS original_price, " + "Z.discount, Z.increment, A.price*Z.discount*Z.increment AS final_price "
							+ "FROM articles A "
							+ "LEFT JOIN  "
							+ "    ( "
							+ "        SELECT "
							+ "            CASE  "
							+ "                WHEN MIN(P.discount) > 1 THEN 1 "
							+ "                ELSE MIN(P.discount) "
							+ "            END AS discount, "
							+ "            CASE  "
							+ "                WHEN MAX(P.discount) < 1 THEN 1 "
							+ "                ELSE MAX(P.discount) "
							+ "            END AS increment, "
							+ "            P.delay_days AS delay_days, "
							+ "            P.origin,  "
							+ "            P.destination "
							+ "        FROM promos P "
							+ "        WHERE P.date_ini <= '" + question_date + "' AND P.date_fin >= '" + question_date + "' "
							+ "    ) AS Z ON (Z.delay_days = A.delay_days OR Z.delay_days IS NULL) AND A.origin = Z.origin AND A.destination = Z.destination "
							+ "WHERE A.name = '" + question_name + "' "
							+ "AND A.origin = '" + question_ori + "' "
							+ "AND A.destination = '" + question_dest + "' "
							+ "AND A.delay_days = " + question_delay + ""
							+ ";"
							);
			// Printing query result.
			// Title.
			System.out.println("PRICE OF THE ARTICLE\n");
			System.out.println("Your data input, in case you want to check:"
					+ "\n\tDATE FOR PROMOS: " + question_date 
					+ "\n\tNAME OF ARTICLE: " + question_name
					+ "\n\tORIGIN: " + question_ori
					+ "\n\tDESTINATION: " + question_dest
					+ "\n\tDELAY: " + question_delay
					);
				if (resultado.next() == false) {
					// Printing message if query result is null.
					throw new Exception("EXCEPTION: Articles not found with the chosen name and date. Details:");
				} else {
					do {
					// Printing query result if not null.
				System.out.println("\nFINAL PRICE: " + resultado.getDouble("final_price") + "€");
				System.out.println("Detailed data in case it's of your interest:");
				System.out.println("\tORIGINAL PRICE: " + resultado.getDouble("original_price") + "€");
				System.out.println("\tDISCOUNTS APPLIED: " + df.format((resultado.getDouble("discount")-1)*100) + "% (modifier: " + resultado.getDouble("discount")+")");
				System.out.println("\tINCREMENTS APPLIED: " + df.format((resultado.getDouble("increment")-1)*100) + "% (modifier: " + resultado.getDouble("increment")+")");
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
				long art_delay_days = Long.parseLong(JOptionPane.showInputDialog("Set delay days for the article"));
				double art_price = Double.parseDouble(JOptionPane.showInputDialog("Set price for the article"));
				insertar_art(art_nom, art_ori, art_dest, art_delay_days, art_price);
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
			case 5:// Get article price by article name and date
				String question_name = JOptionPane.showInputDialog("Write an article name to search");
				String question_ori = JOptionPane.showInputDialog("Write an article origin to search");
				String question_dest = JOptionPane.showInputDialog("Write an article destination to search");
				Long question_delay = Long.parseLong(JOptionPane.showInputDialog("Write an article delay to search"));
				LocalDate question_date = LocalDate.parse(JOptionPane.showInputDialog("Write a date to search"));
				selectPrices(question_name, question_ori, question_dest, question_delay, question_date);
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


