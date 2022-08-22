package com.solvd.secondHomework.Interfaces;

public interface IMetalDetectorSystem {

	String on();
	String off();
	
	default boolean alarmOn(){
		return true;
	}
	
}
