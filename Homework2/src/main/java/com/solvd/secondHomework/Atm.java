package com.solvd.secondHomework;

import com.solvd.secondHomework.Interfaces.IAtmSystem;

public class Atm implements IAtmSystem {

	@Override
	public String on() {
		return("Turning on atm system...");
	}

	@Override
	public String off() {
		return("Turning off atm system...");
	}

	@Override
	public String warningMessage() {
		return("Service under maintenance, do not use.");
	}

}
