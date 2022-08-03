package secondHomework;

public class Cameras implements VideoRecordSystem {

	private String Status;
	
	@Override
	public void cctvRecorder() {
		System.out.println("cctv is now recording...");
		this.Status = "Recording...";
	}

	@Override
	public void cctvNoRecorder() {
		System.out.println("cctv is not recording now...");
		this.Status = "Transmitting mode...";
	}

	public String getStatus() {
		return Status;
	}
}
