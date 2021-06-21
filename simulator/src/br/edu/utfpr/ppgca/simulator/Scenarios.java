package br.edu.utfpr.ppgca.simulator;

import org.junit.Test;

public class Scenarios {

	final int AMOUNT_OF_GOALS = 100;
	final int ENVIRONMENT_SIZE = AMOUNT_OF_GOALS * 10;
	final int[] RULES_PER_GOAL = { 4, 5, 6, 7, 8 };
	final int[] ENVIRONMENT_GOALS_PROPORTION = { 10 };
//	final float[] GOALS_DYNAMICS = { 0.1F, 0.2F, 0.3F, 0.4F, 0.5F };
	final float[] GOALS_DYNAMICS = { 0F };
	final float[] STORING_THRESHOLDS = { 0.1F };
	final float[] OBLIVION_THRESHOLDS = { 0.5F };
	final int REPETITIONS = 10;

	final int MEMORY_SIZE_FACTORS[] = { 1, 2, 4, 5 };
	final int MEMORY_SIZE = ENVIRONMENT_SIZE / 10;

	@Test
	public void generalScenario() {
		for (int i = 0; i < RULES_PER_GOAL.length; i++) {
			for (int j = 0; j < ENVIRONMENT_GOALS_PROPORTION.length; j++) {
				final int ENVIRONMENT_SIZE = ENVIRONMENT_GOALS_PROPORTION[j] * AMOUNT_OF_GOALS;
				for (int k = 0; k < RULES_PER_GOAL.length; k++) {
					for (int l = 0; l < STORING_THRESHOLDS.length; l++) {
						for (int m = 0; m < OBLIVION_THRESHOLDS.length; m++) {
							for (int n = 0; n < GOALS_DYNAMICS.length; n++) {
								for (int o = 0; o < REPETITIONS; o++) {
									Environment.prepareEnvironment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, RULES_PER_GOAL[k],
											STORING_THRESHOLDS[l], STORING_THRESHOLDS[l] - 0.01F,
											OBLIVION_THRESHOLDS[m], GOALS_DYNAMICS[n] , MEMORY_SIZE);
									while (!Environment.beliefs.isEmpty()) {
										Environment.sendPerception();
										if (Environment.terminate()) {
											break;
										}
									}
									Environment.printLogs();
									System.out.println("REPETITION = " + o);
									System.out.println("ENV_SIZE = " + ENVIRONMENT_SIZE + ", RULES = "
											+ RULES_PER_GOAL[k] + "STR = " + STORING_THRESHOLDS[l] + ", OBL = "
											+ OBLIVION_THRESHOLDS[m]);
								}
							}
						}
					}
				}
			}
		}
	}

}
