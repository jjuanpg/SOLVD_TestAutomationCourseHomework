package secondHomework;

public class MetalDetector implements MetalDetectorSystem{

	@Override
	public void on() {
		System.out.println("Metal detector activated...");
	}

	@Override
	public void off() {
		System.out.println("Metal detector deactivated...");
	}

	
}
