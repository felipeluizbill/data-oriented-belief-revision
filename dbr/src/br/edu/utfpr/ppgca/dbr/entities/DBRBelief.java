package br.edu.utfpr.ppgca.dbr.entities;

import br.edu.utfpr.ppgca.prs.entities.Belief;

public class DBRBelief extends Belief {

	public DBRBelief(DBRData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	public DBRData getData() {
		return (DBRData) this.data;
	}

}
