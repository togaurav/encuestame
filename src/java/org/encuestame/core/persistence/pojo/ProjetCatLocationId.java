package org.encuestame.core.persistence.pojo;

// Generated 29-may-2009 13:17:50 by Hibernate Tools 3.2.2.GA

/**
 * ProjetCatLocationId generated by hbm2java
 */
public class ProjetCatLocationId implements java.io.Serializable {

	private int locateId;
	private int projectId;

	public ProjetCatLocationId() {
	}

	public ProjetCatLocationId(int locateId, int projectId) {
		this.locateId = locateId;
		this.projectId = projectId;
	}

	public int getLocateId() {
		return this.locateId;
	}

	public void setLocateId(int locateId) {
		this.locateId = locateId;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjetCatLocationId))
			return false;
		ProjetCatLocationId castOther = (ProjetCatLocationId) other;

		return (this.getLocateId() == castOther.getLocateId())
				&& (this.getProjectId() == castOther.getProjectId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getLocateId();
		result = 37 * result + this.getProjectId();
		return result;
	}

}