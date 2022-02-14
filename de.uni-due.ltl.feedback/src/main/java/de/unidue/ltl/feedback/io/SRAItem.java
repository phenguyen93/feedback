package de.unidue.ltl.feedback.io;

public class SRAItem {

	private String promptId;
	private String question;	
	private String targetAnswer;
	private String answer;
	private String feedback;
	private String label;
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(promptId);
		sb.append("-");
/*		sb.append(question);
		sb.append(" ");
		sb.append(targetAnswer);
		sb.append(" ");
*/		
		String subStringText = answer.length() > 15 ? answer.substring(0, 15) : answer.substring(0, answer.length());
		sb.append(subStringText);
		sb.append(label);
		sb.append(" ");
		String subStringText2 = feedback.length() > 15 ? feedback.substring(0, 15) : feedback.substring(0, feedback.length());
		sb.append(subStringText2);
		sb.append(" ...");
		return sb.toString();        
	}

	
	public SRAItem(String promptId, String question, String targetAnswer, String answer, String feedback,
			String label) {
		super();
		this.promptId = promptId;
		this.question = question;
		this.targetAnswer = targetAnswer;
		this.answer = answer;
		this.feedback = feedback;
		this.label = label;
	}
	public String getPromptId() {
		return promptId;
	}
	public void setPromptId(String promptId) {
		this.promptId = promptId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTargetAnswer() {
		return targetAnswer;
	}
	public void setTargetAnswer(String targetAnswer) {
		this.targetAnswer = targetAnswer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
	
}
