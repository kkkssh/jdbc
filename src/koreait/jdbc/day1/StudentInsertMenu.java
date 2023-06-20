package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentInsertMenu {
	
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		String num;
		String name;
		int age;
		String area;
				
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
			){
						
				System.out.println("연결 상태 = " + conn);
				if(conn != null)
					System.out.println("오라클 데이터베이스 연결 성공!!");
							
			String sql = "insert into TBL_STUDENT values (?,?,?,?)";		//insert SQL 작성
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				while(run) {
					System.out.println("::::::::::::학생 등록 메뉴 입니다.:::::::::::");
					System.out.println("학생번호 입력시 0000 입력은 종료입니다.");
					System.out.println("학생 번호 입력>>>>");
					num = sc.nextLine();
					if(num.equals("0000")) { run = false; break;}
					pstmt.setString(1, num);	
					
					System.out.println("이름 입력>>>>");
					name = sc.nextLine();
					pstmt.setString(2, name);
					
					System.out.println("나이 입력>>>>");
					age = Integer.parseInt(sc.nextLine());	//sc.nextInt();
					pstmt.setInt(3, age);
															//	sc.nextLine();		//sc.nextInt(); 때문에
					System.out.println("사는 지역 입력(ex.경기도)>>>>");
					area = sc.nextLine();
					pstmt.setString(4, area);
					
					pstmt.execute();	//PreparedStatement 객체로 execute 하면 'SQL이 실행'된다.
				}
														
				pstmt.close();
				
				System.out.println("정상적으로 새로운 학생이 입력 되었습니다!!");
										
			} catch (Exception e) {		// 모든 Exception 처리
				System.out.println("오류메세지 = "+e.getMessage());
			//	e.printStackTrace();    //Exception 발생의 모든 원인을 cascade 형식으로 출력
			}
	
	}

}
