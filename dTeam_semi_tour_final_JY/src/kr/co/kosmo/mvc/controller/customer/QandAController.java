package kr.co.kosmo.mvc.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kosmo.mvc.dao.QnADaoInter;
import kr.co.kosmo.mvc.vo.QnACommDTO;
import kr.co.kosmo.mvc.vo.QnADTO;

//Q&A
@Controller
@RequestMapping(value = "/qna")
public class QandAController {
	
	@Autowired
	private QnADaoInter inter;
	
	@RequestMapping(value = "/qna")
	public ModelAndView qna(QnADTO vo) {
		ModelAndView mav = new ModelAndView("customer_qna/list");
		List<QnADTO> list = inter.list();
		mav.addObject("qlist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/wform")
	public ModelAndView wform() {
		ModelAndView mav = new ModelAndView("customer_qna/write");
		
		return mav;
	}

	@RequestMapping(value = "/write")
	public ModelAndView write(HttpSession session, QnADTO vo) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		
		String hid = (String) session.getAttribute("sessionID");
		vo.setQwriter(hid);
		inter.write(vo);
		
		return mav;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(int qnum) {
		ModelAndView mav = new ModelAndView("customer_qna/detail");
		
		QnADTO vo = inter.detail(qnum);
		mav.addObject("qvo", vo);
		
		List<QnACommDTO> list = inter.listAnswer(qnum);
		mav.addObject("alist", list);
		
		return mav;
	}
	
	@RequestMapping(value = "/aform")
	public ModelAndView aform(int qnum) {
		ModelAndView mav = new ModelAndView("customer_qna/writeAnswer");
		
		QnADTO qvo = inter.detail(qnum);
		mav.addObject("qvo", qvo);
		
		return mav;
	}
	
	@RequestMapping(value = "/answer")
	public ModelAndView answer(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		
		String hid = (String) session.getAttribute("sessionID");
		QnACommDTO vo = new QnACommDTO();
		
		vo.setAwriter(hid);
		vo.setAcode(Integer.parseInt(request.getParameter("acode")));
		vo.setAcontent(request.getParameter("acontent"));
		inter.answer(vo);
		
		return mav;
	}
	
	@RequestMapping(value = "/uform")
	public ModelAndView uform() {
		ModelAndView mav = new ModelAndView("customer_qna/update");
		
		return mav;
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		
		String hid = (String) session.getAttribute("sessionID");
		QnADTO vo = new QnADTO();
		
		vo.setQwriter(hid);
		vo.setQsubject(request.getParameter("qsubject"));
		vo.setQcontent(request.getParameter("qcontent"));
		
		inter.update(vo);
		
		return mav;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/main");
		
		String hid = (String) session.getAttribute("sessionID");
		inter.delete(hid);
		
		return mav;
	}
}
