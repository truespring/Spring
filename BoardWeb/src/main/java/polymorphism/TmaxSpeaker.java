package polymorphism;

public class TmaxSpeaker implements Speaker {
	public TmaxSpeaker() {
		System.out.println("==> TmaxSpeaker 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("TmaxSpeaker --- 소리 올림");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("TmaxSpeaker --- 소리 내림");
	}
}
