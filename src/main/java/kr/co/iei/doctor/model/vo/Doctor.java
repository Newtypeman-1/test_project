package kr.co.iei.doctor.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
	private int doctorNo;
	private String doctorId;
	private String doctorPw;
	private String doctorEmail;
	private String doctorName;
	private String doctorPhone;
	private String doctorImg;
	private int departmentNo;
	
	// 여기서부터는 doctor_tbl 테이블에 있진 않은 것들입니다
	private String departmentName;
	private int reviewTotal;
	private double reviewRating;
	
	private int appointTime;
}
