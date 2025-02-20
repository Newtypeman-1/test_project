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

	public Member selectOneMember(Member m) {
		String query = "select * from member_tbl where member_id=? and member_pw=?";
		Object[] params = {m.getMemberId(), m.getMemberPw()};
		List list = jdbc.query(query, memberRowMapper, params);
		System.out.println(m.getMemberId());
		System.out.println(m.getMemberPw());
		if(list.isEmpty()) {
			return null;
		}else {
			Member member = (Member)list.get(0);
			System.out.println(member);
			return member;
		}
	}

	public Member idCheck(Member m) {
		String query = "select * from member_tbl where member_id=?";
		Object[] params = {m.getMemberId()};
		List list = jdbc.query(query, memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		Member member = (Member)list.get(0);
		return member;
	}
	
	public int updateMember(Member m) {
		String query = "update member_tbl set member_pw = ?, member_phone = ?, member_email = ? where member_no = ?";
		Object[] params = {m.getMemberPw(), m.getMemberPhone(), m.getMemberEmail(), m.getMemberNo()};
		int result = jdbc.update(query, params);
		return result;
	}


	public List selectAllMember() {
		String query = "select * from member_tbl order by 1";
		List list = jdbc.query(query, memberRowMapper);
		return list;
	}

	public int registerMember(Member m) {
		String query = "insert into member_tbl values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = {m.getMemberId(), m.getMemberPw(), m.getMemberName(), m.getMemberPhone(), m.getMemberAddr(), m.getMemberEmail(), m.getMemberGender()};
		int r = jdbc.update(query, params);
		return r;
	}
	
	public Member findId(Member m) {
		String query = "select * from member_tbl where member_email=?";
		Object[] params = {m.getMemberEmail()};
		List list = jdbc.query(query, memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
	}
	
	public Member findPw(Member m) {
		String query = "select * from member_tbl where member_id=? and member_email=?";
		Object[] params = {m.getMemberId(), m.getMemberEmail()};
		List list = jdbc.query(query, memberRowMapper, params);
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
	}

	public int deleteMember(int memberNo) {
		String query = "delete from member_tbl where memberNo = ?";
		Object[] params = {memberNo};
		int result = jdbc.update(query, params);
		return result;
	}
	
}
