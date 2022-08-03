package secondHomework;

public class Atm implements AtmSystem{

	@Override
	public void on() {
		System.out.println("Turning on atm system...");
	}

	@Override
	public void off() {
		System.out.println("Turning off atm system...");
	}

	@Override
	public void warningMessage() {
		System.out.println("Service under maintenance, do not use.");
	}

}
