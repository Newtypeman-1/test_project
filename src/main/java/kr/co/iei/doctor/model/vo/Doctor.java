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
}
