package kr.co.kosmo.mvc.vo;
/*
 * ANUM	NUMBER
ACODE	NUMBER
AWRITER	VARCHAR2(30 BYTE)
ACONTENT	VARCHAR2(400 BYTE)
ADATE	DATE
 */
public class QnACommDTO {
	private int anum;
	private int acode;
	private String awriter;
	private String acontent;
	private String adate;
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public int getAcode() {
		return acode;
	}
	public void setAcode(int acode) {
		this.acode = acode;
	}
	public String getAwriter() {
		return awriter;
	}
	public void setAwriter(String awriter) {
		this.awriter = awriter;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getAdate() {
		return adate;
	}
	public void setAdate(String adate) {
		this.adate = adate;
	}
	
	
}
