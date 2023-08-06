package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Martyrs {
	private String name;
	private int age;
	private String location;
	private Date dateOfDead;
	private String gender;

	public Martyrs() {

	}

	public Martyrs(String name, int age, String location, Date dateOfDead, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.location = location;
		this.dateOfDead = dateOfDead;
		this.gender = gender;
	}

	public Martyrs(String name, int age, Date dateOfDead, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.dateOfDead = dateOfDead;
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
		// .substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDateOfDead() {
		return new SimpleDateFormat("MM/dd/yyyy").format(dateOfDead);
		// return dateOfDead;
	}

	public void setDateOfDead(Date dateOfDead) {
		this.dateOfDead = dateOfDead;
	}

	public String getGender() {
		return gender.toUpperCase();
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		// String s = new SimpleDateFormat("yyyy/MM/dd").format(getDateOfDead());
		return getName() + "," + getAge() + "," + getLocation() + "," + getDateOfDead() + "," + getGender() + "\n";
	}

}
