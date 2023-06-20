package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

public class JCustomerDao {
	
	//1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
	public JCustomer selectByID(String custom_id) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_custom where custom_id = ? ";	//PK조회 : 결과 행 0 또는 1개
		PreparedStatement ps = connection.prepareStatement(sql);
		//Statement : SQL/ Prepared : SQL 이 미리 컴파일되어 준비된
		//PreparedStatement 는 Statement 인터페이스와 비교할 수 있다.
		//Statement 인터페이스 : SQL 실행에 필요한 데이터를 동시에 포함시켜서 컴파일을 한다.
		
		ps.setString(1, custom_id);
		//준비된 SQL 에 파라미터를 전달하여
		ResultSet rs = ps.executeQuery();
		//select 쿼리를 실행
		
		JCustomer result = null;
		if(rs.next()) {
//			result = new JCustomer(rs.getString(1),
//							rs.getString(2),
//							rs.getString(3),
//							rs.getString(4),
//							rs.getString(5));
			
			String name = rs.getString(2);
			String email = rs.getString(3);
			int age = rs.getInt(4);
			Date reg_date = rs.getDate(5);
			result = new JCustomer(custom_id, name, email, age, reg_date);
					
		}
		ps.close();
		connection.close();

		return result;
		
	}

}
