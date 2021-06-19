package br.edu.utfpr.soar.model;

import br.edu.utfpr.ppgca.dbr.model.IntegratedRelevanceModel;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class SOARIntegratedRelevanceModel extends IntegratedRelevanceModel {

	protected Float goalInertia(Goal goal) {
		return 1F;
	}

}
