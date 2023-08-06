package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStack {
	private Date date;
	private SLL stack;

	public DateStack(Date date) {
		this.date = date;
		stack = new SLL();
	}

	public String getDate() {
		return new SimpleDateFormat("MM/dd/yyyy").format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SLL getStack() {
		return stack;
	}

	public void setStack(SLL stack) {
		this.stack = stack;
	}

	@Override
	public String toString() {
		return getDate() + "," + stack + "\n";
	}

}
