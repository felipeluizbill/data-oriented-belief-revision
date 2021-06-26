package br.edu.utfpr.ppgca.simulator;

import org.junit.Test;

public class ExperimentSuite {

	private final Integer AMOUNT_OF_GOALS = 100;
	private final Integer ENVIRONMENT_SIZE = 1000;
	private final Integer AMOUNT_OF_RULES_PER_GOAL = 4;

	private final Integer PERCEPTION_SEQUENCE_FACTOR = 4;

	@Test
	public void run() {
		Scenario scenario = new Scenario(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, AMOUNT_OF_RULES_PER_GOAL,
				PERCEPTION_SEQUENCE_FACTOR);

	}

}
