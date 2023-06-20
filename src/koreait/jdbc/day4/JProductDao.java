package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JProductDao {
	//2. 상품 목록 보기
	public List<JProduct> selectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_product";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<JProduct> results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			JProduct dto = new JProduct(rs.getString(1), 
										rs.getString(2),
										rs.getString(3),
										rs.getInt(4));
			results.add(dto);
		}
		ps.close();
		connection.close();
		return results;
		
	}
	
	//3. 상품명으로 검색하기 (유사검색 -> '검색어가 포함된 상품명' 을 목록으로(list) 조회하기)
	public List<JProduct> selectByPname(String pname) throws SQLException {
		//list 를 쓰면 여러행 조회, 안쓰면 하나의 행만 조회됨
		//pname 은 사용자가 입력한 검색어
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_product "
				+"where pname like '%' || ? || '%'";	//like는 유사 비교. % 기호 사용
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, pname);
		
		ResultSet rs = ps.executeQuery();
		List<JProduct> result = new ArrayList<>();
		
		while (rs.next()) {
			JProduct dto = new JProduct(rs.getString(1), 
										rs.getString(2),
										rs.getString(3),
										rs.getInt(4));
			result.add(dto);
		}		
		return result;
		
	}

}
