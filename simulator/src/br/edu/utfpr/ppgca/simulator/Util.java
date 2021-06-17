package br.edu.utfpr.ppgca.simulator;

import java.util.Random;

public class Util {

	static float randomFloat() {
		return new Random().nextFloat();
	}

	static int randomInt(final int BOUND) {
		return new Random().nextInt(BOUND);
	}

	static String randomString() {
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
