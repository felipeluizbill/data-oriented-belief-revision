package br.edu.utfpr.ppgca.simulator;

import org.junit.Test;

public class Scenarios {

	final int AMOUNT_OF_GOALS[] = { 10, 50, 100, 200 };
	final int ENVIRONMENT_PROPORTION[] = { 1, 5, 10};
	final int RULES_PER_GOAL[] = { 4, 6, 8 };
	final int MEMORY_PROPORTION[] = { 1, 2, 4, 6, 8 };

	// thresholds
//	final float[] STORING_THRESHOLDS = { 0.1F, 0.2F, 0.4F };
//	final float[] RETRIEVING_THRESHOLDS = { 0.1F, 0.2F, 0.4F };
//	final float[] OBLIVION_THRESHOLDS = { 0.1F, 0.2F, 0.4F };
	final float[] STORING_THRESHOLDS = { 0.2F };
	final float[] RETRIEVING_THRESHOLDS = { 0.19F };
	final float[] OBLIVION_THRESHOLDS = { 0.4F };

	// environment dynamics
	final float[] DYNAMICS = { 0F, 0.1F, 0.4F };

	// experiment_repetitions
	final int REPETITIONS = 10;

	@Test
	public void generalScenario() {
		final int ENVIRONMENT_SIZE = AMOUNT_OF_GOALS[1] * ENVIRONMENT_PROPORTION[0];
		final int MEMORY_SIZE = ENVIRONMENT_SIZE / MEMORY_PROPORTION[2];

		for (int i = 0; i < STORING_THRESHOLDS.length; i++) {
			for (int j = 0; j < RETRIEVING_THRESHOLDS.length; j++) {
				for (int k = 0; k < OBLIVION_THRESHOLDS.length; k++) {
					runExperiment(AMOUNT_OF_GOALS[1], ENVIRONMENT_SIZE, RULES_PER_GOAL[0], MEMORY_SIZE,
							STORING_THRESHOLDS[i], RETRIEVING_THRESHOLDS[j], OBLIVION_THRESHOLDS[k], DYNAMICS[0]);
				}
			}
		}

	}

	public void runExperiment(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer RULES_PER_GOAL, final Integer MEMORY_SIZE, final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD, final float OBLIVION_THRESHOLD, final float DYNAMICS) {
		Statistics statistics = new Statistics();

		for (int i = 0; i < REPETITIONS; i++) {
			Environment.prepareEnvironment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, RULES_PER_GOAL, MEMORY_SIZE,
					STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, DYNAMICS);
			Environment.sendPerception();
			statistics.addResults(Environment.agents);
			System.out.println("repetition [" + i + "/" + REPETITIONS + "]");
		}

		statistics.run(getScenarioDescriptor(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, RULES_PER_GOAL, MEMORY_SIZE,
				STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, DYNAMICS));
	}

	private String getScenarioDescriptor(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer RULES_PER_GOAL, final Integer MEMORY_SIZE, final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD, final float OBLIVION_THRESHOLD, final float DYNAMICS) {
		return "GOALS = " + AMOUNT_OF_GOALS + " ; ENVIRONMENT_SIZE = " + ENVIRONMENT_SIZE + " ; RULES_PER_GOAL = "
				+ RULES_PER_GOAL + " ; MEMORY SIZE = " + MEMORY_SIZE + " ; DYNAMICS = " + DYNAMICS + " ; STORING = "
				+ STORING_THRESHOLD + " ; RETRIEVING = " + RETRIEVING_THRESHOLD + " ; OBLIVION = " + OBLIVION_THRESHOLD;

	}

}
