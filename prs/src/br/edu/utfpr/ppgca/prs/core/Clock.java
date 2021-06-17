package br.edu.utfpr.ppgca.prs.core;

public class Clock {

	private static Clock instance;
	private Integer counter = 0;

	private Clock() {

	}

	public static Clock getInstance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}

	public void incrementCounter() {
		counter++;
	}

	public Integer getCounter() {
		return this.counter;
	}

	public Float getAge(final Integer MOMENT) {
		return (float) MOMENT / (float) counter;
	}

}
