package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;

public class SLL {
	private NodeStack first, last;
	int count = 0;
	private SLL head;

	public SLL() {

	}

	public SLL(NodeStack first, NodeStack last) {
		this.first = first;
		this.last = last;
		this.head = new SLL();
	}

	public void setFirst(NodeStack first) {
		this.first = first;
	}

	public void setLast(NodeStack last) {
		this.last = last;
	}

	public NodeStack getFirst() {
		return first;
	}

	public NodeStack getLast() {
		return last;
	}

	public void addFirst(Object element) {
		if (count == 0)
			first = last = new NodeStack(element);
		else {
			NodeStack temp = new NodeStack(element);
			temp.setNext(first);
			first = temp;
		}
		count++;
	}

	public void addLast(Object element) {
		if (count == 0)
			first = last = new NodeStack(element);
		else {
			NodeStack temp = new NodeStack(element);
			last.setNext(temp);
			last = temp;
		}
		count++;
	}

	public void add(Object element, int index) {
		if (count == 0)
			addFirst(element);
		else if (index == 0)
			addFirst(element);
		else if (index == count)
			addLast(element);
		else if (index < 0 || count <= index)
			addLast(element);
		else {
			NodeStack temp = new NodeStack(element);
			NodeStack current = first;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			temp.setNext(current.getNext());
			current.setNext(temp);
		}
		count++;
	}

	public boolean removeFirst() {
		if (count == 0)
			return false;
		else if (count == 1)
			first = last = null;
		else {
			NodeStack temp = first;
			first = first.getNext();
			temp.setNext(null);
		}
		count--;
		return true;
	}

	public boolean removeLast() {
		if (count == 0)
			return false;
		else if (count == 1)
			first = last = null;
		else {
			NodeStack current = first;
			while (current.getNext().getNext() != null)
				current = current.getNext();
			last = current;
			last.setNext(null);

		}
		count--;
		return true;
	}

	public boolean remove(int index) {
		NodeStack ptr = null, prev = null;
		if (index < 0 || index >= count)
			return false;
		else if (index == 0)
			return removeFirst();
		else if (index == count - 1)
			return removeLast();
		else {
			ptr = first;
			for (int i = 1; i <= index; i++) {
				prev = ptr;
				ptr = ptr.getNext();
			}
			prev.setNext(ptr.getNext());
			ptr.setNext(null);
			count--;
			return true;
		}
	}

	public boolean remove(Object element) {
		NodeStack prev = null, current = null;
		if (count != 0) {
			if (count == 1)
				removeFirst();
			else if (first != null && element.equals(first.getElement())  )
				return removeFirst();
			else if (last != null && element.equals(last.getElement()))
				return removeLast();
			else {
				current = first;
				for (int i = 0; i < count; i++) {
					if (element.equals(current.getElement()))
						return remove(i);
					current = current.getNext();
				}
			}
		}
		System.out.println("i am in remove object");
		return false;
	}

	public void printList() {
		NodeStack current = first;
		if (count == 0)
			return;
		while (current != null) {
			System.out.println(current.getElement().toString());
			current = current.getNext();
		}
	}

	public String printList1() {
		String x = "";
		NodeStack current = first;
		if (count == 0)
			return x;
		while (current != null) {
			x += (current.getElement().toString());
			current = current.getNext();
		}
		return x;
	}

	public Object get(int index) {
		NodeStack temp = first;
		if (index >= count || index < 0)
			return null;
		int i = 0;
		while (temp != null && i < index) {
			temp = temp.getNext();
			i++;
		}
		return temp.getElement();
	}

	public int indexOf(Object s) {
		NodeStack current = first;
		int i = 0;
		if (count == 0)
			return -1;
		else if (count == 1)
			return 0;
		else {
			while (i < count && current != null) {
				if (current.getElement() == s) {
					break;
				}
				current = current.getNext();
				i++;

			}
			return i;

		}
	}

	public int getSize() {
		return count;
	}

	public boolean isExisting(Martyrs element) {

		NodeStack current = first;
		for (int i = 0; i < count; i++) {
			if (((Martyrs) current.getElement()).getName().equalsIgnoreCase(element.getName())
					&& ((Martyrs) current.getElement()).getAge() == element.getAge()
					&& ((Martyrs) current.getElement()).getGender().equalsIgnoreCase(element.getGender()))
				return true;
			current = current.getNext();
		}
		return false;
	}

	public NodeStack get(Object str) {
		NodeStack current = first;
		if (str instanceof Martyrs) {
			if (!isExisting((Martyrs) str))
				return null;
			else {
				for (int i = 0; i < count; i++) {
					if (current.getElement().toString().equalsIgnoreCase(str.toString()) && current != null)
						return current;
					current = current.getNext();
				}
			}
		} else {
			for (int i = 0; i < count; i++) {
				if (((Martyrs) current.getElement()).getName().equalsIgnoreCase((String) str) && current != null)
					return current;
				current = current.getNext();
			}
		}
		return null;

	}

}
