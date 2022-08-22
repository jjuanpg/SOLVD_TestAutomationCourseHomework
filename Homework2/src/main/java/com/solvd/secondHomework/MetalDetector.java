package com.solvd.secondHomework;

import com.solvd.secondHomework.Interfaces.IMetalDetectorSystem;

public class MetalDetector implements IMetalDetectorSystem {

	@Override
	public String on() {
		return("Metal detector activated...");
	}

	@Override
	public String off() {
		return("Metal detector deactivated...");
	}

	
}
