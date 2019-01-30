package DB190130;

import java.sql.*;

public class SearchExample {
	public static void main(String args[]) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); //com.mysql.jdbc.Driver
			//Class.forName("org.git.mm.mysql.Driver"); �ΰ��� ��� Ŭ���� ã�Ƽ� ������ ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "gmldnjs5693$"); 
			// connect ��Ű�°ǵ� URL / ID / Password ��
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from goodsinfo;");
			System.out.println("��ǰ�ڵ� ��ǰ�� \t\t���� ������");
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
			System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
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
