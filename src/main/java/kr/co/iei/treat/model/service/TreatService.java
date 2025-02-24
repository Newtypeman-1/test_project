package kr.co.iei.treat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.treat.model.dao.TreatDao;
import kr.co.iei.treat.model.vo.Department;
import kr.co.iei.treat.model.vo.Treat;

@Service
public class TreatService {
	
	@Autowired
	private TreatDao treatDao;

	public List<Integer> selectUnavailableTimes(int doctorNo) {
		List<Integer> unavailableTimes = treatDao.selectUnavailableTimes(doctorNo);
		return unavailableTimes;
	}

	public List<Doctor> selectDoctorsWithRating(int departmentNo) {
		List<Doctor> list = treatDao.selectDoctorsWithRating(departmentNo);
		return list;
	}

	public Doctor selectOneDoctor(int doctorNo) {
		Doctor doctor = treatDao.selectOneDoctor(doctorNo);
		return doctor;
	}

	@Transactional
	public int insertTreatment(Treat t) {
		int result = treatDao.insertTreatment(t);
		return result;
	}

	public Department selectDepartment(int departmentNo) {
		return treatDao.selectDepartment(departmentNo);
	}

	public List selectAllDepartment() {
		List list = treatDao.selectAllDepartment();
		return list;
	}

	public List<Doctor> selectDoctorsWithSchedule(int departmentNo) {
		List list = treatDao.selectDoctorsWithSchedule(departmentNo);
		return list;
	}

	public List allSchedule() {
		List list = treatDao.allSchedule();
		return list;
	}

}
