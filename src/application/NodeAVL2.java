package application;

public class NodeAVL2 {
	private DateStack data;
	private NodeAVL2 left, right;
	int hight;

	public NodeAVL2() {
	}

	public NodeAVL2(DateStack data) {
		this.data = data;
	}

	public DateStack getData() {
		return data;
	}

	public void setData(DateStack data) {
		this.data = data;
	}

	public NodeAVL2 getLeft() {
		return left;
	}

	public void setLeft(NodeAVL2 left) {
		this.left = left;
	}

	public NodeAVL2 getRight() {
		return right;
	}

	public void setRight(NodeAVL2 right) {
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