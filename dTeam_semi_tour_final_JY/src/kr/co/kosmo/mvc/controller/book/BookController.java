package kr.co.kosmo.mvc.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kosmo.mvc.controller.service.BookService;
import kr.co.kosmo.mvc.dao.MemberDaoInter;
import kr.co.kosmo.mvc.vo.ChargeMVO;
import kr.co.kosmo.mvc.vo.LocalVO;
import kr.co.kosmo.mvc.vo.MembershipVO;
import kr.co.kosmo.mvc.vo.SendMailVO;
import kr.co.kosmo.mvc.vo.BookLoggerDTO;
import kr.co.kosmo.mvc.vo.BookVO;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	@Autowired
	private BookService booksvc;

	@Autowired
	private MemberDaoInter memberdao;

	// ���������� �̵�
	@GetMapping(value = "/bookForm")
	public ModelAndView bookForm(int lid,HttpSession session) {
		ModelAndView mav = new ModelAndView("book/bookForm");
		//�����------------------------------------
		int mnum = (int) session.getAttribute("sessionNum");
		MembershipVO mbsvo = memberdao.memMembership(mnum);
		mav.addObject("mbsvo", mbsvo);
		//��������(����)����-----------------------------------		
		LocalVO lvo = booksvc.detailLocalTotal(lid);
		mav.addObject("vo", lvo);
		return mav;
	}

	// ���� insert
	// ���� + ���� (+���������Ʈ)
	@PostMapping(value = "/bookProcess")
	public String bookProcessInsert(HttpSession session,BookVO bookvo, ChargeMVO chvo, HttpServletRequest request,
			SendMailVO mailvo) {
		// String mid = (String) session.getAttribute("sessionID");
		int mnum = (int) session.getAttribute("sessionNum");

		// System.out.println("index" + request.getParameter("index"));
		int index = Integer.parseInt(request.getParameter("index"));
		char itype = 'A';

		for (int i = 0; i <= index; i++) {
			if (i == index) {
				bookvo.setItype(String.valueOf(itype));
			}
			itype++;
		}
		// ���ڰ� SendMailVO �׽�Ʈ
		/*
		 * System.out.println("===BookController SendMail===");
		 * System.out.println("chpay =>"+mailvo.getChpay());
		 * System.out.println("Edate =>"+mailvo.getEdate());
		 * System.out.println("Sdate =>"+mailvo.getSdate());
		 * System.out.println("Memail =>"+mailvo.getMemail());
		 * System.out.println("===BookController SendMail===");
		 */
		booksvc.addBookCh(bookvo, chvo, mailvo, mnum);
		return "redirect:/main";
	}

//====================================================
	// ���� ���������� �̵�
	@GetMapping(value = "/bookUpdateForm")
	public ModelAndView bookUpdateForm(int bid,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		BookVO bvo;

		// ��¥ ����
		// uplimit ���� : 0 �������� / 1 ���������ؼ� ����
		bvo = booksvc.changeLimit(bid);
		int uplimit = bvo.getUplimit();

		if (uplimit == 0) { // ������� ����
			mav = new ModelAndView("book/bookUpdateForm");
			//�����------------------------------------
			int mnum = (int) session.getAttribute("sessionNum");
			MembershipVO mbsvo = memberdao.memMembership(mnum);
			mav.addObject("mbsvo", mbsvo);
			//���� ���� + ��������(����)����-----------------------------------		
			bvo = booksvc.updateBookForm(bid); // bid,lid,itype,sdate,edate
			mav.addObject("bvo", bvo);
			LocalVO lvo = booksvc.detailLocalTotal(bvo.getLid());
			mav.addObject("vo", lvo);
		} else if (uplimit == 1) { // ������� �Ұ���
			System.out.println("�����Ұ���! ȣ��Ʈ���� ���� �����ϼ���");
			mav = new ModelAndView("redirect:/calendar/calendarList");
		}

		return mav;
	}

	// ���� ���� : ������� �� �������� + Ķ������ �̵�
	@PostMapping(value = "/bookUpdate")
	public String bookProcessUpdate(HttpSession session,BookVO bookvo, ChargeMVO chvo, HttpServletRequest request) {
		// String mid = (String) session.getAttribute("sessionID");
		int mnum = (int) session.getAttribute("sessionNum");
		bookvo.setMid(mnum);

		// System.out.println("index" + request.getParameter("index"));
		int index = Integer.parseInt(request.getParameter("index"));
		char itype = 'A';

		for (int i = 0; i <= index; i++) {
			if (i == index) {
				bookvo.setItype(String.valueOf(itype));
			}
			itype++;
		}
		// �� ����
		booksvc.updateBookCh(bookvo, chvo, mnum);

		return "redirect:/calendar/calendarList";
	}

//====================================================
	// ���� ��� : ������� �� Ķ������ �̵� + ���������� ���� ������
	@GetMapping(value = "/delBook")
	public String bookProcessDelete(HttpSession session,BookVO bookvo) {
		// ��¥ ����=================================
		// dellimit ��� : 1�� �������� / 3�� 50%ȯ�� / 5�� 70%ȯ�� / 7�� 100%ȯ��
		BookVO bvo;
		bvo = booksvc.changeLimit(bookvo.getBid());
		int datediff = bvo.getDatediff(); // ��¥ ����
		int dellimit = bvo.getDellimit(); // ��ұݾ�
		//System.out.println("��ұݾ� : " + dellimit);

		if (datediff <= 1) { //1�� ��������
		} else if (datediff <= 3) { //3�� 50%ȯ��
		} else if (datediff <= 5) { //5�� 70%ȯ��
		} else { //7�� 100%ȯ��
			booksvc.delBook(bookvo); // 100%
		}
		return "redirect:/calendar/calendarList";
	}
}
