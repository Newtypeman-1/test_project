package kr.co.iei.review.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
	private int reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private String reviewWriter;
	private int doctorNo;
	private String reviewDate;
	private int reviewStar;
	private int treatmentNo;
	private String doctorName;
}
