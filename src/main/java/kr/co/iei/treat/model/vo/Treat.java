package kr.co.iei.treat.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Treat {
	private int treatmentNo;
	private String appointDate;
	private int appointTime;
	private int payAmount;
	private String isDone;
	private String opinion;
	private int memberNo;
	private int doctorNo;
}