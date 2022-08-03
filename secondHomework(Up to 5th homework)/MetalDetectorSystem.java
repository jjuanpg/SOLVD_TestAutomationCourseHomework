package secondHomework;

public interface MetalDetectorSystem {

	void on();
	void off();
	
	default boolean alarmOn(){
		return true;
	}
	
}
