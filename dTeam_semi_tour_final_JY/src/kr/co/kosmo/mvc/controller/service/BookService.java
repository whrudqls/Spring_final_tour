package kr.co.kosmo.mvc.controller.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kosmo.mvc.dao.MemberDao;
import kr.co.kosmo.mvc.dao.SendMailDao;
import kr.co.kosmo.mvc.dao.BookDao;
import kr.co.kosmo.mvc.vo.ChargeMVO;
import kr.co.kosmo.mvc.vo.HostVO;
import kr.co.kosmo.mvc.vo.ItemVO;
import kr.co.kosmo.mvc.vo.LocalVO;
import kr.co.kosmo.mvc.vo.MemberVO;
import kr.co.kosmo.mvc.vo.MembershipVO;
import kr.co.kosmo.mvc.vo.SendMailVO;
import kr.co.kosmo.mvc.vo.BookLoggerDTO;
import kr.co.kosmo.mvc.vo.BookVO;

@Service
public class BookService {
	@Autowired
	private BookDao bookdao;
	
	@Autowired 
	private SendMailDao smdao;


	@Autowired
	private MemberDao memdao;

//=============================================================
	// ���� �α�
	public void addBookLogging(BookLoggerDTO vo) {
		bookdao.addBookLogging(vo);
	}

//=============================================================
	// ���� ���� ����
	public LocalVO detailLocalTotal(int lid) {
		return bookdao.detailLocalTotal(lid);
	}

	// add
	// ����ó�� (����+����+���������Ʈ)
	@Transactional
	public void addBookCh(BookVO bookvo, ChargeMVO chvo, SendMailVO mailvo, int num) {
		// ����--------------------------------------------
		bookdao.addBook(bookvo);

		// ����--------------------------------------------
		bookdao.addChage(chvo);

		// ����--------------------------------------------
		smdao.textupdate(mailvo);
		
		// ����Ʈ&���������ݾ�----------------------------------
		// �����Ҷ� ���� ����Ʈ �߰� => id / point �ޱ�
		MemberVO mvo = new MemberVO();
		int mnum = num;
		mvo.setMnum(mnum);

		// point ���----------
		// �����ݾ� chvo.getChpay()
		int chpay = chvo.getChpay();
		// ��� �� ����Ʈ ��� 0.02~0.1
		MembershipVO mbsvo = memdao.mbsPoint(mnum);
		float mbspoint = mbsvo.getMbspoint();
		// �߰��� ����Ʈ : �����ݾ�*��޺� ����Ʈ ����
		int mpoint = (int) (chpay * mbspoint);
		// ����� ����Ʈ ����
		int usepoint = chvo.getUsepoint();
		//System.out.println("�������Ʈ" + usepoint);
		mpoint = mpoint - usepoint;

		//System.out.println("================����Insert================");
		//System.out.println("�����ݾ� : " + chpay);
		//System.out.println("��� �� ����Ʈ  : " + mbspoint);
		//System.out.println("�߰��� ����Ʈ(��������-���)  : " + mpoint);

		// db�� ����
		mvo.setMpoint(mpoint); //����Ʈ �߰�
		mvo.setMcharge(chpay); //�����ݾ� �߰�

		memdao.upMPoint(mvo);

		// ���������ݾ׿� ���� ����ʵ�� ����------------------------
		memdao.updateMbs(mnum);
	}

//=============================================================
	// ���� ����Ʈ ��� + �ֱ�Top3 => ��� ��Ʈ�ѷ����� ���
	public List<BookVO> listBook(BookVO bookvo) {
		return bookdao.listBook(bookvo);
	}

//=============================================================
	// ������� ���� ����
	public BookVO updateBookForm(int bid) {
		return bookdao.updateBookForm(bid);
	}

