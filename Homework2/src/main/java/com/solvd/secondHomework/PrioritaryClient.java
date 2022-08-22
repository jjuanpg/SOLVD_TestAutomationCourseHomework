package com.solvd.secondHomework;

public class PrioritaryClient {
	
	private final String disabilitie;
	private final int age;
	
	public PrioritaryClient(String disabilitie, int age){
		this.disabilitie = disabilitie;
		this.age = age;
	}
	
	public String getDisabilitie() {
		return disabilitie;
	}

	public int getAge() {
		return age;
	}

	private final String[] disabilities = {"age", "handicap", "pregnant"};
	
	public boolean getAgeConfirm (int age){
		if(age > 60){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean getDisabilitie (String handicap, int age){
		for(int i=0; i < disabilities.length; i++) {
			if(handicap.equals("age")){
				return getAgeConfirm(age);
			}else if(handicap.equals(disabilities[i])){
				return true;
			}
		}
		return false;
	}
	
}
