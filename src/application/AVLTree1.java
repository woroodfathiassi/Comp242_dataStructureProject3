package application;

public class AVLTree1 extends BST1 {
	@Override
	protected NodeAVL1 insertNode(Martyrs data, NodeAVL1 node) {
		node = super.insertNode(data, node);
		updateHeight(node);
		return reBalance(node);

	}

	@Override
	protected NodeAVL1 deleteNode(Martyrs data, NodeAVL1 node) {
		node = super.deleteNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}

	private NodeAVL1 reBalance(NodeAVL1 node) {
		if (node != null) {
			int bf = balanceFactor(node);
			if (bf > 1) {
				if (balanceFactor(node.getLeft()) >= 0)
					node = rotateRight(node);
				else {
					node.setLeft(rotateLeft(node.getLeft()));
					node = rotateRight(node);
				}
			} else if (bf < -1) {
				if (balanceFactor(node.getRight()) <= 0)
					node = rotateLeft(node);
				else {
					node.setRight(rotateRight(node.getRight()));
					node = rotateLeft(node);
				}
			}
		}
		return node;
	}

	private int height(NodeAVL1 node) {
		return node != null ? node.getHight() : -1;
	}

	private void updateHeight(NodeAVL1 node) {
		if (node != null) {
			int leftChildHeight = height(node.getLeft());
			int rightChildHeight = height(node.getLeft());
			node.setHight(Math.max(leftChildHeight, rightChildHeight) + 1);
		}
	}

	private int balanceFactor(NodeAVL1 node) {
		// if(node != null)
		int bf = height(node.getLeft()) - height(node.getRight());
		return bf;
	}

	private NodeAVL1 rotateRight(NodeAVL1 node) {
		NodeAVL1 x = node.getLeft();
		node.setLeft(x.getRight());
		x.setRight(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private NodeAVL1 rotateLeft(NodeAVL1 node) {
		NodeAVL1 x = node.getRight();
		node.setRight(x.getLeft());
		x.setLeft(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	public int getHeigh() {
		return getHeigh(root);
	}

	private int getHeigh(NodeAVL1 node) {
		if (node == null)
			return -1;

		int leftHeight = getHeigh(node.getLeft());
		int rightHeight = getHeigh(node.getRight());

		return Math.max(leftHeight, rightHeight) + 1;
	}

}
