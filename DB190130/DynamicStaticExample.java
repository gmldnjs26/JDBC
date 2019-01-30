package DB190130;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DynamicStaticExample {
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null; // 정적인 쿼리를 위한 클래스
		PreparedStatement pstmt = null; // 동적인 쿼리를 위한 클래스
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kwak", "root", "gmldnjs5693$");
			stmt = conn.createStatement();

			String name = "fff";
			String jumin = "555555-5555555";
			String birth = "2003-12-13";

			String query = "insert into person values" + "(null,'" + name +"','" + jumin +"','" + birth +"')"; // 정적 쿼리

			stmt=conn.createStatement();
			stmt.executeUpdate(query);

			String d_query = "insert into person values (null,?,?,?)"; // 동적인 query ?에 순서대로 들어간다.
			pstmt = conn.prepareStatement(d_query);
			pstmt.setString(1,name);
			pstmt.setString(2,jumin);
			pstmt.setString(3,birth);

			String search = "select * from person where num > ?";
			pstmt = conn.prepareStatement(search);
			pstmt.setInt(1,3);
			ResultSet rs = pstmt.executeQuery(); // 동적으로 Search해주기
			while(rs.next()) {
				int num = rs.getInt("num");
				String name2 = rs.getString("name");
				String jumin2 = rs.getString("jumin");
				java.sql.Date date = rs.getDate("birth");
				java.util.Date d = new java.util.Date(date.getTime());
				System.out.print(num + "\t");
				System.out.print(name2 + "\t");
				System.out.print(jumin2 + "\t");
				System.out.println(d.toString());
			}
			pstmt.close();
			stmt.close();
			conn.close();
			System.out.println("업데이트 성공!");
		}catch(SQLException e) {
			System.err.println("error =" + e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
