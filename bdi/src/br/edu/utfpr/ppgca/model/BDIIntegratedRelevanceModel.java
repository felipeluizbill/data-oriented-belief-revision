package br.edu.utfpr.ppgca.model;

import br.edu.utfpr.ppgca.dbr.model.IntegratedRelevanceModel;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.GoalStatus;

public class BDIIntegratedRelevanceModel extends IntegratedRelevanceModel {

	protected Float goalInertia(Goal goal) {
		if (GoalStatus.EXECUTIVE.equals(goal.getStatus())) {
			return 1F;
		} else
			return 0.5F;
	}

}
