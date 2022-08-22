package com.solvd.secondHomework;

import com.solvd.secondHomework.Interfaces.IComputerSystem;

public class Computers implements IComputerSystem {
	
	private boolean Status;
	
	@Override
	public String on() {
		this.Status = true;
		return("Turning on computers...");
	}

	@Override
	public String off() {
		this.Status = false;
		return("Turning off computers...");
	}

	public boolean getStatus() {
		return Status;
	}
	
}
