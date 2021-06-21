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
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		agents.addAll(AgentFactory.buildDefaultAgents());
		agents.addAll(AgentFactory.buildStoringRetrievingAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD));
		agents.addAll(AgentFactory.buildForgettingAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD));
		agents.forEach(a -> a.getBeliefBase().setMemorySize(MEMORY_SIZE));
	}

	private static void prepareBeliefs(final Integer AMOUNT_OF_BELIEFS) {
		for (int i = 0; i < AMOUNT_OF_BELIEFS; i++) {
			beliefs.add(new Belief(new Data(Util.getInstance().nextInt())));
		}
	}

	private static void prepareGoals(final Integer AMOUNT_OF_GOALS, final Integer AMOUNT_OF_RULES_PER_GOAL) {
		for (int i = 0; i < AMOUNT_OF_GOALS; i++) {
			Goal goal = new Goal(Util.getInstance().randomString(), Util.getInstance().randomFloat());
			for (int j = 0; j < AMOUNT_OF_RULES_PER_GOAL; j++) {
				Belief belief = beliefs.get(Util.getInstance().randomInt(beliefs.size()));
				float randomFloat = Util.getInstance().randomFloat();
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
		agents.forEach(a -> {
			goals.forEach(g -> {
				a.getPlanLibrary().addGoal(g);
			});
		});
	}

	public static void sendPerception() {
		Data perception = drawPerception();

		agents.forEach(a -> {
			a.perceive(perception);
			a.run();
		});

		Clock.getInstance().incrementCounter();
		Environment.updateGoals();
	}

	public static Data drawPerception() {
		int randomInt = Util.getInstance().randomInt(beliefs.size());
		return beliefs.get(randomInt).getData();
	}

	public static boolean terminate() {
		return agents.stream().anyMatch(a -> a.getPlanLibrary().getGoals().isEmpty());
	}

	public static void printLogs() {
		agents.forEach(a -> a.getMonitor().printLog());
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
			final float OBLIVION_THRESHOLD, float GOALS_DYNAMIC, final Integer MEMORY_SIZE) {
		agents.clear();
		beliefs.clear();
		goals.clear();

		prepareAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE);
		prepareBeliefs(AMOUNT_OF_BELIEFS);
		prepareGoals(AMOUNT_OF_GOALS, AMOUNT_OF_RULES_PER_GOAL);
		preparePlans();
		dynamic = GOALS_DYNAMIC;
	}

	public static void dump() {
		System.out.println("dumping");

	}

}
