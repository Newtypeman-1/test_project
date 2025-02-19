package kr.co.iei.doctor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.doctor.model.dao.DoctorDao;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.vo.Member;
import kr.co.iei.review.model.vo.Review;

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

	public List selectAllDoctor() {
		List list2 = doctorDao.selectAllDoctor();
		return list2;
	}

	public Doctor findId(Doctor d) {
		Doctor doctor = doctorDao.findId(d);
		return doctor;
	}
	
	public Doctor findPw(Doctor d) {
		Doctor doctor = doctorDao.findPw(d);
		return doctor;
	}

	
}
