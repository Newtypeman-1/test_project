package kr.co.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberId(rs.getString("member_id"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberName(rs.getString("member_name"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberAddr(rs.getString("member_addr"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setMemberGender(rs.getString("member_gender"));
		m.setEnrollDate(rs.getString("enroll_date"));
		return m;
	}

}
