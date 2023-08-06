package application;

public class AVLTree2 extends BST2 {
	@Override
	protected NodeAVL2 insertNode(DateStack data, NodeAVL2 node) {
		node = super.insertNode(data, node);
		updateHeight(node);
		return reBalance(node);

	}

	@Override
	protected NodeAVL2 deleteNode(DateStack data, NodeAVL2 node) {
		node = super.deleteNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}

	private NodeAVL2 reBalance(NodeAVL2 node) {
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

	private int height(NodeAVL2 node) {
		return node != null ? node.getHight() : -1;
	}

	private void updateHeight(NodeAVL2 node) {
		if (node != null) {
			int leftChildHeight = height(node.getLeft());
			int rightChildHeight = height(node.getLeft());
			node.setHight(Math.max(leftChildHeight, rightChildHeight) + 1);
		}
	}

	private int balanceFactor(NodeAVL2 node) {
		// if(node != null)
		int bf = height(node.getLeft()) - height(node.getRight());
		return bf;
	}

	private NodeAVL2 rotateRight(NodeAVL2 node) {
		NodeAVL2 x = node.getLeft();
		node.setLeft(x.getRight());
		x.setRight(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}

	private NodeAVL2 rotateLeft(NodeAVL2 node) {
		NodeAVL2 x = node.getRight();
		node.setRight(x.getLeft());
		x.setLeft(node);
		updateHeight(node);
		updateHeight(x);
		return x;
	}
	
	public int getHeigh() {
        return getHeigh(root);
    }

    private int getHeigh(NodeAVL2 node) {
        if (node == null)
            return -1;
        
        int leftHeight = getHeigh(node.getLeft());
        int rightHeight = getHeigh(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    
    public NodeAVL2 maxSizeStack() {
    	if(root == null )
    		return null;
        int[] maxSize = {0};
        NodeAVL2[] maxNode = {null};
        bigSize(root, maxSize, maxNode);
        return maxNode[0];
    }

    private void bigSize(NodeAVL2 node, int[] maxSize, NodeAVL2[] maxNode) {
        if (node == null)
            return;

        bigSize(node.getLeft(), maxSize, maxNode);
        bigSize(node.getRight(), maxSize, maxNode);

        SLL stack = ((DateStack) node.getData()).getStack();
        int stackSize = stack.getSize();

        if (stackSize > maxSize[0]) {
            maxSize[0] = stackSize;
            maxNode[0] = node;
        }
    }
    
//    public NodeAVL2 maxSizeStack() {
//        if (root == null)
//            return null;
//        
//        NodeAVL2 maxNode = null;
//        int maxSize = 0;
//        
//        bigSize(root, maxSize, maxNode);
//        
//        return maxNode;
//    }
//
//    private void bigSize(NodeAVL2 node, int maxSize, NodeAVL2 maxNode) {
//        if (node == null)
//            return;
//
//        bigSize(node.getLeft(), maxSize, maxNode);
//        bigSize(node.getRight(), maxSize, maxNode);
//
//        SLL stack = ((DateStack) node.getData()).getStack();
//        int stackSize = stack.getSize();
//
//        if (stackSize > maxSize) {
//            maxSize = stackSize;
//            maxNode = node;
//        }
//    }
}
