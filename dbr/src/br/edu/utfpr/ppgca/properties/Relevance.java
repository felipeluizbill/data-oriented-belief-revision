package br.edu.utfpr.ppgca.properties;

import java.util.Collection;

import br.edu.utfpr.ppgca.dbr.entities.DBRBelief;
import br.edu.utfpr.ppgca.dbr.entities.DBRData;
import br.edu.utfpr.ppgca.dbr.layer.BeliefLayer;
import br.edu.utfpr.ppgca.dbr.layer.DataLayer;
import br.edu.utfpr.ppgca.dbr.model.AbstractRelevanceModel;
import br.edu.utfpr.ppgca.prs.core.PlanLibrary;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class Relevance extends AbstractEpistemicProperty {

	public static void update(BeliefLayer beliefLayer, PlanLibrary planLibrary, AbstractRelevanceModel relevanceModel) {
		float maxUtilitySum = 0;
		float minUtilitySum = Float.MAX_VALUE;

		for (DBRBelief belief : beliefLayer.getAll()) {
			Collection<Goal> supportingGoals = planLibrary.selectSupportingGoals(belief);
			Float relevanceValue = relevanceModel.compute(belief, supportingGoals);

			if (relevanceValue > maxUtilitySum) {
				maxUtilitySum = relevanceValue;
			}

			if (relevanceValue < minUtilitySum) {
				minUtilitySum = relevanceValue;
			}
			belief.getData().getRelevance().setValue(relevanceValue);
		}

		minMaxStandardization(beliefLayer.getAll(), minUtilitySum, maxUtilitySum);
	}

	public static void update(DataLayer dataLayer, PlanLibrary planLibrary, AbstractRelevanceModel relevanceModel) {
		float maxUtilitySum = 0;
		float minUtilitySum = Float.MAX_VALUE;

		for (DBRData data : dataLayer.getAll()) {
			Collection<Goal> supportingGoals = planLibrary.selectSupportingGoals(data);
			Float relevanceValue = relevanceModel.compute(data, supportingGoals);

			if (relevanceValue > maxUtilitySum) {
				maxUtilitySum = relevanceValue;
			}

			if (relevanceValue < minUtilitySum) {
				minUtilitySum = relevanceValue;
			}
			data.getRelevance().setValue(relevanceValue);
		}

		minMaxStandardizationData(dataLayer.getAll(), minUtilitySum, maxUtilitySum);
	}

	@Override
	public String toString() {
		return "Relevance [value=" + value + "]";
	}

}
