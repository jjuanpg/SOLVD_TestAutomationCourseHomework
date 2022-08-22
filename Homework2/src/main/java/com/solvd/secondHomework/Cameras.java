package com.solvd.secondHomework;

import com.solvd.secondHomework.Interfaces.IVideoRecordSystem;

public class Cameras implements IVideoRecordSystem {

	private String Status;
	
	@Override
	public String cctvRecorder() {
		this.Status = "Recording...";
		return("cctv is now recording...");
	}

	@Override
	public String cctvNoRecorder() {
		this.Status = "Transmitting mode...";
		return("cctv is not recording now...");
	}

	public String getStatus() {
		return Status;
	}
}
