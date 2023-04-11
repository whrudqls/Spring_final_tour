package kr.co.kosmo.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.kosmo.mvc.vo.ChargeMVO;
import kr.co.kosmo.mvc.vo.HostVO;
import kr.co.kosmo.mvc.vo.ItemVO;
import kr.co.kosmo.mvc.vo.LocalVO;
import kr.co.kosmo.mvc.vo.MemberVO;
import kr.co.kosmo.mvc.vo.QnACommDTO;
import kr.co.kosmo.mvc.vo.QnADTO;
import kr.co.kosmo.mvc.vo.BookLoggerDTO;
import kr.co.kosmo.mvc.vo.BookVO;

@Repository
public class QnADao implements QnADaoInter {
	@Autowired
	private SqlSessionTemplate ss;

	@Override
	public void write(QnADTO vo) {
		// TODO Auto-generated method stub
		ss.insert("qna.write", vo);
	}

	@Override
	public List<QnADTO> list() {
		// TODO Auto-generated method stub
		return ss.selectList("qna.list");
	}

	@Override
	public QnADTO detail(int qnum) {
		// TODO Auto-generated method stub
		return ss.selectOne("qna.detail", qnum);
	}

	@Override
	public void answer(QnACommDTO vo) {
		// TODO Auto-generated method stub
		ss.insert("qna.answer", vo);
	}

	@Override
	public List<QnACommDTO> listAnswer(int qnum) {
		// TODO Auto-generated method stub
		return ss.selectList("qna.listAnswer", qnum);
	}

	@Override
	public void update(QnADTO vo) {
		// TODO Auto-generated method stub
		ss.update("qna.update", vo);
	}

	@Override
	public void delete(String pid) {
		// TODO Auto-generated method stub
		ss.delete("qna.delete", pid);
	}
	
	
}
