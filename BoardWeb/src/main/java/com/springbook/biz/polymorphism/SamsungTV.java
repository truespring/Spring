package com.springbook.biz.polymorphism;

public class SamsungTV {
	public void powerOn() {
		System.out.println("SamsungTV --- 전원 켬");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV --- 전원 끔");
	}
	
	public void volumeUp() {
		System.out.println("SamsungTV --- 소리 키움");
	}
	
	public void volumeDown() {
		System.out.println("SamsungTV --- 소리 낮춤");
	}
}
