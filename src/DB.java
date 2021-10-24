
import java.sql.*;
import javax.swing.JOptionPane;

public class DB {
//Articles atributes.
String name;
String origin;
String date;
int m;
//Insertions to DB on ARTICLES table.
	public void insertar_art(String art_nom, String art_ori, String art_dest, String art_date){
		try{
			//MySQL connectors.
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager","root","");
			//SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			//Article insertion query.
			consulta.executeUpdate("INSERT INTO articles (name, origin, destination, date) VALUES ("
					+ "'" 
					+ art_nom 
					+ "'"
					+ ","
					+ "'"
					+ art_ori
					+ "'"
					+ ","
					+ "'"
					+ art_dest
					+ "'"
					+ ","
					+ "'"
					+ art_date
					+ "'"
					+ ")");
			//Closing DB connection.
			conexion.close();
		}
		catch(Exception ex){
			//Printing query error.
			ex.printStackTrace();
		}
	}
	
	//Insertions to DB on PROMOS table.
	public void insertar_prom(String prom_nom, String prom_ori, String prom_dest, String prom_date){
		try{
			//MySQL connectors.
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//Connection parameters JDBC//Server//DB name>User>Password
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager","root","");
			//SQLServerStatement for objects creation.
			Statement consulta = conexion.createStatement();
			//Promotion insertion query.
			consulta.executeUpdate("INSERT INTO promos (name, origin, destination, date) VALUES ("
					+ "'" 
					+ prom_nom 
					+ "'"
					+ ","
					+ "'"
					+ prom_ori
					+ "'"
					+ ","
					+ "'"
					+ prom_dest
					+ "'"
					+ ","
					+ "'"
					+ prom_date
					+ "'"
					+ ")");
			//Closing DB connection.
			conexion.close();
		}
		catch(Exception ex){
			//Printing query error.
			ex.printStackTrace();
		}
	}

public void selectArticles(){
	try{
		//MySQL connectors.
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Connection parameters JDBC//Server//DB name>User>Password
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager","root","");
		//SQLServerStatement for objects creation.
		Statement consulta = conexion.createStatement();
		//Article list query.
		ResultSet resultado = consulta.executeQuery("SELECT * FROM articles;");
		//Printing query result.
			//Title.
			System.out.println("ARTICLE LIST:\n");
		while(resultado.next()){
			//All results.
			System.out.print("\tID: " + resultado.getInt("id"));
			System.out.print("\n\t\tNAME: " + resultado.getString("name"));
			System.out.print("\n\t\tORIGIN: " + resultado.getString("origin"));
			System.out.print("\n\t\tDESTINATION: " + resultado.getString("destination"));
			System.out.println("\n\t\tDATE: " + resultado.getString("date"));
			System.out.println("\n");
		}
			//Closing DB connection.
			conexion.close();
	}
	catch(Exception ex){
		//Printing query error.
		ex.printStackTrace();
	}
}

public void selectPromos(){
	try{
		//MySQL connectors.
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Connection parameters JDBC//Server//DB name>User>Password
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager","root","");
		//SQLServerStatement for objects creation.
		Statement consulta = conexion.createStatement();
		//Article list query.
		ResultSet resultado = consulta.executeQuery("SELECT * FROM promos;");
		//Printing query result.
			//Title.
			System.out.println("PROMOTIONS LIST:\n");
		while(resultado.next()){
			//All results.
			System.out.print("\tID: " + resultado.getInt("id"));
			System.out.print("\n\t\tNAME: " + resultado.getString("name"));
			System.out.print("\n\t\tORIGIN: " + resultado.getString("origin"));
			System.out.print("\n\t\tDESTINATION: " + resultado.getString("destination"));
			System.out.println("\n\t\tDATE: " + resultado.getString("date"));
			System.out.println("\n");
		}
			//Closing DB connection.
			conexion.close();
	}
	catch(Exception ex){
		//Printing query error.
		ex.printStackTrace();
	}
}

public void selectPrices(String question){
	try{
		//MySQL connectors.
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Connection parameters JDBC//Server//DB name>User>Password
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/larapricemanager","root","");
		//SQLServerStatement for objects creation.
		Statement consulta = conexion.createStatement();
		//Article list query.
		ResultSet resultado = consulta.executeQuery("SELECT A.id AS art_id, P.id AS prom_id, A.name, A.date FROM articles A INNER JOIN promos P ON P.date = A.date AND A.date = '" + question + "';");
		//Printing query result.
			//Title.
			System.out.println("BLABLABLA (shoud be get price when developed):\n");
		while(resultado.next()){
			//All results.
			System.out.print("\n\t\tART ID: " + resultado.getString("art_id"));
			System.out.print("\n\t\tPROM ID: " + resultado.getString("prom_id"));
			System.out.print("\n\t\tNAME: " + resultado.getString("name"));
			System.out.println("\n\t\tDATE: " + resultado.getString("date"));
			System.out.println("\n");
		}
			//Closing DB connection.
			conexion.close();
	}
	catch(Exception ex){
		//Printing query error.
		ex.printStackTrace();
	}
}
    
	//Options menu.
	public int menu(int m){
		m = 0;
		do{
			m = Integer.parseInt(JOptionPane.showInputDialog(
			//Option can be added here if later defined on its own case.
			"[1] Add article\n"
			+ "[2] Get articles\n"
			+ "[3] Add promotion\n"
			+ "[4] Get promotions\n"
			+ "[5] Get prices (in progress)\n"
			+ "[6] Exit\n"
			+ "Choose an option"
			));
			switch(m){
				case 1://Add article.
					String art_nom = JOptionPane.showInputDialog("Write a name for the article");
					String art_ori = JOptionPane.showInputDialog("Write an origin for the article");
					String art_dest = JOptionPane.showInputDialog("Write a destination for the article");
					String art_date = JOptionPane.showInputDialog("Write a date for the article");
					insertar_art(art_nom, art_ori, art_dest, art_date);
					JOptionPane.showMessageDialog(null, "Article saved");
				break;
				case 2://Article list.
					selectArticles();
				break;
				case 3://Add promotion.
					String prom_nom = JOptionPane.showInputDialog("Write a name for the promo");
					String prom_ori = JOptionPane.showInputDialog("Write an origin for the promo");
					String prom_dest = JOptionPane.showInputDialog("Write a destination for the promo");
					String prom_date = JOptionPane.showInputDialog("Write a date for the article");
					insertar_prom(prom_nom, prom_ori, prom_dest, prom_date);
					JOptionPane.showMessageDialog(null, "Promo saved");
				break;
				case 4://Promotions list.
					selectPromos();
				break;
				case 5://Article price by name and date. Por ahora: article name by date
					String question = JOptionPane.showInputDialog("Write a date to search");
					selectPrices(question);
				break;
				case 6://Exit.
					JOptionPane.showMessageDialog(null, "Bye!");
				break;
				//Option choice error.
				default:
					JOptionPane.showMessageDialog(null, "Invalid");
				break;
			}
			}while(m!=6);
				return m;
		}
	}
