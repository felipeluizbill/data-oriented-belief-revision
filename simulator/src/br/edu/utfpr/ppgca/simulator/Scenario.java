package br.edu.utfpr.ppgca.simulator;

import java.util.HashSet;
import java.util.Set;

import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.simulator.Statistics.Result;

public class Scenario {

	private Environment environment;
	private Set<Agent> agents = new HashSet<>();

	private final Integer AMOUNT_OF_GOALS;
	private final Integer ENVIRONMENT_SIZE;
	private final Integer AMOUNT_OF_RULES_PER_GOAL;

	private final Integer PERCEPTION_SEQUENCE_FACTOR;

	public Scenario(Integer AMOUNT_OF_GOALS, Integer ENVIRONMENT_SIZE, Integer AMOUNT_OF_RULES_PER_GOAL,
			Integer PERCEPTION_SEQUENCE_FACTOR) {
		this.AMOUNT_OF_GOALS = AMOUNT_OF_GOALS;
		this.ENVIRONMENT_SIZE = ENVIRONMENT_SIZE;
		this.AMOUNT_OF_RULES_PER_GOAL = AMOUNT_OF_RULES_PER_GOAL;
		this.PERCEPTION_SEQUENCE_FACTOR = PERCEPTION_SEQUENCE_FACTOR;

		this.environment = new Environment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, AMOUNT_OF_RULES_PER_GOAL,
				PERCEPTION_SEQUENCE_FACTOR);
		agents = AgentFactory.build(environment.getGoals());
		System.out.println(agents.size());

		Agent next = agents.iterator().next();
		next.perceive(environment.getPerceptionSequence());
		Result result = Statistics.getInstance().processMonitor(next);
		System.out.println(result);
	}

//	Environment.sendPerception();
//	statistics.addResults(Environment.agents);
//	updateGoals

//statistics.run(getScenarioDescriptor(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, RULES_PER_GOAL, STORING_THRESHOLD,
//		RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, DYNAMICS));

	private String getScenarioDescriptor(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer RULES_PER_GOAL, final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final float DYNAMICS) {
		return "GOALS = " + AMOUNT_OF_GOALS + " ; ENVIRONMENT_SIZE = " + ENVIRONMENT_SIZE + " ; RULES_PER_GOAL = "
				+ RULES_PER_GOAL + " ; STORING = " + STORING_THRESHOLD + " ; RETRIEVING = " + RETRIEVING_THRESHOLD
				+ " ; OBLIVION = " + OBLIVION_THRESHOLD;

	}

}
