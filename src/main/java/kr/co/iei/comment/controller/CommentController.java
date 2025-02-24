package kr.co.iei.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.comment.model.service.CommentService;
import kr.co.iei.comment.model.vo.Comment;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BoardService boardService;
	

}
