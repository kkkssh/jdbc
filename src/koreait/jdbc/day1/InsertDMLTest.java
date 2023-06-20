package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//학생 성적처리 프로그램 중에 새로운 학생을 등록(입력)하는 기능을 만들어 보자.(테이블에 insert 실행)
public class InsertDMLTest {

	public static void main(String[] args) {
		

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		
		try (
			Connection conn = DriverManager.getConnection(url,user,password);
			){
					
			System.out.println("연결 상태 = " + conn);
			if(conn != null)
				System.out.println("오라클 데이터베이스 연결 성공!!");
						
			//db연결 완료 후에 sql 실행하기
			
			//insert SQL 작성 : 제약조건(기본키 stuno) 위반 되지 않는 값으로 입력하기
			String sql = "insert into TBL_STUDENT values ('2023002','김땡이',17,'경기도')";		//insert SQL 작성
			
			//PreparedStatement 객체를 생성하면서 실행할 SQL을 설정한다.
			//PreparedStatement 객체는 Connection 객체 메소드로 만든다. Connection 구현객체는 dbms 종류에 따라 생성되고
			//PreparedStatement 객체도 그에 따라 구현 객체가 결정된다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt 객체의 구현 클래스 : " + pstmt.getClass().getName());
			//oracle.jdbc.driver.OraclePreparedStatementWrapper 클래스로 객체가 생성
			
			pstmt.execute();		//PreparedStatement 객체로 execute 하면 SQL이 실행된다.
			pstmt.close();
			
						
		} catch (Exception e) {		// 모든 Exception 처리
			System.out.println("오류메세지 = "+e.getMessage());
		//	e.printStackTrace();    //Exception 발생의 모든 원인을 cascade 형식으로 출력
		}
		
		
		
	}
}

// Statement 인터페이스는 SQL 쿼리 처리와 관련된 방법을 정의한다.
// Statement 인터페이스의 객체는 SQL 쿼리문을 데이터베이스에 전송한다. Connection 객체를 통해서 만든다.

//PreparedStatement는 Statement의 자식 인터페이스
//특징은 SQL을 먼저 컴파일 하고 SQL 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식이다.











