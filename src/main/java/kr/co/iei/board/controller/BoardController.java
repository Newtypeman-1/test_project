package kr.co.iei.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.board.model.service.BoardService;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.comment.model.vo.Comment;
import kr.co.iei.doctor.model.service.DoctorService;
import kr.co.iei.doctor.model.vo.Doctor;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Value(value="${file.root}")
	private String root;
	
	@GetMapping(value="/boardList")
	public String boardList(Model mod) {
		//비동기 게시판 로딩
		//일단 게시글 수 받아오기
		int totalCount = boardService.selectBoardTotalCount();
		mod.addAttribute("totalCount", totalCount);
		return "board/boardList";
	}

	@GetMapping(value="/writeFrm")
	public String writeFrm(@SessionAttribute Member member) {
		return "board/writeFrm";
	}
	
	@PostMapping(value="write")
	public String boardWrite(Board b, @SessionAttribute Member member) {
		int result = boardService.insertBoard(b, member);
		return "redirect:/board/boardList";
	}
	
	@ResponseBody
	@GetMapping(value="/more")
	public List<Board> boardMore(int start, int amount){
		List<Board> list = boardService.selectBoardList(start, amount);
		return list;
	}
	@GetMapping(value="/view")
	public String boardView(int boardNo, @SessionAttribute(required=false) Member member, Model model) {
		Board b = boardService.selectBoard(boardNo);
		model.addAttribute("board", b);
		return "board/view";
	}
	@PostMapping(value="/writeComment")
	public String writeComment(Comment c) {
		int result = boardService.writeComment(c);
		return "redirect:/board/view?boardNo="+c.getBoardNo();
	}
	@GetMapping(value="/commentWriteFrm")
	public String commentWriteFrm() {
		return "board/commentWriteFrm";
	}
}
