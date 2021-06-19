package br.edu.utfpr.ppgca.dbr;

import br.edu.utfpr.ppgca.dbr.model.AbstractRelevanceModel;
import br.edu.utfpr.ppgca.dbr.model.DefaultRelevanceModel;

public class Parameters {

	private Float focusThreshold = 0F;
	private Float selectThreshold = 0F;
	private Float storeThreshold = 0F;
	private Float retrieveThreshold = 0F;
	private Float oblivionThreshold = 0F;

	private AbstractRelevanceModel relevanceModel = new DefaultRelevanceModel();

	public Parameters() {

	}

	public Parameters(Float storeThreshold, Float retrieveThreshold, Float oblivionThreshold) {
		super();
		this.storeThreshold = storeThreshold;
		this.retrieveThreshold = retrieveThreshold;
		this.oblivionThreshold = oblivionThreshold;
	}

	public Float getFocusThreshold() {
		return focusThreshold;
	}

	public void setFocusThreshold(Float focusThreshold) {
		this.focusThreshold = focusThreshold;
	}

	public Float getSelectThreshold() {
		return selectThreshold;
	}

	public void setSelectThreshold(Float selectThreshold) {
		this.selectThreshold = selectThreshold;
	}

	public Float getStoreThreshold() {
		return storeThreshold;
	}

	public void setStoreThreshold(Float storeThreshold) {
		this.storeThreshold = storeThreshold;
	}

	public Float getRetrieveThreshold() {
		return retrieveThreshold;
	}

	public void setRetrieveThreshold(Float retrieveThreshold) {
		this.retrieveThreshold = retrieveThreshold;
	}

	public Float getOblivionThreshold() {
		return oblivionThreshold;
	}

	public void setOblivionThreshold(Float oblivionThreshold) {
		this.oblivionThreshold = oblivionThreshold;
	}

	public AbstractRelevanceModel getRelevanceModel() {
		return relevanceModel;
	}

	public void setRelevanceModel(AbstractRelevanceModel relevanceModel) {
		this.relevanceModel = relevanceModel;
	}

}
