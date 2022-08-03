package secondHomework;

public class Computers implements ComputerSystem{
	
	private boolean Status;
	
	@Override
	public void on() {
		System.out.println("Turning on computers...");
		this.Status = true;
	}

	@Override
	public void off() {
		System.out.println("Turning off computers...");
		this.Status = false;
	}

	public boolean getStatus() {
		return Status;
	}
	
}
