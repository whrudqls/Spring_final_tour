package kr.co.kosmo.mvc.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.kosmo.mvc.controller.service.BookService;
import kr.co.kosmo.mvc.dao.MemberDaoInter;
import kr.co.kosmo.mvc.vo.BookLoggerDTO;
import kr.co.kosmo.mvc.vo.BookVO;
import kr.co.kosmo.mvc.vo.ChargeMVO;
import kr.co.kosmo.mvc.vo.MembershipVO;

@Component // controller�� �ƴ� �Ϲ� �� ����
@Aspect // AOP_Advice : Around,Before..��밡��
public class BookAdvice {

	@Autowired
	private BookService bookdao;

	@Autowired
	private MemberDaoInter memdao;

	@Around("execution(* kr.co.kosmo.mvc.controller.book.Book*.bookProcess*(..))")
	public String bookLogger(ProceedingJoinPoint jp) {
		String rpath = null;

		// 1) �޼����� �̸��� ������ ����/����/���� �����Ѵ�.
		String methodName = jp.getSignature().getName();
		// System.out.println("methodName: " + methodName);

		// 2) JoinPoint�κ��� �޼����� Ÿ�� ��ü�� �޼��� ���ڰ� �޾ƿ���
		// BookVO bookvo,
		// ChargeMVO chvo,
		// HttpServletRequest request,
		// HttpSession session
		// SendMailVO mailvo...
		Object[] fd = jp.getArgs();

		// 3) �޼��忡 ���� ����/����/���� ���п� ���� ó��
		if (methodName.equals("bookProcessInsert")) { // 3-1) ����
			// =======================================
			try {
				rpath = (String) jp.proceed(); // ���� �޼��� ȣ��
			} catch (Throwable e) {
				e.printStackTrace();
			}
			// =======================================
			// [1]BookVO�� ��������
			// [2]ChargeMVO�� ��������
			BookVO bookvo = null;
			ChargeMVO chvo = null;
			for (Object arg : fd) {
				if (arg instanceof BookVO) {
					bookvo = (BookVO) arg;
				} else if (arg instanceof ChargeMVO) {
					chvo = (ChargeMVO) arg;
				}
			}
			// [3]HttpServletRequest�� ��������
			HttpServletRequest request = (HttpServletRequest) fd[3];
			// [0]session�� ��������
			HttpSession session = (HttpSession) fd[0];
			String mid = (String) session.getAttribute("sessionID");
			// System.out.println("mid: " + mid);

			int bid = 0;
			int pay = 0;
			int spoint = 0;
			int upoint = 0;
			// �����ݾ� + ���� ����Ʈ �� �����ϱ�
			if (bookvo != null && chvo != null) {
				bid = chvo.getChno(); // bid = chno�̹Ƿ� �������� ��� �̾ƿ���
				pay = chvo.getChpay();
				upoint = chvo.getUsepoint();
				int mnum = (int) request.getSession().getAttribute("sessionNum");
				MembershipVO mbsvo = memdao.mbsPoint(mnum);
				float mbspoint = mbsvo.getMbspoint();
				spoint = (int) (pay * mbspoint);

				// System.out.println("================�����α�(����)================");
				// System.out.println("bid �� : " + bid);
				// System.out.println("���� �� : " + pay);
				// System.out.println("����Ʈ �� : " + spoint);
				// System.out.println("�������Ʈ �� : " + upoint);
			}

			// =========���� ������ ������ DB�� �ֱ�(����)=============
			BookLoggerDTO vo = new BookLoggerDTO();
			vo.setBid(bid);
			vo.setMid(mid);
			vo.setStatus("����");
			vo.setPay(pay);
			vo.setSpoint(spoint);
			vo.setUpoint(upoint);
			bookdao.addBookLogging(vo);
		} else if (methodName.equals("bookProcessUpdate")) { // 3-2) ����
			// =======================================
			try {
				rpath = (String) jp.proceed(); // ������� �޼��� ȣ��
			} catch (Throwable e) {
				e.printStackTrace();
			}
			// =======================================
			// [1]BookVO�� ��������
			// [2]ChargeMVO�� ��������
			BookVO bookvo = null;
			ChargeMVO chvo = null;
			for (Object arg : fd) {
				if (arg instanceof BookVO) {
					bookvo = (BookVO) arg;
				} else if (arg instanceof ChargeMVO) {
					chvo = (ChargeMVO) arg;
				}
			}
			// [3]HttpServletRequest�� ��������
			HttpServletRequest request = (HttpServletRequest) fd[3];
			// [0]session�� ��������
			HttpSession session = (HttpSession) fd[0];
			String mid = (String) session.getAttribute("sessionID");
			// System.out.println("mid: " + mid);

			int bid = 0;
			int pay = 0;
			int spoint = 0;
			int upoint = 0;
			// �����ݾ� + ���� ����Ʈ �� �����ϱ�
			if (bookvo != null && chvo != null) {
				bid = bookvo.getBid(); // bid = chno�̹Ƿ� �������� ��� �̾ƿ���
				pay = chvo.getChpay();
				upoint = chvo.getUsepoint();
				int mnum = (int) request.getSession().getAttribute("sessionNum");
				MembershipVO mbsvo = memdao.mbsPoint(mnum);
				float mbspoint = mbsvo.getMbspoint();
				spoint = (int) (pay * mbspoint);

				 System.out.println("================�����α�(����)================");
				 System.out.println("bid �� : " + bid);
				 System.out.println("���� �� : " + pay);
				 System.out.println("����Ʈ �� : " + spoint);
				 System.out.println("�������Ʈ �� : " + upoint);
			}

			// =========���� ������ ������ DB�� �ֱ�(����)=============
			BookLoggerDTO vo = new BookLoggerDTO();
			vo.setBid(bid);
			vo.setMid(mid);
			vo.setStatus("����");
			vo.setPay(pay);
			vo.setSpoint(spoint);
			vo.setUpoint(upoint);
			bookdao.addBookLogging(vo);
		} else if (methodName.equals("bookProcessDelete")) { // 3-3) ���
			// =======================================
			try {
				rpath = (String) jp.proceed(); // ��� �޼��� ȣ��
			} catch (Throwable e) {
				e.printStackTrace();
			}
			// =======================================
			// [1]BookVO�� ��������
			BookVO bookvo = null;
			for (Object arg : fd) {
				if (arg instanceof BookVO) {
					bookvo = (BookVO) arg;
				}
			}
			// bid�� Ȱ���Ͽ� ���� �ֽ� ��� ��������
			BookLoggerDTO booklogvo;
			booklogvo = bookdao.delBookforLog(bookvo.getBid());

			// [0]session�� ��������
			HttpSession session = (HttpSession) fd[0];
			String mid = (String) session.getAttribute("sessionID");

			// ��ұݾ� + ���� ����Ʈ �� �����ϱ�
			int bid = bookvo.getBid();
			int pay = booklogvo.getPay();
			int spoint = booklogvo.getSpoint();
			int upoint = 0;
			//System.out.println("================�����α�(���)================");
			//System.out.println("bid �� : " + bid);
			//System.out.println("���� �� : " + pay);
			//System.out.println("����Ʈ �� : " + bpoint);

			// =========���� ������ ������ DB�� �ֱ�(���)=============
			BookLoggerDTO vo = new BookLoggerDTO();
			vo.setBid(bid);
			vo.setMid(mid);
			vo.setStatus("���");
			vo.setPay(pay);
			vo.setSpoint(spoint);
			vo.setUpoint(upoint);
			bookdao.addBookLogging(vo);
		}
		return rpath;
	}

}
