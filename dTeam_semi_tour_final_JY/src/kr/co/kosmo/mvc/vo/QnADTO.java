package kr.co.kosmo.mvc.vo;

/*
 * QNUM	NUMBER
QWRITER	VARCHAR2(30 BYTE)
QSUBJECT	VARCHAR2(100 BYTE)
QCONTENT	VARCHAR2(400 BYTE)
QDATE	DATE
 */
public class QnADTO {
	private int qnum;
	private String qwriter;
	private String qsubject;
	private String qcontent;
	private String qdate;
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getQwriter() {
		return qwriter;
	}
	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}
	public String getQsubject() {
		return qsubject;
	}
	public void setQsubject(String qsubject) {
		this.qsubject = qsubject;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	
}
