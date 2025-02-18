package kr.co.iei.doctor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.doctor.model.dao.DoctorDao;
import kr.co.iei.doctor.model.vo.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;

	public Doctor selectOneDoctor(Doctor d) {
		Doctor doctor = doctorDao.selectOneDoctor(d);
		return doctor;
	}

}
