package koreait.jdbc.day4;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class JBuy {
	private int buy_seq;
	private String customid;
	private String pcode;
	private int quantity;
	private Date buy_date;
	
}

//필드값이 모두 같으면 equals 로 true 이 되도록 하고 싶다
// -> equals 와 hashcode 를 재정의 해야 한다.(불변객체)
// => vo 이다. vo 는 테스트 케이스에서 객체를 비교할 때 사용할 수 있다. assertEquals 비교.
