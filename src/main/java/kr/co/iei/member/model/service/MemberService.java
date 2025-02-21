package kr.co.iei.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberPageList;
import kr.co.iei.treat.model.vo.Treat;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public Member selectOneMember(Member m) {
		Member member = memberDao.selectOneMember(m);
		return member;
	}
	
	@Transactional
	public int registerMember(Member m, String memberEmail) {
		int r = memberDao.registerMember(m, memberEmail);
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

	public MemberPageList allMedicalRecords(Member member, int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage +1;
		
		List list = memberDao.allMedicalRecords(member, start, end);
		
		int totalCount = memberDao.memberTotalCount(member);
		
		int totalPage = totalCount/numPerPage;
		if(totalCount%numPerPage != 0) {
			totalPage += 1;
		}
		
		int pageF = 1;
		int pageMM = reqPage-2;
		int pageM = reqPage-1;
		int pageNo = reqPage;
		int pageP = reqPage+1;
		int pagePP = reqPage+2;
		int pageE = totalPage;
		String pageNavi = "<ul class='pageNavi'>";
		if(reqPage > 3){
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage=1'>";
			pageNavi += pageF;
			pageNavi += "</a></li>";
			pageNavi += "<div>...</div>";
		}
		if(pageMM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage="+pageMM+"'>";
			pageNavi += pageMM;
			pageNavi += "</a></li>";
		}
		if(pageM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage="+pageM+"'>";
			pageNavi += pageM;
			pageNavi += "</a></li>";
		}
		pageNavi += "<li>";
		pageNavi += "<input type='text' value='"+pageNo+"' class='page-item' id='now-page'>";
		pageNavi += "</li>";
		if(pageP > 0 && pageP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage="+pageP+"'>";
			pageNavi += pageP;
			pageNavi += "</a></li>";
		}
		if(pagePP > 0 && pagePP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage="+pagePP+"'>";
			pageNavi += pagePP;
			pageNavi += "</a></li>";
		}
		if(reqPage < totalPage-2){
			pageNavi += "<div>...</div>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/member/myMedicalRecordsPageFrm?reqPage="+pageE+"'>";
			pageNavi += pageE;
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		MemberPageList mpl = new MemberPageList(list, pageNavi);
		
		return mpl;
	}
	
	@Transactional
	public int deleteMemeber(int memberNo) {
		int result = memberDao.deleteMember(memberNo);
		return result;
	}


	public Treat selectOpinion(int treatmentNo, int memberNo) {
		Treat t = memberDao.selectOpinion(treatmentNo, memberNo);
		return t;
	}

	
}
