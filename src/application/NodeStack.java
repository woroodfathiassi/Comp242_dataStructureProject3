package application;

public class NodeStack {
	private Object element;
	private NodeStack next;

	public NodeStack() {

	}

	public NodeStack(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public NodeStack getNext() {
		return next;
	}

	public void setNext(NodeStack next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return element + "";
	}

}
