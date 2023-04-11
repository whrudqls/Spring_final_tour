package kr.co.kosmo.mvc.dao;

import java.util.List;

import kr.co.kosmo.mvc.vo.QnACommDTO;
import kr.co.kosmo.mvc.vo.QnADTO;

public interface QnADaoInter {
	// ==============================================
	public void write(QnADTO vo);
	public List<QnADTO> list();
	public QnADTO detail(int qnum);
	public void answer(QnACommDTO vo);
	public List<QnACommDTO> listAnswer(int qnum);
	public void update(QnADTO vo);
	public void delete(String pid);
}
