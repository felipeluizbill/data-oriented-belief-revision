package br.edu.utfpr.ppgca.prs.entities;

import java.util.Arrays;
import java.util.List;

public enum GoalStatus {

	SLEEPING, ACTIVE, PURSUABLE, CHOSEN, EXECUTIVE;

	public static float getValue(GoalStatus status) {
		List<GoalStatus> asList = Arrays.asList(GoalStatus.values());
		int indexOf = asList.indexOf(status) + 1;
		return (float) indexOf / (float) asList.size();
	}

}
