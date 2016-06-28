package gotouch.app.preparatec;


/**
 * Clase plantilla de las preguntas con sus opciones y respuestas.
 */
public class Question_Template {
	private int ID;
	private String QUESTION;
	private String firstOpt;
	private String SecondOpt;
	private String ThirdOpt;
	private String FourthOpt;
	private String FifthOpt;
	private String Answer;

	public Question_Template()
	{
		ID			= 0;
		QUESTION	= "";
		firstOpt = "";
		SecondOpt = "";
		ThirdOpt = "";
		Answer = "";
	}

	public Question_Template(String pPregunta, String pOpcA, String pOpcB, String pOpcC,
							 String pOpcD, String pOpcE, String pResp) {

		QUESTION = pPregunta;
		firstOpt = pOpcA;
		SecondOpt = pOpcB;
		ThirdOpt = pOpcC;
		FourthOpt =pOpcD;
		FifthOpt =pOpcE;
		Answer = pResp;
	}
	public int getID()
	{
		return ID;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getFirstOpt() {
		return firstOpt;
	}
	public String getSecondOpt() {
		return SecondOpt;
	}
	public String getThirdOpt() {
		return ThirdOpt;
	}
	public String getFourthOpt() {
		return FourthOpt;
	}
	public String getFifthOpt() {
		return FifthOpt;
	}

	public String getAnswer() {
		return Answer;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setFirstOpt(String pOpcA) {
		firstOpt = pOpcA;
	}
	public void setSecondOpt(String pOpcB) {
		SecondOpt = pOpcB;
	}
	public void setThirdOpt(String pOpcC) {
		ThirdOpt = pOpcC;
	}
	public void setFourthOpt(String pOpcD) {
		FourthOpt = pOpcD;
	}
	public void setFifthOpt(String pOpcE) {
		FifthOpt = pOpcE;
	}
	public void setAnswer(String aNSWER) {
		Answer = aNSWER;
	}
}
