package application;

public class NodeDLL {
	private Object element;
	private NodeDLL prev, next;

	public NodeDLL() {

	}

	public NodeDLL(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public NodeDLL getPrev() {
		return prev;
	}

	public void setPrev(NodeDLL prev) {
		this.prev = prev;
	}

	public NodeDLL getNext() {
		return next;
	}

	public void setNext(NodeDLL next) {
		this.next = next;
	}

}
