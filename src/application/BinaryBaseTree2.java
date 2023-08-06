package application;

public class BinaryBaseTree2 implements BinaryTree2 {
	NodeAVL2 root;

	public NodeAVL2 getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(getRoot() !=null)
		appendStringToTree(builder, getRoot());
		return builder.toString();
	}

	public String toStringLPR() {

		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, getRoot());
		return builder.toString();
	}

	public void appendStringToTree(StringBuilder builder, NodeAVL2 node) {

		if (node.getLeft() != null)
			appendStringToTree(builder, node.getLeft());
		appendNode(builder, node);
		if (node.getRight() != null)
			appendStringToTree(builder, node.getRight());

	}

	public void appendNode(StringBuilder builder, NodeAVL2 node) {
		builder.append(node.getData() + " ");
	}
}
