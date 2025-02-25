package kr.co.iei.treat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Symptom {
	private int symptomNo;
	private String symptomName;
	private int departmentNo;
	private String symptomImg;
}
