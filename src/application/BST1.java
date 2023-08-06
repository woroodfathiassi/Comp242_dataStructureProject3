package application;

import javafx.scene.control.TableView;

public class BST1 extends BinaryBaseTree1 implements BSTIF1 {
	@Override
	public NodeAVL1 findNode(Martyrs data) {
		return findeNode(data, root);
	}

	protected NodeAVL1 findeNode(Martyrs data, NodeAVL1 node) {
		if (node == null)
			return null;
		else if (node.getData().getName().compareToIgnoreCase(data.getName()) < 0)
			node = findeNode(data, node.getRight());
		else if (node.getData().getName().compareToIgnoreCase(data.getName()) > 0)
			node = findeNode(data, node.getLeft());
		return node;
	}

	@Override
	public void insertNode(Martyrs data) {
		root = insertNode(data, root);
	}

	protected NodeAVL1 insertNode(Martyrs data, NodeAVL1 node) {
		if (node == null)
			node = new NodeAVL1(data);
		else if (data.getName().toString().compareToIgnoreCase(node.getData().getName().toString()) > 0)
			node.setRight(insertNode(data, node.getRight()));
		else if (data.getName().toString().compareToIgnoreCase(node.getData().getName().toString()) <= 0)
			node.setLeft(insertNode(data, node.getLeft()));
		return node;
	}

	protected NodeAVL1 deleteNode(Martyrs data, NodeAVL1 node) {
		if (node == null)
			return null;
		else if (data.getName().compareToIgnoreCase(node.getData().getName()) > 0)
			node.setRight(deleteNode(data, node.getRight()));
		else if (data.getName().compareToIgnoreCase(node.getData().getName()) < 0)
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

	public NodeAVL1 deleteNodeWithTwoChildren(NodeAVL1 node) {
		if (node == null)
			return null;

		NodeAVL1 inOrderSuccessor = findMin(node.getRight());
		node.setData(inOrderSuccessor.getData());
		node.setRight(deleteNode(node.getData(), node.getRight()));
		return node;
	}

	public NodeAVL1 findMin(NodeAVL1 node) {
		if (node == null)
			return null;
		else if (node.getLeft() == null)
			return node;
		else
			return findMin(node.getLeft());
	}

	@Override
	public void deleteNode(Martyrs data) {
		root = deleteNode(data, root);
	}

	public Martyrs insertIntoMartTable(NodeAVL1 node, TableView table) {

		Martyrs mart = null;
		if (node == null)
			return null;
		else {

			mart = insertIntoMartTable(node.getLeft(), table);
			if (mart != null) {
				table.getItems().add(mart);
			}

			mart = (Martyrs) node.getData();
			if (mart != null) {
				table.getItems().add(mart);
			}

			mart = insertIntoMartTable(node.getRight(), table);
			if (mart != null) {
				table.getItems().add(mart);
			}
		}

		return mart;
	}

//	public void MartSearch(String str, NodeAVL1 root, TableView table) {
//		table.getItems().clear();
//
//		displayMartDate(str, root, table);
//	}

	private void displayMartDate(String str, NodeAVL1 node, TableView table) {
		if (node == null)
			return;
		else {
			if (node.getData().getName().contains(str)) {
				table.getItems().add(node.getData());
			}
			displayMartDate(str, node.getLeft(), table);

			displayMartDate(str, node.getRight(), table);
		}
	}

	public void avl1LevelByLevel(NodeAVL1 root, TableView table) {
		int height = getHeight(root);
		for (int level = 1; level <= height; level++) {
			getLevel(root, level, table);
		}
	}

	private void getLevel(NodeAVL1 node, int level, TableView table) {
		if (node == null) {
			return;
		}

		if (level == 1) {
			Martyrs martyr = (Martyrs) node.getData();
			table.getItems().add(martyr);
		} else if (level > 1) {
			getLevel(node.getLeft(), level - 1, table);
			getLevel(node.getRight(), level - 1, table);
		}
	}

	public int getHeight(NodeAVL1 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = getHeight(node.getLeft());
		int rightHeight = getHeight(node.getRight());
		return Math.max(leftHeight, rightHeight) + 1;
	}

}