package application;

public class DLL {

	private NodeDLL first, last;
	int count = 0;

	public NodeDLL getfirst() {
		return first;
	}

	public NodeDLL getLast() {
		return last;
	}

	public void addLast(Object element) {
		NodeDLL newNode = new NodeDLL(element);

		if (count == 0) {
			first = last = newNode;
		} else {
			NodeDLL temp = new NodeDLL(element);
			last.setNext(temp);
			temp.setNext(first);
			first.setPrev(temp);
			temp.setPrev(last);
			last = temp;

		}
		count++;
	}

	public void addFirst(Object element) {
		if (count == 0)
			first = last = new NodeDLL(element);
		else {
			NodeDLL temp = new NodeDLL(element);
			first.setPrev(temp);
			temp.setNext(first);
			temp.setPrev(last);
			last.setNext(temp);
			first = temp;
		}

		count++;
	}

	public void add(Object element, int index) {
		NodeDLL newnode = new NodeDLL(element);
		NodeDLL current = first;
		if (count == 0)
			addFirst(element);
		else if (index == 0)
			addFirst(element);
		else if (index == count)
			addLast(element);
		else {
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			newnode.setNext(current.getNext());
			current.setNext(newnode);
			newnode.setPrev(current);
			newnode.getNext().setPrev(newnode);
			count++;
		}

	}

	public void addSorted(Object element) {
		if (isExisting(element.toString()))
			return;
		int index = 0;
		if (count == 0)
			addFirst(element);
		else if (element.toString().trim().compareToIgnoreCase(first.getElement().toString().trim()) < 0)
			addFirst(element);
		else if (element.toString().trim().compareToIgnoreCase(last.getElement().toString().trim()) > 0)
			addLast(element);
		else {
			NodeDLL newnode = new NodeDLL(element);
			NodeDLL current = first;
			while (current.getElement().toString().trim().compareToIgnoreCase(element.toString().trim()) < 0
					&& current.getNext() != null) {
				index++;
				current = current.getNext();
			}
			add(element, index);
		}

	}

	public boolean removeLast() {
		if (count == 0)
			return false;
		else if (count == 1) {
			first = last = null;
		} else {
			NodeDLL current = first;
			for (int i = 0; i < count - 1; i++)
				current = current.getNext();
			last.setPrev(current.getPrev());
			last = current;
			last.setNext(first);
		}
		count--;
		return true;
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else if (count == 1) {
			first = last = null;
		} else {
			NodeDLL current = first;
			first = first.getNext();
			first.setPrev(last);
			current = null;
		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		NodeDLL prev = first;
		NodeDLL current = first;

		if (index == 0)
			return removeFirst();
		else if (index == count - 1)
			return removeLast();
		else if (index < 0 || index > count)
			return false;
		else {
			for (int i = 0; i < index; i++) {
				prev = current;
				current = current.getNext();

			}
			prev.setNext(current.getNext());
			current.getNext().setPrev(prev);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		if (count == 0)
			return false;
		if (element.equals(first.getElement()))
			return removeFirst();
		if (element.equals(last.getElement()))
			return removeLast();
		else {
			NodeDLL current = first.getNext();
			for (int i = 0; i < count; i++) {
				if (current.getElement().equals(element))
					return remove(i + 1);
				current = current.getNext();
			}
			return false;
		}
	}

	public String print() {
		String x = "";
		if (count == 0)
			return x;
		NodeDLL current = first;
		for (int i = 0; i < count; i++) {
			x += (current.getElement().toString()) + "\n";
			current = current.getNext();
		}
		return x;
	}

	public void printList() {
		if (count == 0)
			return;
		NodeDLL current = first;
		for (int i = 0; i < count; i++) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}

	public int getSize() {
		return count;
	}

	public boolean isExisting(String element) {
		NodeDLL current = first;
//		while(current != null) {
		for (int i = 0; i < count; i++) {
			if (element.toString().equalsIgnoreCase(current.getElement().toString()) && current != null)
				return true;
			current = current.getNext();
		}
		return false;
	}

	public int indexOf(Object s) {
		NodeDLL current = first;
		int i = 0;
		if (count == 0)
			return -1;
		else if (count == 1)
			return 0;
		else {
			while (i < count && current != null) {
				if (current.getElement().equals(s)) {
					break;
				}
				current = current.getNext();
				i++;

			}
			return i;

		}
	}

	public NodeDLL get(String str) {
		NodeDLL current = first;
		if (!isExisting(str))
			return null;
		else {
			for (int i = 0; i < count; i++) {
				if (current.getElement().toString().equalsIgnoreCase(str) && current != null)
					return current;
				current = current.getNext();
			}
		}

		return null;
	}

}
