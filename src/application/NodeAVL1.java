package application;

public class NodeAVL1 {
	private Martyrs data;
	private NodeAVL1 left, right;
	int hight;

	public NodeAVL1() {

	}

	public NodeAVL1(Martyrs data) {
		this.data = data;
	}

	public Martyrs getData() {
		return data;
	}

	public void setData(Martyrs data) {
		this.data = data;
	}

	public NodeAVL1 getLeft() {
		return left;
	}

	public void setLeft(NodeAVL1 left) {
		this.left = left;
	}

	public NodeAVL1 getRight() {
		return right;
	}

	public void setRight(NodeAVL1 right) {
		this.right = right;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	@Override
	public String toString() {
		return "Node [data=" + getData() + ", left=" + left + ", right=" + right + ", height=" + hight + "]";
	}

}
