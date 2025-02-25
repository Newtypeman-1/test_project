package kr.co.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.admin.model.dao.AdminDao;
import kr.co.iei.admin.model.vo.AdminPageList;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.MemberPageList;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	@Transactional
	public int insertDoctor(Doctor d) {
		int result = adminDao.insertDoctor(d);
		return result;
		
	}
	
	@Transactional
	public int deleteDoctor(Doctor d) {
		int r = adminDao.deleteDoctor(d);
		return r;
	}

	public AdminPageList allSchedule(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage +1;
		
		List list = adminDao.allSchedule(start, end);
		
		int totalCount = adminDao.scheduleTotalCount();
		
		int totalPage = totalCount/numPerPage;
		if(totalCount%numPerPage != 0) {
			totalPage += 1;
		}
		
		int pageF = 1;
		int pageMM = reqPage-2;
		int pageM = reqPage-1;
		int scheduleNo = reqPage;
		int pageP = reqPage+1;
		int pagePP = reqPage+2;
		int pageE = totalPage;
		String pageNavi = "<ul class='pageNavi'>";
		if(reqPage > 3){
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage=1'>";
			pageNavi += pageF;
			pageNavi += "</a></li>";
			pageNavi += "<div>...</div>";
		}
		if(pageMM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage="+pageMM+"'>";
			pageNavi += pageMM;
			pageNavi += "</a></li>";
		}
		if(pageM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage="+pageM+"'>";
			pageNavi += pageM;
			pageNavi += "</a></li>";
		}
		pageNavi += "<li>";
		pageNavi += "<div class='page-item' id='now-page'>"+scheduleNo+"</div>";
		pageNavi += "</li>";
		if(pageP > 0 && pageP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage="+pageP+"'>";
			pageNavi += pageP;
			pageNavi += "</a></li>";
		}
		if(pagePP > 0 && pagePP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage="+pagePP+"'>";
			pageNavi += pagePP;
			pageNavi += "</a></li>";
		}
		if(reqPage < totalPage-2){
			pageNavi += "<div>...</div>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/schedule?reqPage="+pageE+"'>";
			pageNavi += pageE;
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		AdminPageList apl = new AdminPageList(list, pageNavi);
		
		return apl;
	}
}
