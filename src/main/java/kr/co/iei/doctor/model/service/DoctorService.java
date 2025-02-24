package kr.co.iei.doctor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.doctor.model.dao.DoctorDao;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.doctor.model.vo.DoctorPageList;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.member.model.vo.MemberPageList;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.treat.model.vo.Treat;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;

	public Doctor selectOneDoctor(Doctor d) {
		Doctor doctor = doctorDao.selectOneDoctor(d);
		return doctor;
	}
	
	@Transactional
	public int updateDoctor(Doctor d) {
		int result = doctorDao.updateDoctor(d);
		return result;
	}

	public Doctor findId(Doctor d) {
		Doctor doctor = doctorDao.findId(d);
		return doctor;
	}
	
	public Doctor findPw(Doctor d) {
		Doctor doctor = doctorDao.findPw(d);
		return doctor;
	}

	public DoctorPageList allMedicalRecords(Doctor doctor, int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage +1;
		
		List list = doctorDao.allMedicalRecords(doctor, start, end);
		
		int totalCount = doctorDao.memberSelectCount(doctor);
		
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
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage=1'>";
			pageNavi += pageF;
			pageNavi += "</a></li>";
			pageNavi += "<div>...</div>";
		}
		if(pageMM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage="+pageMM+"'>";
			pageNavi += pageMM;
			pageNavi += "</a></li>";
		}
		if(pageM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage="+pageM+"'>";
			pageNavi += pageM;
			pageNavi += "</a></li>";
		}
		pageNavi += "<li>";
		pageNavi += "<input type='text' value='"+pageNo+"' class='page-item' id='now-page'>";
		pageNavi += "</li>";
		if(pageP > 0 && pageP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage="+pageP+"'>";
			pageNavi += pageP;
			pageNavi += "</a></li>";
		}
		if(pagePP > 0 && pagePP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage="+pagePP+"'>";
			pageNavi += pagePP;
			pageNavi += "</a></li>";
		}
		if(reqPage < totalPage-2){
			pageNavi += "<div>...</div>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/doctor/myMedicalRecordsPageFrm?reqPage="+pageE+"'>";
			pageNavi += pageE;
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		DoctorPageList dpl = new DoctorPageList(list, pageNavi, 0, 0);
		
		return dpl;
	}

	public Treat selectOpinion(int treatmentNo, int doctorNo) {
		Treat t = doctorDao.selectOpinion(treatmentNo, doctorNo);
		return t;
	}
	
	@Transactional
	public int updateOpinion(Treat t, Doctor doctor) {
		int r = doctorDao.updateOpinion(t, doctor);
		return r;
	}

	public DoctorPageList allDoctorList(int i, int j) {
		int numPerPage = 10;
		int end = j * numPerPage;
		int start = end - numPerPage +1;
		
		List list2 = doctorDao.selectAllDoctor();
		
		int totalCount = doctorDao.memberTotalCount();
		
		int totalPage = totalCount/numPerPage;
		if(totalCount%numPerPage != 0) {
			totalPage += 1;
		}
		
		int pageF = 1;
		int pageMM = j-2;
		int pageM = j-1;
		int doctorNo = j;
		int pageP = j+1;
		int pagePP = j+2;
		int pageE = totalPage;
		String pageNavi = "<ul class='pageNavi'>";
		if(j > 3){
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage=1'>";
			pageNavi += pageF;
			pageNavi += "</a></li>";
			pageNavi += "<div>...</div>";
		}
		if(pageMM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage="+pageMM+"'>";
			pageNavi += pageMM;
			pageNavi += "</a></li>";
		}
		if(pageM > 0) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage="+pageM+"'>";
			pageNavi += pageM;
			pageNavi += "</a></li>";
		}
		pageNavi += "<li>";
		pageNavi += "<input type='text' value='"+doctorNo+"' class='page-item' id='now-page'>";
		pageNavi += "</li>";
		if(pageP > 0 && pageP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage="+pageP+"'>";
			pageNavi += pageP;
			pageNavi += "</a></li>";
		}
		if(pagePP > 0 && pagePP <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage="+pagePP+"'>";
			pageNavi += pagePP;
			pageNavi += "</a></li>";
		}
		if(j < totalPage-2){
			pageNavi += "<div>...</div>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/allMember?memberPage="+i+"&doctorPage="+pageE+"'>";
			pageNavi += pageE;
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		DoctorPageList dpl = new DoctorPageList(list2, pageNavi, i, doctorNo);
		
		return dpl;
	}

	
}
