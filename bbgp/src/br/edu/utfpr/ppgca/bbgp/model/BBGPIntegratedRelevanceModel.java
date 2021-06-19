package br.edu.utfpr.ppgca.bbgp.model;

import br.edu.utfpr.ppgca.dbr.model.IntegratedRelevanceModel;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.GoalStatus;

public class BBGPIntegratedRelevanceModel extends IntegratedRelevanceModel {

	protected Float goalInertia(Goal goal) {
		return GoalStatus.getValue(goal.getStatus());
	}

}
