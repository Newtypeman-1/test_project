package kr.co.iei.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.comment.model.dao.CommentDao;
import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.doctor.model.vo.Doctor;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;

	public List allComment(Doctor doctor) {
		List allComment = commentDao.allComment(doctor);
		return allComment;
	}
	
}
