
public class StatisticsCalculatorCached {
	private int worstScore;
	private int bestScore;
	private String worstSubject;
	private String bestSubject;
	private long sum;
	private long numOfGrades;
	
	private class SubjectScore {
		public String subject;
		public int score;
		
		public SubjectScore(String subject, int score) {
			this.subject = subject;
			this.score = score;
		}
	}
	
	public StatisticsCalculatorCached(){
		worstSubject = getWorstSubject();
		bestSubject = getBestSubject();
		sum = 0;
		numOfGrades = 0;
	}
	
	public void addScore(int score) {
		if (score > bestScore) {
			bestScore = score;
			bestSubject = "Unknown Subject";
		}
		if (score < worstScore || worstScore == 0) {
			worstScore = score;
			worstSubject = "Unknown Subject";
		} 
		numOfGrades += 1;
		sum += score;
		
	}
	
	public void addScore(int score, String subject) {
		if (score > bestScore) {
			bestScore = score;
			bestSubject = subject;
		}
		if (score < worstScore || worstScore == 0) {
			worstScore = score;
			worstSubject = subject;
		}
		numOfGrades += 1;
		sum += score;
	}
	
	public String getBestSubject() {
		return bestSubject;
	}
	
	public String getWorstSubject() {
		return worstSubject;
	}
	
	public double getAverage() {
		if (numOfGrades == 0) return 0;
		double average = (double) sum / numOfGrades;
		return average;
	}
	
	public String getGrade() {
		double average = getAverage();
		if (average < 50) {
			return "Fail";
		}
		else if (average >= 50 && average < 65) {
			return "Pass";
		}
		else if (average >= 65 && average < 75) {
			return "Credit";
		}
		else if (average >= 75 && average < 85) {
			return "Distinction";
		}
		else {
			return "High Distinction";
		}
	}
	
	public static void main(String[] args) {
		StatisticsCalculatorCached s = new StatisticsCalculatorCached();
		s.addScore(75, "ENGG1805");
		s.addScore(64, "INFO1103");
		s.addScore(71, "INFO1003");
		s.addScore(45, "MATH1001");
		s.addScore(39, "MATH1002");
		System.out.println(s.getBestSubject());
		System.out.println(s.getWorstSubject());
		
		System.out.println(s.getAverage());
		System.out.println(s.getGrade());		
	}
}