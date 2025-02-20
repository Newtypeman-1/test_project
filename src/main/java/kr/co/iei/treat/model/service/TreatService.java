package kr.co.iei.treat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.treat.model.dao.TreatDao;
import kr.co.iei.treat.model.vo.Treat;

@Service
public class TreatService {
	
	@Autowired
	private TreatDao treatDao;

	public List<Integer> selectUnavailableTimes(int doctorNo) {
		List<Integer> unavailableTimes = treatDao.selectUnavailableTimes(doctorNo);
		return unavailableTimes;
	}

	public List<Doctor> selectDoctors(int departmentNo) {
		List<Doctor> list = treatDao.selectDoctors(departmentNo);
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

	public String selectDepartmentName(int departmentNo) {
		return treatDao.selectDepartmentName(departmentNo);
	}

}
