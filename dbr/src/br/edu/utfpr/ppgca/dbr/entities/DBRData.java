package br.edu.utfpr.ppgca.dbr.entities;

import br.edu.utfpr.ppgca.properties.Credibility;
import br.edu.utfpr.ppgca.properties.Importance;
import br.edu.utfpr.ppgca.properties.Likeability;
import br.edu.utfpr.ppgca.properties.Relevance;
import br.edu.utfpr.ppgca.prs.entities.Data;

public class DBRData extends Data {

	public DBRData(String content, String source) {
		super(content, source);
	}

	public DBRData(String content) {
		super(content, SELF);
	}

	public DBRData(Data perception) {
		super(perception.getContent(), perception.getSource());
	}

	private Relevance relevance = new Relevance();
	private Importance importance = new Importance();
	private Credibility credibility = new Credibility();
	private Likeability likeability = new Likeability();

	public Relevance getRelevance() {
		return relevance;
	}

	public void setRelevance(Relevance relevance) {
		this.relevance = relevance;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	public Credibility getCredibility() {
		return credibility;
	}

	public void setCredibility(Credibility credibility) {
		this.credibility = credibility;
	}

	public Likeability getLikeability() {
		return likeability;
	}

	public void setLikeability(Likeability likeability) {
		this.likeability = likeability;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBRData other = (DBRData) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Data [content=" + content + ", source=" + source + ", relevance=" + relevance + ", importance="
				+ importance + ", credibility=" + credibility + ", likeability=" + likeability + "]";
	}

}
