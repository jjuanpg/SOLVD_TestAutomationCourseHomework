package com.solvd.secondHomework.Interfaces;

public interface IAlarmSystem {

	String on();
	String off();
	
	default boolean alarmSet(){
		return true;
	}
}
