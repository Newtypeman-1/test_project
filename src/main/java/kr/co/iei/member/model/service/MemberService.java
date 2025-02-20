package kr.co.iei.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public Member selectOneMember(Member m) {
		Member member = memberDao.selectOneMember(m);
		return member;
	}
	
	@Transactional
	public int registerMember(Member m) {
		int r = memberDao.registerMember(m);
		return r;
	}

	public Member idCheck(Member m) {
		Member member = memberDao.idCheck(m);
		return member;
	}

	@Transactional
	public int updateMember(Member m) {
		int result = memberDao.updateMember(m);
		return result;
	}

	public List selectAllMember() {
		List list = memberDao.selectAllMember();
		return list;
	}

	public Member findId(Member m) {
		Member member = memberDao.findId(m);
		return member;
	}
	
	public Member findPw(Member m) {
		Member member = memberDao.findPw(m);
		return member;
	}

	public int deleteMemeber(int memberNo) {
		int result = memberDao.deleteMember(memberNo);
		return result;
	}

	
}
