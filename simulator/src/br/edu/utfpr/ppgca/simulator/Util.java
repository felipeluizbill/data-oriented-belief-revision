package br.edu.utfpr.ppgca.simulator;

import java.util.Random;

public class Util {

	private static Util instance;
	private Integer beliefCounter = 0;

	private Util() {

	}

	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}

	public float randomFloat() {
		return new Random().nextFloat();
	}

	public int randomInt(final int BOUND) {
		return new Random().nextInt(BOUND);
	}

	public int nextInt() {
		return beliefCounter++;
	}

	public String randomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
	
	

}
