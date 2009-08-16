package org.encuestame.core.persistence.pojo;

// Generated 29-may-2009 13:17:50 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SecUsers generated by hbm2java
 */
public class SecUsers implements java.io.Serializable {

	private Integer uid;
	private String name;
	private String email;
	private String username;
	private String password;
	private Boolean status;
	private String inviteCode;
	private Date dateNew;
	private String publisher;
	private String owner;
	private String twitter;
	private Set<SecGroupUser> secGroupUsers = new HashSet<SecGroupUser>(0);
	private Set<SecUserPermission> secUserPermissions = new HashSet<SecUserPermission>(
			0);
	private Set<CatLocationUser> catLocationUsers = new HashSet<CatLocationUser>(
			0);
	private Set<SurveyResultMod> surveyResultMods = new HashSet<SurveyResultMod>(
			0);
	private Set<ProjectUser> projectUsers = new HashSet<ProjectUser>(0);
	private Set<QuestionColettion> questionColettions = new HashSet<QuestionColettion>(
			0);
	private Set<Surveys> surveyses = new HashSet<Surveys>(0);

	public SecUsers() {
	}

	public SecUsers(String name, String email, String password, Boolean status,
			Date dateNew, String publisher) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.dateNew = dateNew;
		this.publisher = publisher;
	}

	public SecUsers(String name, String email, String username,
			String password, Boolean status, String inviteCode, Date dateNew,
			String publisher, String owner, String twitter,
			Set<SecGroupUser> secGroupUsers,
			Set<SecUserPermission> secUserPermissions,
			Set<CatLocationUser> catLocationUsers,
			Set<SurveyResultMod> surveyResultMods,
			Set<ProjectUser> projectUsers,
			Set<QuestionColettion> questionColettions, Set<Surveys> surveyses) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.status = status;
		this.inviteCode = inviteCode;
		this.dateNew = dateNew;
		this.publisher = publisher;
		this.owner = owner;
		this.twitter = twitter;
		this.secGroupUsers = secGroupUsers;
		this.secUserPermissions = secUserPermissions;
		this.catLocationUsers = catLocationUsers;
		this.surveyResultMods = surveyResultMods;
		this.projectUsers = projectUsers;
		this.questionColettions = questionColettions;
		this.surveyses = surveyses;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Date getDateNew() {
		return this.dateNew;
	}

	public void setDateNew(Date dateNew) {
		this.dateNew = dateNew;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Set<SecGroupUser> getSecGroupUsers() {
		return this.secGroupUsers;
	}

	public void setSecGroupUsers(Set<SecGroupUser> secGroupUsers) {
		this.secGroupUsers = secGroupUsers;
	}

	public Set<SecUserPermission> getSecUserPermissions() {
		return this.secUserPermissions;
	}

	public void setSecUserPermissions(Set<SecUserPermission> secUserPermissions) {
		this.secUserPermissions = secUserPermissions;
	}

	public Set<CatLocationUser> getCatLocationUsers() {
		return this.catLocationUsers;
	}

	public void setCatLocationUsers(Set<CatLocationUser> catLocationUsers) {
		this.catLocationUsers = catLocationUsers;
	}

	public Set<SurveyResultMod> getSurveyResultMods() {
		return this.surveyResultMods;
	}

	public void setSurveyResultMods(Set<SurveyResultMod> surveyResultMods) {
		this.surveyResultMods = surveyResultMods;
	}

	public Set<ProjectUser> getProjectUsers() {
		return this.projectUsers;
	}

	public void setProjectUsers(Set<ProjectUser> projectUsers) {
		this.projectUsers = projectUsers;
	}

	public Set<QuestionColettion> getQuestionColettions() {
		return this.questionColettions;
	}

	public void setQuestionColettions(Set<QuestionColettion> questionColettions) {
		this.questionColettions = questionColettions;
	}

	public Set<Surveys> getSurveyses() {
		return this.surveyses;
	}

	public void setSurveyses(Set<Surveys> surveyses) {
		this.surveyses = surveyses;
	}

}