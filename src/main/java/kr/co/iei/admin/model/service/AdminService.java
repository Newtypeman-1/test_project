package kr.co.iei.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.admin.model.dao.AdminDao;
import kr.co.iei.doctor.model.vo.Doctor;

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

	public List allSchedule() {
		List list = adminDao.allSchedule();
		return list;
	}
}
