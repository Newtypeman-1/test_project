package kr.co.iei.comment.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
	private int commentNo;
	private String commentContent;
	private String commentDate;
	private int doctorNo;
	private int boardNo;
}