	// update
	// ����ó�� (����+����+���������Ʈ)
	@Transactional
	public void updateBookCh(BookVO bookvo, ChargeMVO chvo, int num) {
		// ����--------------------------------------------
		bookdao.updateBook(bookvo);

		// ����--------------------------------------------
		bookdao.updateChage(chvo);

		// ����Ʈ--------------------------------------------
		//1. ���� ����Ʈ ����
		// ���� ����α׿��� ���� ������ Ȯ�� => �����Ǿ��� ����Ʈ ����
		BookLoggerDTO booklogvo;
		booklogvo = delBookforLog(bookvo.getBid());
		int prechpay = -(booklogvo.getPay());
		int prespoint = -(booklogvo.getSpoint());
		int preupoint = booklogvo.getUpoint();
		System.out.println("================����Update(�������)================");
		System.out.println("�����ݾ�(�α�) : " + prechpay);
		System.out.println("��������Ʈ(�α�)  : " + prespoint);
		System.out.println("�������Ʈ(�α�)  : " + preupoint);
		
		//2. ����� �����ǿ� ���� ����Ʈ ����
		MemberVO mvo = new MemberVO();
		int mnum = bookvo.getMid();
		mvo.setMnum(mnum);

		// point ���----------
		// �����ݾ� chvo.getChpay()
		int upchpay = chvo.getChpay();
		// ��� �� ����Ʈ ��� 0.02~0.1
		MembershipVO mbsvo = memdao.mbsPoint(mnum);
		float mbspoint = mbsvo.getMbspoint();
		// �߰��� ����Ʈ : �����ݾ�*��޺� ����Ʈ ����
		int upmpoint = (int) (upchpay * mbspoint);

		// (-���� �����ݾ�) + (���� �����ݾ�)
		// (-���� ������ ����Ʈ+����ߴ�����Ʈ) + (���� ������ ���� ����Ʈ)
		int chpay = upchpay+prechpay;
		int mpoint = upmpoint+prespoint+preupoint;
		System.out.println("================����Update(����Ϸ�)================");
		System.out.println("�����ݾ� : " + chpay);
		System.out.println("��� �� ����Ʈ  : " + mbspoint);
		System.out.println("�߰��� ����Ʈ  : " + mpoint);

		// db�� ����
		mvo.setMpoint(mpoint); //����Ʈ �߰�
		mvo.setMcharge(chpay); //�����ݾ� �߰�

		memdao.upMPoint(mvo);

		// ���������ݾ׿� ���� ����ʵ�� ����------------------------
		memdao.updateMbs(mnum);
	}

//=============================================================
	// ������� �α� ����� ���� Detail => ���� �α׿��� �̾ƿ���(����/����)
	public BookLoggerDTO delBookforLog(int bid) {
		return bookdao.delBookforLog(bid);
	}
	// �������
	@Transactional
	public void delBook(BookVO bookvo) {
		// ����--------------------------------------------
		bookdao.delBook(bookvo);
		// ����--------------------------------------------
		// ���� ������ �ڵ� ����
		// ����Ʈ--------------------------------------------
		// ���� ����α׿��� ���� ������ Ȯ�� => �����Ǿ��� ����Ʈ ����
		BookLoggerDTO booklogvo;
		booklogvo = delBookforLog(bookvo.getBid());
		int chpay = -(booklogvo.getPay());
		int mpoint = -(booklogvo.getSpoint());
		int usepoint = booklogvo.getUpoint();
		System.out.println("================����Delete================");
		System.out.println("�����ݾ�(�α�) : " + chpay);
		System.out.println("����Ʈ(�α�)  : " + mpoint);
		System.out.println("����ߴ�����Ʈ  : " + usepoint);
		// ����Ʈ rollbackó��
		mpoint = mpoint + usepoint;
		System.out.println("����Ʈ(����)  : " + mpoint);

		// db�� ����
		int mnum = bookvo.getMid();
		MemberVO mvo = new MemberVO();
		mvo.setMnum(mnum);
		mvo.setMpoint(mpoint); //����Ʈ �߰�
		mvo.setMcharge(chpay); //�����ݾ� �߰�
		
		memdao.upMPoint(mvo);

		// ���������ݾ׿� ���� ����ʵ�� ����------------------------
		memdao.updateMbs(mnum);
		
	}

//=============================================================
	// ��¥ ����
	// uplimit ���� : 0 �������� / 1 ���������ؼ� ����
	// dellimit ��� : 1�� �������� / 3�� 50%ȯ�� / 5�� 70%ȯ�� / 7�� 100%ȯ��
	public BookVO changeLimit(int bid) {
		return bookdao.changeLimit(bid);
	}

//=============================================================
	// ���������� => useHistory�� ��� => MemberController
	public BookVO useHistory(int mnum) {
		return bookdao.useHistory(mnum);
	}
//=============================================================
	// ��Ʈ ���� => MemberRestController
	// ���� Ƚ�� ���
	public List<BookVO> tourCount(int mnum) {
		return bookdao.tourCount(mnum);
	}
	// �湮 ������
	public List<BookVO> visitSpot(int mnum) {
		return bookdao.visitSpot(mnum);
	}
	// �ִ� ����Ⱓ
	public List<BookVO> periodMax(int mnum) {
		return bookdao.periodMax(mnum);
	}
	// �� ���� ���
	public List<BookVO> costType(int mnum) {
		return bookdao.costType(mnum);
	}
	// ���� ���� ���
	public List<BookVO> costTotal(int mnum) {
		return bookdao.costTotal(mnum);
	}

//=============================================================
	// ��������Ʈ ���
	public List<BookVO> rankLocal() {
		return bookdao.rankLocal();
	}

	// ���� ����
	public List<ItemVO> detailItem(int lid) {
		return bookdao.detailItem(lid);
	}

	// ���� Ȯ��
	public int checkItem(ItemVO vo) {
		return bookdao.checkItem(vo);
	}

	// �����ϼ�
	public int getBookCnt(ItemVO vo) {
		return bookdao.getBookCnt(vo);
	}
//=============================================================
	// ȣ��Ʈ ManageMent : ������� ����
	public void updateBookHost(BookVO bookvo) {
		bookdao.updateBookHost(bookvo);
	}
	// ȣ��Ʈ ManageMent 1-1 : �������(���� ����Ʈ)
	public List<LocalVO> hostBookList(HostVO vo) {
		return bookdao.hostBookList(vo);
	}
	// ȣ��Ʈ ManageMent : ��ҵ� ���೻���� ���
	public List<BookLoggerDTO> hostBookDelList() {
		return bookdao.hostBookDelList();
	}

}
