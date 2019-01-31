package DB190131;

import java.sql.*;
import java.util.Scanner;

public class DBManagementExample {
	public static void main(String []args) {
		Connection conn = null;
		PreparedStatement pstmt = null; // 동적
		Statement stmt = null;  // 정적
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 접속 성공");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kwak","root","gmldnjs5693$");
			
		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("error : " + e1);
			System.exit(1);
		}
		String query = null;
		
		while(true) {
			System.out.println("1.친구추가 2.주민번호로검색 3.전체출력 4.삭제 5.개명 6.종료");
			System.out.print("입력: ");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1:
				System.out.print("이름: ");
				String name = sc.nextLine();
				System.out.print("주민번호: ");
				String jumin = sc.nextLine();
				query = "insert into friend values (null,?,?)";
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, name);
					pstmt.setString(2,jumin);
					pstmt.executeUpdate();
					pstmt.close();
					System.out.println("등록 완료 되었습니다.");
				} catch (SQLException e) {
					System.out.println("sql error: " + e);
				}
				break;
			case 2:
				System.out.print("주민번호: ");
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
				System.out.println("------------전체 출력------------ ");
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
			case 4: // 삭제
				query = "delete from friend where jumin=?";
				try {
					pstmt = conn.prepareStatement(query);
					System.out.print("주민 번호: ");
					String num = sc.nextLine();
					pstmt.setString(1, num);
					pstmt.executeUpdate();
					System.out.println("삭제 되었습니다.");
					pstmt.close();
				} catch (SQLException e) {}
				
				break;
				
			case 5: // 수정
				query = "update friend set name=? where jumin=?";
				try {
					pstmt = conn.prepareStatement(query);
					System.out.print("주민 번호: ");
					String num = sc.nextLine();
					System.out.print("개명할 이름:");
					String name2 = sc.nextLine();
					pstmt.setString(1, name2);
					pstmt.setString(2, num);
					pstmt.executeUpdate();
					System.out.println("개명 되었습니다.");
					pstmt.close();
				} catch (SQLException e) {}
				break;
			case 6:
				System.exit(0);
			}
		}
	}
}
