package org.encuestame.core.persistence.pojo;

// Generated 29-may-2009 13:17:50 by Hibernate Tools 3.2.2.GA

/**
 * SurveyGroupProjectId generated by hbm2java
 */
public class SurveyGroupProjectId implements java.io.Serializable {

	private int sgId;
	private int proyectId;

	public SurveyGroupProjectId() {
	}

	public SurveyGroupProjectId(int sgId, int proyectId) {
		this.sgId = sgId;
		this.proyectId = proyectId;
	}

	public int getSgId() {
		return this.sgId;
	}

	public void setSgId(int sgId) {
		this.sgId = sgId;
	}

	public int getProyectId() {
		return this.proyectId;
	}

	public void setProyectId(int proyectId) {
		this.proyectId = proyectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SurveyGroupProjectId))
			return false;
		SurveyGroupProjectId castOther = (SurveyGroupProjectId) other;

		return (this.getSgId() == castOther.getSgId())
				&& (this.getProyectId() == castOther.getProyectId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getSgId();
		result = 37 * result + this.getProyectId();
		return result;
	}

}