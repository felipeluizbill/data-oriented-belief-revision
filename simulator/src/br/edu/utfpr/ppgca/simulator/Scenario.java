package br.edu.utfpr.ppgca.simulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.simulator.Statistics.Result;

public class Scenario {

	private Environment environment;
	private List<Agent> agents = new ArrayList<>();

	public Scenario(Integer AMOUNT_OF_GOALS, Integer ENVIRONMENT_SIZE, Integer AMOUNT_OF_RULES_PER_GOAL,
			Integer PERCEPTION_SEQUENCE_FACTOR) throws IOException {

		this.environment = new Environment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, AMOUNT_OF_RULES_PER_GOAL,
				PERCEPTION_SEQUENCE_FACTOR);
		agents.addAll(AgentFactory.build(environment.getGoals()));

		for (int i = 0; i < agents.size(); i++) {
			Agent agent = agents.get(i);
			agent.perceive(environment.getPerceptionSequence());
			Result result = Statistics.getInstance().processMonitor(agent);
			FileUtil.getInstance().save(String.valueOf(i),
					agent.getDescriptor().concat(" ; ").concat(result.toString()));
			System.out.println("[" + i + "/" + agents.size() + "]");
		}

	}

}
