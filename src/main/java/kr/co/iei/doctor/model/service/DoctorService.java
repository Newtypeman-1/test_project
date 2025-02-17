package kr.co.iei.doctor.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.doctor.model.dao.DoctorDao;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;
}
