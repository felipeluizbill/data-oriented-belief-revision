package br.edu.utfpr.ppgca.prs.knowledge;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class ProceduralRule extends AbstractRule<Goal> {

	public ProceduralRule(Belief condition, Goal consequent) {
		super(condition, consequent);
		consequent.addRule(this);
	}

}
