package br.edu.utfpr.ppgca.simulator;

import java.util.Arrays;

import org.junit.Test;

public class Scenarios {

	final int AMOUNT_OF_GOALS[] = { 100, 200, 400, 800 };
	final int ENVIRONMENT_PROPORTION[] = { 10, 20, 40, 80 };
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
		repeatExperiment(AMOUNT_OF_GOALS[0], (ENVIRONMENT_PROPORTION[0] * AMOUNT_OF_GOALS[0]), RULES_PER_GOAL[0],
				STORING_THRESHOLDS[1], RETRIEVING_THRESHOLDS[1], OBLIVION_THRESHOLDS[1], DYNAMICS[0],
				AMOUNT_OF_GOALS[0] * ENVIRONMENT_PROPORTION[0] / MEMORY_PROPORTION[2]);
	}

	public void repeatExperiment(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer RULES_PER_GOAL, final Float STORING_THRESHOLD, final Float RETRIEVING_THRESHOLD,
			final Float OBLIVION_THRESHOLD, final Float DYNAMIC, final Integer MEMORY_SIZE) {
		// Collection<Agent> agents = new HashSet<>();
		Arrays.asList(REPETITIONS).parallelStream().forEach(i -> {
			Environment.prepareEnvironment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, RULES_PER_GOAL, STORING_THRESHOLD,
					RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, DYNAMIC, MEMORY_SIZE);

			Environment.sendPerception();

			// agents.addAll(Environment.agents);
			Environment.printLogs();
		});

	}

}
