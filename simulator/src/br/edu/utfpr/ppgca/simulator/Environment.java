package br.edu.utfpr.ppgca.simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.knowledge.CheckingRule;
import br.edu.utfpr.ppgca.prs.knowledge.DeliberatingRule;
import br.edu.utfpr.ppgca.prs.knowledge.ImpossibilityRule;
import br.edu.utfpr.ppgca.prs.knowledge.MotivatingRule;

public class Environment {

	private List<Belief> beliefs = new ArrayList<>();
	private Set<Goal> goals = new HashSet<>();
	private List<Data> perceptionSequence = new ArrayList<>();

	public Environment(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer AMOUNT_OF_RULES_PER_GOAL, final Integer PERCEPTION_SEQUENCE_FACTOR) {
		prepareBeliefs(ENVIRONMENT_SIZE);
		prepareGoals(AMOUNT_OF_GOALS, AMOUNT_OF_RULES_PER_GOAL);
		drawPerceptions(PERCEPTION_SEQUENCE_FACTOR);
	}

	public List<Data> getPerceptionSequence() {
		return new ArrayList<Data>(this.perceptionSequence);
	}

	public Set<Goal> getGoals() {
		return new HashSet<>(this.goals);
	}

	private void prepareBeliefs(final Integer AMOUNT_OF_BELIEFS) {
		for (int i = 0; i < AMOUNT_OF_BELIEFS; i++) {
			this.beliefs.add(new Belief(new Data(Util.getInstance().nextInt())));
		}
	}

	private void prepareGoals(final Integer AMOUNT_OF_GOALS, final Integer AMOUNT_OF_RULES_PER_GOAL) {
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

	private void drawPerceptions(final Integer PERCEPTION_SEQUENCE_FACTOR) {
		for (int i = 0; i < beliefs.size() * PERCEPTION_SEQUENCE_FACTOR; i++) {
			int randomInt = Util.getInstance().randomInt(beliefs.size());
			this.perceptionSequence.add(beliefs.get(randomInt).getData());
		}
	}

//	public static void updateGoals() {
//		Random random = new Random();
//		float nextFloat = random.nextFloat();
//		if (nextFloat < dynamic) {
//			for (Goal g : goals) {
//				nextFloat = random.nextFloat();
//				if (nextFloat < dynamic) {
//					nextFloat = random.nextFloat();
//					if (nextFloat >= 0.5F) {
//						g.setUtility(g.getUtility() * (float) (1 + dynamic));
//					} else {
//						g.setUtility(g.getUtility() * (float) (1 - dynamic));
//					}
//				}
//			}
//		}
//	}

}
