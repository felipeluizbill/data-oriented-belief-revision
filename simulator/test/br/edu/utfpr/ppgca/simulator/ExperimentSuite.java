package br.edu.utfpr.ppgca.simulator;

import java.io.IOException;

public class ExperimentSuite {

	private final static Integer AMOUNT_OF_GOALS = 100;
	/**
	 * 
	 */
	private final static Integer ENVIRONMENT_SIZE = 1000;
	private final static Integer AMOUNT_OF_RULES_PER_GOAL = 4;
	private final static Float DYNAMICS = 0.2F;

	private final static Integer PERCEPTION_SEQUENCE_FACTOR = 4;

	private final static Integer REPETITIONS = 100;

	public static void main(String[] args) throws IOException {
//
		FileUtil.getInstance().mergeFiles("E:\\finalResults\\dynamics2");

//		for (int i = 0; i < REPETITIONS; i++) {
//			FileUtil.getInstance().clear();
//			Scenario scenario = new Scenario(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, AMOUNT_OF_RULES_PER_GOAL,
//					PERCEPTION_SEQUENCE_FACTOR, DYNAMICS);
//			scenario.run();
//			FileUtil.getInstance().mergeFiles();
//		}

		
		
	}

}
