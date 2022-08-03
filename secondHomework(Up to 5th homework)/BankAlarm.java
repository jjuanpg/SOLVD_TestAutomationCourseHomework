package secondHomework;

public class BankAlarm implements AlarmSystem{

	@Override
	public void on() {
		System.out.println("Alarm system turned on");
	}

	@Override
	public void off() {
		System.out.println("Alarm system turned off");
	}

	
}
