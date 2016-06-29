package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cyb.rms.enums.RmsEnums.ProjectStatus;

@Entity
//@table is nessary to create better tables
@Table(name="PROJECTS")
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1581627407212776620L;
	private long id;//PK ID
	private String title;//not_null , length=255	TITLE	-
	private String description;//length=4096	DESCRIPTION	
	private String shortTitle;//length=255	SHORTTITLE	
	private User creator;//	FK	--	CREATORID	--	ManyToOne	USER
	private Date createdOn;//		not_null	CREATEDON
	private Date lastModifiedOn;//	not_null	LASTMODIFIEDON
	private ProjectStatus status;	//	not_null	STATUS
	private Set<User> users;//USER_PROJECT	ManyToMany
	//private Set<Requirement> requirements;//PROJECT_REQUIREMENT	ManyToMany	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@column is nessary unless its an join type field creates better table
	@Column(name="ID")
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="TITLE",length=255,nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="DESCRIPTION",length=4096)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="SHORTTITLE",length=255)
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CREATORID")
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Column(name="CREATEDON",nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Column(name="LASTMODIFIEDON",nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	@Column(name="STATUS",nullable=false)
	@Enumerated(EnumType.STRING)
	public ProjectStatus getStatus() {
		return status;
	}
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
	

	 @ManyToMany(cascade=CascadeType.ALL)  
	    @JoinTable(name="USER_PROJECT",  
	    joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},  
	    inverseJoinColumns={@JoinColumn(name="project_id", referencedColumnName="id")})
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Project other = (Project) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
