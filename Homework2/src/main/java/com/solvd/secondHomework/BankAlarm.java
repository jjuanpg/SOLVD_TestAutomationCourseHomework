package com.solvd.secondHomework;

import com.solvd.secondHomework.Interfaces.IAlarmSystem;

public class BankAlarm implements IAlarmSystem {
	private boolean Status = true;

	@Override
	public String on() {
		this.Status = true;
		return("Alarm system turned on");
	}

	@Override
	public String off() {
		this.Status = false;
		return("Alarm system turned off");
	}

	public boolean getStatus(){
		return Status;
	}
	
}
