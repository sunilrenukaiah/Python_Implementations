package eu.unicreditgroup.appsec.cast.test;

This class was automatically generated

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.OracleCodec;

public class SQLInjection_CAST_TC_by_method extends HttpServlet {
	private static String DBURL = "dburl";
	private static String SQL = "select * from users";

	@Test
	public static void main_sqlinjection_OK_001(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(SQL)) { // no issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_001(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(req.getParameter("dirty_statement"))) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_002(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")))) { // no issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_002(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(req.getParameter("dirty_statement"), Statement.NO_GENERATED_KEYS)) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_003(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), Statement.NO_GENERATED_KEYS)) { // no issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_003(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(req.getParameter("dirty_statement"), ci)) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
		public static void main_sqlinjection_KO_003bis(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(req.getParameter("dirty_statement"), new int[] { 1 })) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_004(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), ci)) { // no issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_004(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(req.getParameter("dirty_statement"), cn)) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_005(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.execute(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), cn)) { // no issue
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_005(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(req.getParameter("dirty_statement")) != 0) { // issue
				System.out.println(stmt.getResultSet().first());
			}
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_006(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement"))) != 0) { // no issue
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_006(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(req.getParameter("dirty_statement"), Statement.NO_GENERATED_KEYS) != 0) { // issue
				System.out.println(stmt.getResultSet().first());
			}
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_007(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
		if(stmt.executeUpdate(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), Statement.NO_GENERATED_KEYS) != 0) { // no issue
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_007(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(req.getParameter("dirty_statement"), ci) != 0) { // issue
				System.out.println(stmt.getResultSet().first());
			}
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), ci) != 0) { // no issue
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_008(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(req.getParameter("dirty_statement"), cn) != 0) { // issue
				System.out.println(stmt.getResultSet().first());
			}
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_009(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			if(stmt.executeUpdate(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), cn) != 0) { // no issue
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_009(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			rs = stmt.executeQuery(req.getParameter("dirty_statement")); // issue
			if(rs != null) {
				System.out.println(stmt.getResultSet().first());
			}
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_010(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			rs = stmt.executeQuery(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement"))); // no issue
			if(rs != null) {
				System.out.println(stmt.getResultSet().first());
			}

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_010(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			stmt.addBatch(req.getParameter("dirty_statement")); // issue
			stmt.executeBatch();
			
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_011(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			stmt.addBatch(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement"))); // no issue
			stmt.executeBatch();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_012(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(SQL);
			ps.execute(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_013(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(SQL);
			ps.executeQuery(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_014(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(SQL);
			ps.executeUpdate(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_011(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(req.getParameter("dirty_statement"));
			ps.execute(); // issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_012(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(req.getParameter("dirty_statement"));
			ps.executeQuery(); // issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_013(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(req.getParameter("dirty_statement"));
			ps.executeUpdate(); // issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_015(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")));
			ps.execute(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_016(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")));
			ps.executeQuery(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_017(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			ps = conn.prepareStatement(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")));
			ps.executeUpdate(); // no issue

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_018(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(SQL); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_019(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(SQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_020(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(SQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_014(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(req.getParameter("dirty_statement")); // issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_015(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(req.getParameter("dirty_statement"), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); // issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_KO_016(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(req.getParameter("dirty_statement"), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT); // issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_021(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement"))); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main_sqlinjection_OK_022(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
		public static void main_sqlinjection_OK_023(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
			Connection conn = DriverManager.getConnection(DBURL);
			Statement stmt = conn.createStatement();
			
			cs = conn.prepareCall(ESAPI.encoder().encodeForSQL(new OracleCodec(), req.getParameter("dirty_statement")), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT); // no issue
			cs.execute();

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int[] ci = new int[] { 1 };
		String[] cn = new String[] { "uid" };
		
		try {
				
		// all test cases moved to specific methods...

		} catch(SQLException exc) {
			exc.printStackTrace();
		}
	}
}