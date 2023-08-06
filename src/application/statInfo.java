package application;

public class statInfo {
	private int male;
	private int female;
	private int size;

	public statInfo(int male, int female, int size) {
		super();
		this.male = male;
		this.female = female;
		this.size = size;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return male + ", " + female + "," + size;
	}

}
