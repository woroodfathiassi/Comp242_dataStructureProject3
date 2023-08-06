package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TableView;

public class BST2 extends BinaryBaseTree2 implements BSTIF2 {
	@Override
	public NodeAVL2 findNode(DateStack data) {
		return findeNode(data, root);
	}

	protected NodeAVL2 findeNode(DateStack data, NodeAVL2 node) {
		if (node == null)
			return null;
		else if (node.getData().getDate().compareTo(data.getDate()) < 0)
			node = findeNode(data, node.getRight());
		else if (node.getData().getDate().compareTo(data.getDate()) > 0)
			node = findeNode(data, node.getLeft());
		return node;
	}

	@Override
	public void insertNode(DateStack data) {
		root = insertNode(data, root);

	}

	protected NodeAVL2 insertNode(DateStack data, NodeAVL2 node) {
		if (node == null)
			node = new NodeAVL2(data);
		else if (data.getDate().compareTo(node.getData().getDate()) > 0)
			node.setRight(insertNode(data, node.getRight()));
		else if (data.getDate().compareTo(node.getData().getDate()) <= 0)
			node.setLeft(insertNode(data, node.getLeft()));
		return node;
	}

	protected NodeAVL2 deleteNode(DateStack data, NodeAVL2 node) {
		if (node == null)
			return null;
		else if (data.getDate().compareTo(node.getData().getDate()) > 0)
			node.setRight(deleteNode(data, node.getRight()));
		else if (data.getDate().compareTo(node.getData().getDate()) < 0)
			node.setLeft(deleteNode(data, node.getLeft()));
		else if (node.getLeft() == null && node.getRight() == null)
			node = null;
		else if (node.getLeft() == null)
			node = node.getRight();
		else if (node.getRight() == null)
			node = node.getLeft();
		else
			node = deleteNodeWithTwoChildren(node);
		return node;
	}

	public NodeAVL2 deleteNodeWithTwoChildren(NodeAVL2 node) {
		if (node == null)
			return null;

		NodeAVL2 inOrderSuccessor = findMin(node.getRight());
		node.setData(inOrderSuccessor.getData());
		node.setRight(deleteNode(node.getData(), node.getRight()));
		return node;
	}

	public NodeAVL2 findMin(NodeAVL2 node) {
		if (node == null)
			return null;
		else if (node.getLeft() == null)
			return node;
		else
			return findMin(node.getLeft());
	}

	@Override
	public void deleteNode(DateStack data) {
		root = deleteNode(data, root);
	}

	public void avl2Backward(NodeAVL2 node, TableView table) {
		if (node == null) {
			return;
		}

		if (node.getRight() != null)
			avl2Backward(node.getRight(), table);

		DateStack dateStack = (DateStack) node.getData();
		SLL stack = dateStack.getStack();
		SLL stackCopy = new SLL();

		// Create another stack to save stack's values
		NodeStack currentNode = stack.getFirst();
		while (currentNode != null) {
			stackCopy.addLast(currentNode.getElement());
			currentNode = currentNode.getNext();
		}

		currentNode = stackCopy.getFirst();
		while (currentNode != null && node!=null) {
			Martyrs martyrs = (Martyrs) currentNode.getElement();
			table.getItems().add(martyrs);
			currentNode = currentNode.getNext();
		}

		if (node.getLeft() != null)
			avl2Backward(node.getLeft(), table);
	}

}