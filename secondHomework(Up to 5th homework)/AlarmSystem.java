package secondHomework;

public interface AlarmSystem {

	void on();
	void off();
	
	default boolean alarmSet(){
		return true;
	}
}
