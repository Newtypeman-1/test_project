package kr.co.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	public int ajaxCheckLogin(Member m) {
		String query = "select * from member_tbl where member_id = ? and member_pw = ?";
		Object[] params = {m.getMemberId(), m.getMemberPw()};
		List list = jdbc.query(query, memberRowMapper, params);
		int r = 0;
		if(!list.isEmpty()) {
			r = 1;
		}
		return r;
	}
}
