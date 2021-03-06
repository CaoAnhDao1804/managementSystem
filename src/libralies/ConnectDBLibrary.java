package libralies;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class ConnectDBLibrary {
	private static final String URL = "jdbc:mysql://192.168.20.166:3306/mcts?useUnicode=yes&characterEncoding=utf-8";
	private static final String USER = "daisy";
	private static final String PASSWORD = "123456";
	
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	Statement st;
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connect sucesss!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs, Statement st, Connection cn) {
		close(rs);
		close(st);
		close(cn);
	}
	public static void close(ResultSet rs, PreparedStatement pst, Connection cn) {
		close(rs);
		close(pst);
		close(cn);
	}

}
