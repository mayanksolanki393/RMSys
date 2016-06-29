package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cyb.rms.enums.RmsEnums.Constraints;
import cyb.rms.enums.RmsEnums.Priority;
import cyb.rms.enums.RmsEnums.RequirementStatus;

import cyb.rms.enums.RmsEnums.Type;

@Entity
@Table(name="REQUIREMENTS")
public class Requirement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8613161674360685751L;
	private long id;//PK ID
	private String title;//not_null , length=255	TITLE
	private String description;//length=4096	DESCRIPTION
	private String shortTitle	;//	length=255	SHORTTITLE	--	--	--	--
	private Set<User> creator		;//FK , not_null	--	--	REQUIREMENT_CREATOR	ManyToMany	USER
	private	Set<User> contributor	;//	FK	--	--	REQUIREMENT_CONTIBUTOR	ManyToMany	USER
	private	Date createdOn	;//	not_null	CREATEDON	--	--	--	--
	private	Date lastModifiedOn	;//	not_null	LASTMODIFIEDON	--	--	--	--
	private	Type type;//(enum)	not_null	TYPE	--	--	--	--
	private	Set<Constraints> constraints;//	TBD	TBD	TBD	--	TBD	TBD
	private	Set<String> links;//	TBD	TBD	TBD	TBD	--	TBD	TBD
	private	Set<Elaboration> elaboration;//	TBD	TBD	TBD	--	TBD	TBD
										//private	elaboratedBy	List<User>;//	FK	--	--	REQUIREMENT_ELABORATOR	ManyToMany	USER	
	private	Requirement parent;//	FK	--	PARENTID	--	OneToOne	REQUIREMENT
	private	Priority priority;//	not_null	PRIORITY	--	--	--	--
	private RequirementStatus	status;	// 	not_null	STATUS	--	--	--	--
	private Set<AppFile> files;	//FK	--	--	REQUIREMENT_FILE	OneToMany	FILE
	private Project	project;//	not_null	-	-	PROJECT_REQUIREMENT	ManyToOne	PROJECT
	
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="TITLE",nullable=false,length=255)
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
	
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_CREATOR",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="creator_id", referencedColumnName="id")})  
	public Set<User> getCreator() {
		return creator;
	}
	public void setCreator(Set<User> creator) {
		this.creator = creator;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_CONTIBUTOR",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="contributor_id", referencedColumnName="id")})  
	public Set<User> getContributor() {
		return contributor;
	}
	public void setContributor(Set<User> contributor) {
		this.contributor = contributor;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="CREATEDON",nullable=false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="LASTMODIFIEDON",nullable=false)
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE",nullable=false)
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name="CONSTRAINTS")
	public Set<Constraints> getConstraints() {
		return constraints;
	}
	public void setConstraints(Set<Constraints> constraints) {
		this.constraints = constraints;
	}
	
	@ElementCollection
	@Column(name="LINKS")
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		this.links = links;
	}
	
	@OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_ELABORATION",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="elaboration_id", referencedColumnName="id")})  
	public Set<Elaboration> getElaboration() {
		return elaboration;
	}
	public void setElaboration(Set<Elaboration> elaboration) {
		this.elaboration = elaboration;
	}
	
	

	@OneToOne(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_SUBREQUIREMENT",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="subrequirement_id", referencedColumnName="id")})  
	public Requirement getParent() {
		return parent;
	}
	public void setParent(Requirement parent) {
		this.parent = parent;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="PRIORITY")
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	public RequirementStatus getStatus() {
		return status;
	}
	public void setStatus(RequirementStatus status) {
		this.status = status;
	}
	@OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_FILE",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="file_id", referencedColumnName="id")})  
	public Set<AppFile> getFiles() {
		return files;
	}
	public void setFiles(Set<AppFile> files) {
		this.files = files;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL)  
    @JoinTable(name="PROJECT_REQUIREMENT",  
    joinColumns={@JoinColumn(name="requirement_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="project_id", referencedColumnName="id")})  
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
		Requirement other = (Requirement) obj;
		if (id != other.id)
			return false;
		return true;
	}


	


}





							


