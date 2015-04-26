public class Main {
	
	public static void main(String[] args) {
		Tree t = new SimpleTree();
		
		Node bob = new SimpleNode("Bob");
//		Node bobe = new SimpleNode("Bobe");
//		bobi = new SimpleNode("Bobi");
//		Node bobson = new SimpleNode("Bob");
		/*
		Node jane = new SimpleNode("Jane");
		Node tim = new SimpleNode("Tim");
		Node kate = new SimpleNode("Kate");
		Node ted = new SimpleNode("Ted");
		Node terry = new SimpleNode("Terry");
		Node terace = new SimpleNode("Terace");
		 */	
		t.setRoot(bob);
//		t.insert(bob, bobe);
//		t.insert(bobe, bobi);
//		t.insert(bobi, bobson);
		/*
		t.insert(bob, jane);
		t.insert(bob, tim);
		t.insert(tim, kate);
		t.insert(kate, ted);
		t.insert(ted, terace);
		t.insert(terace, terry);
		 */	
		FamilyTree f = new FamilyTree(t);
		
		System.out.println(f.mostSimilarDescendant(bob));
		
		
	}
	
}