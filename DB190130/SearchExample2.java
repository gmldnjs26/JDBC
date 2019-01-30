package DB190130;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchExample2 {
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kwak", "root", "gmldnjs5693$");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from person;");
			while(rs.next()) { // ResultSet에 담고 하나씩 빼온다.
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String jumin = rs.getString("jumin");
				java.sql.Date date = rs.getDate("birth"); // 날짜 형식 데이터
				java.util.Date d = new java.util.Date(date.getTime()); // 날짜 형식으로 고쳐준다.
				System.out.print(num + "\t");
				System.out.print(name + "\t");
				System.out.print(jumin + "\t");
				System.out.println(d.toString());
			}
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}
		catch(SQLException se) {
			System.out.println(se.getMessage());
		}
		finally {
			try {
				stmt.close();
			}catch(Exception ignored) {}
			try {
				conn.close();
			}catch(Exception ignored) {}
		}
	}
}
