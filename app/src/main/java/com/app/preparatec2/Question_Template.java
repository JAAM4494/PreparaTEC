package com.app.preparatec2;


public class Question_Template {
	private int ID;
	private String QUESTION;
	private String opcA;
	private String opcB;
	private String opcC;
	private String opcD;
	private String opcE;
	private String ANSWER;

	public Question_Template()
	{
		ID=0;
		QUESTION="";
		opcA="";
		opcB="";
		opcC="";
		ANSWER="";
	}

	public Question_Template(String pPregunta, String pOpcA, String pOpcB, String pOpcC,
							 String pOpcD, String pOpcE, String pResp) {

		QUESTION = pPregunta;
		opcA = pOpcA;
		opcB = pOpcB;
		opcC = pOpcC;
		opcD=pOpcD;
		opcE=pOpcE;
		ANSWER = pResp;
	}
	public int getID()
	{
		return ID;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getOpcA() {
		return opcA;
	}
	public String getOpcB() {
		return opcB;
	}
	public String getOpcC() {
		return opcC;
	}
	public String getOpcD() {
		return opcD;
	}
	public String getOpcE() {
		return opcE;
	}

	public String getANSWER() {
		return ANSWER;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setOpcA(String pOpcA) {
		opcA = pOpcA;
	}
	public void setOpcB(String pOpcB) {
		opcB = pOpcB;
	}
	public void setOpcC(String pOpcC) {
		opcC = pOpcC;
	}
	public void setOpcD(String pOpcD) {
		opcD = pOpcD;
	}
	public void setOpcE(String pOpcE) {
		opcE = pOpcE;
	}
	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}
}
