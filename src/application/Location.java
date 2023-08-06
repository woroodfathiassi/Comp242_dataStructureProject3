package application;

public class Location {
	private String loc;
	private SLL list;
	private AVLTree1 avl1;
	private AVLTree2 avl2;

	public Location() {

	}

	public Location(String loc) {
		this.loc = loc;
		this.list = new SLL();
//		avl1 = new AVLTree1();
//		avl2 = new AVLTree2();
	}

	public SLL getList() {
		return list;
	}

	public void setList(SLL head) {
		this.list = head;
	}

	public AVLTree1 getAvl1() {
		return avl1;
	}

	public void setAvl1(AVLTree1 avl1) {
		this.avl1 = avl1;
	}

	public AVLTree2 getAvl2() {
		return avl2;
	}

	public void setAvl2(AVLTree2 avl2) {
		this.avl2 = avl2;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String print() {
		return getLoc();
	}

	@Override
	public String toString() {
		return loc;
	}

}
