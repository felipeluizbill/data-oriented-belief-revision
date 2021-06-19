package br.edu.utfpr.ppgca.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.prs.core.Clock;
import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.knowledge.CheckingRule;
import br.edu.utfpr.ppgca.prs.knowledge.DeliberatingRule;
import br.edu.utfpr.ppgca.prs.knowledge.ImpossibilityRule;
import br.edu.utfpr.ppgca.prs.knowledge.MotivatingRule;

public class Environment {

	static Collection<Agent> agents = new HashSet<>();
	static List<Belief> beliefs = new ArrayList<>();
	static Set<Goal> goals = new HashSet<>();

	static Float dynamic = 0F;

	private static void prepareAgents(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD) {
		agents.addAll(AgentFactory.buildForgettingAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD));
	}

	private static void prepareBeliefs(final Integer AMOUNT_OF_BELIEFS) {
		for (int i = 0; i < AMOUNT_OF_BELIEFS; i++) {
			beliefs.add(new Belief(new Data(Util.randomString())));
		}
	}

	private static void prepareGoals(final Integer AMOUNT_OF_GOALS, final Integer AMOUNT_OF_RULES_PER_GOAL) {
		for (int i = 0; i < AMOUNT_OF_GOALS; i++) {
			Goal goal = new Goal(Util.randomString(), Util.randomFloat());
			for (int j = 0; j < AMOUNT_OF_RULES_PER_GOAL; j++) {
				Belief belief = beliefs.get(Util.randomInt(beliefs.size()));
				float randomFloat = Util.randomFloat();
				if (randomFloat < 0.25f) {
					goal.addRule(new MotivatingRule(belief, goal));
				} else if (randomFloat < 0.5f) {
					goal.addRule(new ImpossibilityRule(belief, goal));
				} else if (randomFloat < 0.75f) {
					goal.addRule(new DeliberatingRule(belief, goal));
				} else {
					goal.addRule(new CheckingRule(belief, goal));
				}
			}
			goals.add(goal);
		}
	}

	private static void preparePlans() {
		for (Agent agent : agents) {
			for (Goal goal : goals) {
				agent.getPlanLibrary().addGoal(goal);
			}
		}
	}

	public static void sendPerception(final Data data) {
		for (Agent agent : agents) {
			agent.perceive(data);
			agent.run();
		}
	}

	public static boolean terminate() {
		for (Agent agent : agents) {
			if (agent.getPlanLibrary().getGoals().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static void log() {
		for (Agent agent : agents) {
			agent.getMonitor().getLogs().forEach(l -> {
				System.out.println(l);
			});
		}
	}

	public static void updateGoals() {
		Random random = new Random();
		float nextFloat = random.nextFloat();
		if (nextFloat < dynamic) {
			for (Goal g : goals) {
				nextFloat = random.nextFloat();
				if (nextFloat < dynamic) {
					nextFloat = random.nextFloat();
					if (nextFloat >= 0.5F) {
						g.setUtility(g.getUtility() * (float) (1 + dynamic));
					} else {
						g.setUtility(g.getUtility() * (float) (1 - dynamic));
					}
				}
			}
		}
	}

	public static void prepareEnvironment(final Integer AMOUNT_OF_GOALS, final Integer AMOUNT_OF_BELIEFS,
			final Integer AMOUNT_OF_RULES_PER_GOAL, final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, float GOALS_DYNAMIC) {
		prepareAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD);
		prepareBeliefs(AMOUNT_OF_BELIEFS);
		prepareGoals(AMOUNT_OF_GOALS, AMOUNT_OF_RULES_PER_GOAL);
		preparePlans();
		dynamic = GOALS_DYNAMIC;
	}

	public static void main(String[] args) {
		prepareEnvironment(1000, 300, 10, 0.2F, 0.19F, 0.15F, 0F);
		while (true) {
			Clock.getInstance().incrementCounter();
			sendPerception(beliefs.get(Util.randomInt(beliefs.size())).getData());
			updateGoals();
			if (terminate()) {
				break;
			}
		}

	}

}
