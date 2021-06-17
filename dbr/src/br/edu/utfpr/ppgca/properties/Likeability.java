package br.edu.utfpr.ppgca.properties;

import java.util.Collection;

import br.edu.utfpr.ppgca.dbr.layer.DataLayer;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class Likeability extends AbstractEpistemicProperty {

	public static void update(DataLayer dataLayer, Collection<Goal> goals) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Likeability [value=" + value + "]";
	}

}
