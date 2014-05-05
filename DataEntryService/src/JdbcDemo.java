import java.sql.*;

public class JdbcDemo {

	public static void main(String args[]) {
		String dbtime;
		String dbUrl = "jdbc:mysql://localhost:3399/expdb";
		String dbClass = "com.mysql.jdbc.Driver";
		Connection con = null;

		try {

			Class.forName(dbClass);
			con = DriverManager.getConnection(dbUrl, "jeet", "expcalc135");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("desc RECORD_ENTRY_TABLE");

			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
			con.close();
		} // end try

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	} // end main
} // end class
