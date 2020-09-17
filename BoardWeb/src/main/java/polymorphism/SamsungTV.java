package polymorphism;

import org.springframework.stereotype.Component;

@Component("tv")
public class SamsungTV implements Tv {
	private Speaker speaker;
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체화!");
	}
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
//	public SamsungTV(TmaxSpeaker speaker, int a) {
//		System.out.println("speaker, a 생성자");
//		System.out.println("a : " + a);
//		this.speaker = speaker;
//	}
//	
//	public SamsungTV(int a, TmaxSpeaker speaker) {
//		System.out.println("!speaker, a 생성자!");
//		System.out.println("a : " + a);
//		this.speaker = speaker;
//	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV --- 전원 켬");
	}
	
	@Override
	public void powerOff() {
		System.out.println("SamsungTV --- 전원 끔");
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
