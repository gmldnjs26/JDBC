package DB190131;

import java.sql.*;
import java.util.Scanner;

public class DBManagementExample {
	public static void main(String []args) {
		Connection conn = null;
		PreparedStatement pstmt = null; // ����
		Statement stmt = null;  // ����
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� ���� ����");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kwak","root","gmldnjs5693$");
			
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("error : " + e1);
			System.exit(1);
		}
		String query = null;
		
		while(true) {
			System.out.println("1.ģ���߰� 2.�ֹι�ȣ�ΰ˻� 3.��ü��� 4.���� 5.���� 6.����");
			System.out.print("�Է�: ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				System.out.print("�̸�: ");
				String name = sc.nextLine();
				System.out.print("�ֹι�ȣ: ");
				String jumin = sc.nextLine();
				query = "insert into friend values (null,?,?)";
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, name);
					pstmt.setString(2,jumin);
					pstmt.executeUpdate();
					pstmt.close();
					System.out.println("��� �Ϸ� �Ǿ����ϴ�.");
				} catch (SQLException e) {
					System.out.println("sql error: " + e);
				}
				break;
			case 2:
				System.out.print("�ֹι�ȣ: ");
				String search = sc.nextLine();
				query = "select * from friend where jumin = ?";
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, search);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						int num = rs.getInt("num");
						String name2 = rs.getString("name");
						String jumin2 = rs.getString("jumin");
						System.out.print(num + "\t");
						System.out.print(name2 + "\t");
						System.out.print(jumin2 + "\t");
						System.out.println();
						
					}
				} catch (SQLException e) {
					System.out.println("error: " + e);
				}
				break;
			case 3:
				System.out.println("------------��ü ���------------ ");
				try {
					stmt = conn.createStatement();
					ResultSet rs_all = stmt.executeQuery("select * from friend;");
					System.out.println("asdasd");
					while(rs_all.next()) {
						int num = rs_all.getInt("num");
						String name2 = rs_all.getString("name");
						String jumin2 = rs_all.getString("jumin");
						System.out.print(num + "\t");
						System.out.print(name2 + "\t");
						System.out.print(jumin2 + "\t");
						System.out.println();
					}
					stmt.close();
				} catch (SQLException e) {}				
				break;
			case 4: // ����
				query = "delete from friend where jumin=?";
				try {
					pstmt = conn.prepareStatement(query);
					System.out.print("�ֹ� ��ȣ: ");
					String num = sc.nextLine();
					pstmt.setString(1, num);
					pstmt.executeUpdate();
					System.out.println("���� �Ǿ����ϴ�.");
					pstmt.close();
				} catch (SQLException e) {}
				
				break;
				
			case 5: // ����
				query = "update friend set name=? where jumin=?";
				try {
					pstmt = conn.prepareStatement(query);
					System.out.print("�ֹ� ��ȣ: ");
					String num = sc.nextLine();
					System.out.print("������ �̸�:");
					String name2 = sc.nextLine();
					pstmt.setString(1, name2);
					pstmt.setString(2, num);
					pstmt.executeUpdate();
					System.out.println("���� �Ǿ����ϴ�.");
					pstmt.close();
				} catch (SQLException e) {}
				break;
			case 6:
				System.exit(0);
			}
		}
	}
}
