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
			Integer PERCEPTION_SEQUENCE_FACTOR, final Float DYNAMICS) throws IOException {

		this.environment = new Environment(AMOUNT_OF_GOALS, ENVIRONMENT_SIZE, AMOUNT_OF_RULES_PER_GOAL,
				PERCEPTION_SEQUENCE_FACTOR, DYNAMICS);
		this.agents.addAll(AgentFactory.buildOpt(environment.getGoals()));
	}

	public void run() {		
		agents.parallelStream().forEach(a -> {
			int indexOf = agents.indexOf(a);
			a.perceive(environment.getPerceptionSequence());
			environment.updateGoals();
			Result result = Statistics.getInstance().processMonitor(a);
			try {
				FileUtil.getInstance().save(String.valueOf(indexOf), a.getDescriptor().concat(result.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("[" + indexOf + "/" + agents.size() + "]");

		});

	}

}
