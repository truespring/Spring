package polymorphism;

import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements Tv {
	private Speaker speaker;
	
	public LgTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public LgTV() {
		System.out.println("LgTV 객체화!");
	}
	@Override
	public void powerOn() {
		System.out.println("LgTV --- 전원 켬");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV --- 전원 끔");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
}
