package com.solvd.secondHomework.Interfaces;

public interface IComputerSystem {

	String on();
	String off();
	
	default String connect(){
		return "Systems connected";
	}
	default String disconnect(){
		return "Systems disconnected";
	}
	
}
