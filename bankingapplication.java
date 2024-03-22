
import java.sql.*;
	public class bankingapplication {
	    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost/banking";

	    static final String USER = "root";
	    static final String PASS = "kruthika@123";

	    public static void main(String[] args) {
	        Connection conn = null;
	        Statement stmt = null;
	        try {
	            Class.forName(JDBC_DRIVER);

	            conn = DriverManager.getConnection(DB_URL, USER, PASS);

	            stmt = conn.createStatement();

	            // Creating table if not exists
	            String sql = "CREATE TABLE IF NOT EXISTS accounts " +
	                    "(id INTEGER not NULL AUTO_INCREMENT, " +
	                    " name VARCHAR(255), " +
	                    " balance FLOAT, " +
	                    " PRIMARY KEY ( id ))";
	            stmt.executeUpdate(sql);

	            // Inserting sample data
	            sql = "INSERT INTO accounts (name, balance) VALUES ('John Doe', 1000), ('Jane Doe', 2000)";
	            stmt.executeUpdate(sql);

	            // Deposit
	            deposit("John Doe", 500, conn);

	            // Withdraw
	            withdraw("Jane Doe", 300, conn);

	            // Checking balance
	            checkBalance("John Doe", conn);
	            checkBalance("Jane Doe", conn);

	            stmt.close();
	            conn.close();
	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) stmt.close();
	            } catch (SQLException se2) {
	            }
	            try {
	                if (conn != null) conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    }

	    static void deposit(String name, float amount, Connection conn) throws SQLException {
	        String sql = "UPDATE accounts SET balance = balance + ? WHERE name = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setFloat(1, amount);
	        pstmt.setString(2, name);
	        pstmt.executeUpdate();
	        pstmt.close();
	        System.out.println(amount + " deposited into " + name + "'s account");
	    }

	    static void withdraw(String name, float amount, Connection conn) throws SQLException {
	        String sql = "UPDATE accounts SET balance = balance - ? WHERE name = ? AND balance >= ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setFloat(1, amount);
	        pstmt.setString(2, name);
	        pstmt.setFloat(3, amount);
	        int rowsAffected = pstmt.executeUpdate();
	        pstmt.close();
	        if (rowsAffected > 0) {
	            System.out.println(amount + " withdrawn from " + name + "'s account");
	        } else {
	            System.out.println("Insufficient funds for withdrawal from " + name + "'s account");
	        }
	    }

	    static void checkBalance(String name, Connection conn) throws SQLException {
	        String sql = "SELECT balance FROM accounts WHERE name = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, name);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            float balance = rs.getFloat("balance");
	            System.out.println(name + "'s balance: " + balance);
	        }
	        rs.close();
	        pstmt.close();
	    }
	}