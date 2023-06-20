package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsertMenuThrows {
	String str;
	int num;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		StudentInsertMenuThrows simth = new StudentInsertMenuThrows();
		System.out.println("::::::::::::::::::::학생 등록 메뉴입니다.::::::::::::::::::::");

		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			simth.insertStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}// main

	public void insertStudent(Connection connection) throws SQLException {
		Scanner sc = new Scanner(System.in);
		boolean status = true;
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		while (status) {
			System.out.println("학생번호 입력시 0000입력은 종료입니다.");
			try {
			System.out.print("학번을 입력하세요 >>> ");
			str = sc.nextLine();

			if (str.equals("0000")) {
				System.out.println("학생 등록을 중지합니다.");
				status = false;
				break;
			}
			ps.setString(1, str);

			System.out.print("이름을 입력하세요 >>> ");
			str = sc.nextLine();
			ps.setString(2, str);

			System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
			num = Integer.parseInt(sc.nextLine());// sc.nextInt();
			ps.setInt(3, num);
												//sc.nextLine();

			System.out.print("주소를 입력하세요 >>> ");
			str = sc.nextLine();
			ps.setString(4, str);

			ps.execute();	//같은 데이터를 입력하면 insert 하면서 오류 발생
			}catch(Exception e) {
				System.out.println("오류 메시지 = " + e);
			}
			System.out.println("------------------------------------------------");
		}//while
		ps.close();
		sc.close();
		System.out.println("입력한 학생정보가 성공적으로 등록되었습니다..");

	}
}
