
package br.edu.utfpr.ppgca.prs.core;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;

public class Clock {

	private static Clock instance;
	private Long startTime;

	private Clock() {
		this.startTime = System.currentTimeMillis();
	}

	public static Clock getInstance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public Float getAge(final Belief BELIEF) {
		return getAge(BELIEF.getData());
	}

	public Float getAge(final Data DATA) {
		Long lastActivation = DATA.getLastActivation();
		long now = System.currentTimeMillis();
		
		return (float) (lastActivation - startTime) / (float) (now - startTime);
	}

}