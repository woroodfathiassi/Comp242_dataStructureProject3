package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryBaseTree1 implements BinaryTree1 {
	NodeAVL1 root;

	public NodeAVL1 getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, getRoot());
		return builder.toString();
	}

	public String toStringLPR() {

		StringBuilder builder = new StringBuilder();
		appendStringToTree(builder, getRoot());
		return builder.toString();
	}

	public void appendStringToTree(StringBuilder builder, NodeAVL1 node) {

		if (node.getLeft() != null)
			appendStringToTree(builder, node.getLeft());
		appendNode(builder, node);
		if (node.getRight() != null)
			appendStringToTree(builder, node.getRight());

	}

	public void appendNode(StringBuilder builder, NodeAVL1 node) {
		builder.append(node.getData() + " ");
	}

}
