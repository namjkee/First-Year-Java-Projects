import java.util.ArrayList;
import java.util.List;

public class SpeciesDetector<E> {
	private Tree<String> t;
	private Node<String> currentQ;
	private boolean started = false;
	private boolean finished = false;
	private boolean answer = false;
	
	public SpeciesDetector(Tree<String> t) {
		this.t = t;
	}
	
	public String getNextQuestion() {
		// special case handling
		if (t.isEmpty()) return null;
		
		// normal function
		if (!started) {
			started = true;
			currentQ = t.getRoot();
			String first = t.getRoot().getElement().toString();	// returns the first question
			return first;
		}
		else {
			List<Node<String>> children = currentQ.getChildren();
			String question;
			if (answer) {
				currentQ = children.get(0);
				question = currentQ.getElement().toString();
				return question;
			}
			else {
				currentQ = children.get(1);
				question = currentQ.getElement().toString();
				return question;
			}
		}
	}
	
	public boolean isSpecies(Node<String> currentQuestion) {
		List<Node<String>> children = currentQuestion.getChildren();
		/* Check if children of question are leaves by checking it's grandchildren */
		if (answer) {
			if (children.get(0).getChildren().isEmpty()) {			// leaf
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (children.get(1).getChildren().isEmpty()) {			// leaf
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public String submitAnswer(boolean answer) {
		this.answer = answer;
		finished = isSpecies(currentQ);
		List<Node<String>> children = currentQ.getChildren();
		if (finished) {
			if (answer) {
				return children.get(0).getElement().toString();
			}
			else {
				return children.get(1).getElement().toString();
			}
		}
		else {
			return null;
		}	
	}
	
	public static void main(String[] args) {
		Tree<String> t = new SimpleTree<String>();

		Node<String> question = new SimpleNode<String>("Does it have fur?");
		Node<String> answer1 = new SimpleNode<String>("Cat");
		Node<String> answer2 = new SimpleNode<String>("Fish");

		t.setRoot(question);
		t.insert(question, answer1);
		t.insert(question, answer2);

		SpeciesDetector<String> s = new SpeciesDetector<String>(t);
		System.out.println(s.getNextQuestion()); // Returns "Does it have fur?"
		
		System.out.println(s.submitAnswer(true));	// Returns "Cat";
	}
	
}
