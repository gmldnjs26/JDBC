package DB190130;

import java.sql.*;

public class SearchExample {
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); //com.mysql.jdbc.Driver
			//Class.forName("org.git.mm.mysql.Driver"); 두가지 방법 클래스 찾아서 정보를 제공
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "gmldnjs5693$"); 
			// connect 시키는건데 URL / ID / Password 순
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsinfo;");
			System.out.println("상품코드 상품명 \t\t가격 제조사");
			System.out.println("------------------------------");
			while(rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String maker = rs.getString("maker");
				System.out.printf("%8s %s \t%12d %s%n", code, name, price, maker);
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
