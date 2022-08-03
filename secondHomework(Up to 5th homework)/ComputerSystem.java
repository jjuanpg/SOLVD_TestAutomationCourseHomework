package secondHomework;

public interface ComputerSystem {

	void on();
	void off();
	
	default String connect(){
		return "Systems connected";
	}
	default String disconnect(){
		return "Systems disconnected";
	}
	
}
