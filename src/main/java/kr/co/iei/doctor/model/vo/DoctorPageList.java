package kr.co.iei.doctor.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorPageList {
	private List list;
	private String pageNavi;
	private int memberNo;
	private int doctorNo;
}
