package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//모든 학생 조회
//1줄에 1개 행 출력
public class StudentSelectAllMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::::모든 학생을 조회하는 메뉴:::::::::");
		selectAllStudent(conn);
				
		OracleUtility.close(conn);
				
	}

	private static void selectAllStudent(Connection conn) {
		String sql = "select * from TBL_STUDENT";
		try(
			PreparedStatement ps = conn.prepareStatement(sql);
		){							
			ResultSet rs = ps.executeQuery();	
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());
		while(true) {
			if(rs.next())
				System.out.println("학번 : " +rs.getString(1) + ", 이름 : "+rs.getString(2)
				+", 나이 : " + rs.getString(3) +", 주소 : " + rs.getString(4));
		}
			
		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 - " + e.getMessage());
		}
	
	}
	
}
