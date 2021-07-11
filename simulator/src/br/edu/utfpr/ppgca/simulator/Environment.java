package br.edu.utfpr.ppgca.simulator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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

	private Float DYNAMICS = 0F;

	public Environment(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer AMOUNT_OF_RULES_PER_GOAL, final Integer PERCEPTION_SEQUENCE_FACTOR) {
		prepareBeliefs(ENVIRONMENT_SIZE);
		prepareGoals(AMOUNT_OF_GOALS, AMOUNT_OF_RULES_PER_GOAL);
		drawPerceptions(PERCEPTION_SEQUENCE_FACTOR);
	}

	public Environment(final Integer AMOUNT_OF_GOALS, final Integer ENVIRONMENT_SIZE,
			final Integer AMOUNT_OF_RULES_PER_GOAL, final Integer PERCEPTION_SEQUENCE_FACTOR, final Float DYNAMICS) {
		this.DYNAMICS = DYNAMICS;
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

	public void updateGoals() {
		Random random = new Random();
		List<Goal> goalsList = new ArrayList<>(this.goals);
		for (Goal goal : goalsList) {
			float nextFloat = random.nextFloat();
			if(nextFloat < (DYNAMICS /100)) {//a cada 1 dos 4000 ciclos vai atualizar 1000*0,1/4000 objetivos = 0,1 , esperado = 400
				if(random.nextFloat() > 0.5F) {//increase
					System.out.print(goal.getUtility());
					goal.setUtility(goal.getUtility() * (float) (1 + DYNAMICS));					
					System.out.print("aumentou" + goal.getUtility());
				}else {//decrease
					System.out.print(goal.getUtility());
					goal.setUtility(goal.getUtility() * (float) (1 - DYNAMICS));										
					System.out.print("diminuiu" + goal.getUtility());
				}
			}
		}
		
	}
	
	

}
