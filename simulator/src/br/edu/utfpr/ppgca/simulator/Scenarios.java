package br.edu.utfpr.ppgca.simulator;

import org.junit.Test;

public class Scenarios {

	final int AMOUNT_OF_GOALS[] = { 10, 100, 1000 };
	final int ENVIRONMENT_PROPORTION[] = { 10, 10, 100 };
	final int RULES_PER_GOAL[] = { 4, 8, 16 };
	final int MEMORY_PROPORTION[] = { 2, 4, 8 };

	// thresholds
	final float[] STORING_THRESHOLDS = { 0.1F, 0.2F, 0.4F };
	final float[] RETRIEVING_THRESHOLDS = { 0.1F, 0.2F, 0.4F };
	final float[] OBLIVION_THRESHOLDS = { 0.1F, 0.2F, 0.4F };

	// environment dynamics
	final float[] DYNAMICS = { 0F, 0.1F, 0.4F };

	// experiment_repetitions
	final int REPETITIONS = 10;

	@Test
	public void generalScenario() {
		final int ENVIRONMENT_SIZE = AMOUNT_OF_GOALS[0] * ENVIRONMENT_PROPORTION[0];
		final int MEMORY_SIZE = ENVIRONMENT_SIZE / MEMORY_PROPORTION[0];

		runExperiment(AMOUNT_OF_GOALS[0], ENVIRONMENT_SIZE, RULES_PER_GOAL[0], MEMORY_SIZE, STORING_THRESHOLDS[0],
				RETRIEVING_THRESHOLDS[0], OBLIVION_THRESHOLDS[0], DYNAMICS[0]);
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

		}

		statistics.run();

	}

}
