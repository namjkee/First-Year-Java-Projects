import java.util.ArrayList;
import java.util.List;


public class FamilyTree {
	private Tree t;
	
	
	public FamilyTree(Tree t) {
		this.t = t;
	}
	
	/**
	 * Returns the parent of the given node
	 */
	public String getParent(Node person) {
		try {
			Object parent = person.getParent().getElement();
			return parent.toString();
		} catch (NullPointerException e) {
			return "No parent";
		}
	}
	
	/**
	 * Returns the parent of the parent of the given node
	 */
	public String getGrandparent(Node person) {
		try {
			Object grandParent = person.getParent().getParent().getElement();
			return grandParent.toString();
		} catch (NullPointerException e) {
			return "No grandparent";
		}
	}
	
	/**
	 * Returns all children of the given node
	 */
	public List<String> getChildren(Node person) {
		List<Node> children = person.getChildren();
		List<String> childrenString = new ArrayList<String>();;
		
		for (Node child: children) {
			String s = child.getElement().toString();
			childrenString.add(s);
		}
		
		return childrenString;
	}
	
	/**
	 * Returns all children of the children of a given node
	 */
	public List<String> getGrandchildren(Node person) {
		List<Node> children = person.getChildren();
		List<String> grandChildrenString = new ArrayList<String>();
		
		for (Node child: children) {
			List<Node> grandChildren = child.getChildren();
			
			if (grandChildren.size() == 0) {
				continue;
			}
			else {
				for (int i = 0; i < grandChildren.size(); i++) {
					Object grandChild = grandChildren.get(i).getElement();
					grandChildrenString.add(grandChild.toString());
				}

			}
			
		}
		return grandChildrenString;
	}
	
	public List<String> getSiblings(Node person) {
		List<String> siblings = new ArrayList<String>();
		if (person == null) return siblings;
		
		try {
			person.getParent().getChildren();						// one man family, if no parent then no children thus return empty sibling list. i.e testing root
		} catch (NullPointerException e) {
			return siblings;
		}
		List<Node> childrens = person.getParent().getChildren();
		
		for (Node child: childrens) {
			if (child == person) {
				continue;
			}
			else {
				Object sibling = child.getElement();
				siblings.add(sibling.toString());
			}
		}
		
		return siblings;
	}
	
	public int getSimilarityScore(String personName, String descendant) {
		int counter = 0;
		
		for (int i = 0; i < descendant.length(); i++) {
			try {											// if person's name is shorter than the descendant's name
				personName.charAt(i);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
			if (descendant.charAt(i) == personName.charAt(i)) counter++;
			else {
				break;
			}
		}
		
		return counter;
	}
	
	public String mostSimilarDescendant(Node person) {
	List<String> children = getChildren(person);
	List<String> grandChildren = getGrandchildren(person);
		
	List<String> descendants = new ArrayList<String>();
	descendants.addAll(children);
	descendants.addAll(grandChildren);
		
	if (descendants.isEmpty()) return null;
		
	String personName = person.getElement().toString();
	String mostSimilar = null;
	int currentSimilarScore = 0;
		
		for (String descendant: descendants) {
			int similarScore = getSimilarityScore(personName, descendant);
			
			if (similarScore > currentSimilarScore) {
				mostSimilar = descendant;
				currentSimilarScore = similarScore;
			}
		}
		
	if (currentSimilarScore == 0) {
		return descendants.get(0);
	}
	return mostSimilar;
	}
	
}
