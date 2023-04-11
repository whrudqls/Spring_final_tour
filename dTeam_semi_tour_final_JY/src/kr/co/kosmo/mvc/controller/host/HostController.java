package kr.co.kosmo.mvc.controller.host;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kosmo.mvc.controller.service.BookService;
import kr.co.kosmo.mvc.dao.HostDaoInter;
import kr.co.kosmo.mvc.vo.BookLoggerDTO;
import kr.co.kosmo.mvc.vo.BookVO;
import kr.co.kosmo.mvc.vo.ChargeMVO;
import kr.co.kosmo.mvc.vo.HostVO;
import kr.co.kosmo.mvc.vo.LocalVO;
import kr.co.kosmo.mvc.vo.MemberVO;

@Controller
@RequestMapping(value = "/host")
public class HostController {
	@Autowired
	private HostDaoInter hostdao;

	@Autowired
	private BookService booksvc;

//========================================================================
	// ȸ������ : signupForm���� �̵�
	@GetMapping(value = "/hostSignup")
	public String hostsignUpForm() {
		return "host/hostSignup";
	}

	// ȸ������ : Form���� �ۼ��� ������ ���� => �������� �̵�
	// ��Ϲ�ư ������ ��� �����Ͱ� ����.
	@PostMapping(value = "/hsignUpProcess")
	public String hsignUpProcess(HostVO vo) {
		hostdao.addHost(vo);
		return "redirect:/main";
	}

//========================================================================
	// ȣ��Ʈ������
	@GetMapping(value = "/hostpage")
	public ModelAndView hostpage(String hid) {
		ModelAndView mav = new ModelAndView("host/hostpage");
		HostVO hvo = hostdao.hostpage(hid);
		mav.addObject("hvo", hvo);

		return mav;
	}

//========================================================================
	// ȸ�� ����
	@GetMapping(value = "/uphostForm")
	public ModelAndView uphostForm() {
		ModelAndView mav = new ModelAndView("host/uphostForm");
		return mav;
	}

	@PostMapping(value = "/hostupdate")
	public String hostupdate(HostVO vo) {
		hostdao.hostupdate(vo);
		return "redirect:/main";
	}

//========================================================================
	// ȸ��Ż�� : ȸ������ �� main���� �̵�
	@GetMapping(value = "/delhost")
	public String upmemForm(String hid, HttpSession session) {
		hostdao.delHost(hid);
		// ����� ���� ����
		session.removeAttribute("sessionHNum");
		session.removeAttribute("sessionHID");
		session.removeAttribute("sessionHName");
		return "redirect:/main";
	}

//========================================================================
	// ȣ��Ʈ ManageMent : ������� ����
	@PostMapping(value = "/updateBookHost")
	public String updateBookHost(BookVO bookvo,HttpServletRequest request) {
		booksvc.updateBookHost(bookvo);
		// HttpServletRequest reqeust referer = ���� �������� ���ư���
		String referer = request.getHeader("Referer");
		// ��θ� ���� �������� �������ֱ�
		return "redirect:" + referer;
	}

	// ȣ��Ʈ ManageMent 1-1 : �������(���� ����Ʈ) => ������
	@GetMapping(value = "/hostBookList")
	public ModelAndView hostBookList(String bstatus,HttpSession session) {
		ModelAndView mav = new ModelAndView("book/hostManageyBook");
		HostVO vo = new HostVO();
		int hno = (int) session.getAttribute("sessionHNum");
		vo.setBstatus(bstatus);
		vo.setHno(hno);
		List<LocalVO> locallist = booksvc.hostBookList(vo);

		mav.addObject("locallist", locallist);

		return mav;
	}

	// ȣ��Ʈ ManageMent : ���� ����/��� ��û(���� ����Ʈ)
	@GetMapping(value = "/hostUpBookList")
	public ModelAndView hostUpBookList(String bstatus,HttpSession session) {
		ModelAndView mav = new ModelAndView("book/hostManageyUpBook");
		HostVO vo = new HostVO();
		int hno = (int) session.getAttribute("sessionHNum");
		vo.setBstatus(bstatus);
		vo.setHno(hno);
		List<LocalVO> locallist = booksvc.hostBookList(vo);

		mav.addObject("locallist", locallist);

		return mav;
	}
	// ȣ��Ʈ ManageMent : ���� ����/��� ��û(���� ����Ʈ)
	@GetMapping(value = "/hostDelBookList")
	public ModelAndView hostDelBookList() {
		ModelAndView mav = new ModelAndView("book/hostManageyDelBook");
		List<BookLoggerDTO> dellist = booksvc.hostBookDelList();
		mav.addObject("locallist", dellist);

		return mav;
	}
	
//========================================================================

}