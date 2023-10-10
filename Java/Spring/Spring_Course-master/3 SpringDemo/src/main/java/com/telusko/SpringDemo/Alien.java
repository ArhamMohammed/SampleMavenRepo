package com.telusko.SpringDemo;

public class Alien {
	
	private int age;
	private Computer com;
	
	public Alien() {
		System.out.println("Constructor is called");
	}
	
//	public Alien(int age) {
//		this.age = age;
//	}
	
	public int getAge() {
		return age;
	}

	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
public void code() 
{
	System.out.println("Im Coding..");
	com.compile();
}


}
