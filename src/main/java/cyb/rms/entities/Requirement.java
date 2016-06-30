package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cyb.rms.enums.RmsEnums;
import cyb.rms.enums.RmsEnums.Constraints;
import cyb.rms.enums.RmsEnums.Priority;
import cyb.rms.enums.RmsEnums.RequirementStatus;
import cyb.rms.enums.RmsEnums.Type;

@Entity
@Table(name="REQUIREMENTS")
@NamedQuery(name="Requirement.list",query="Select r from Requirement r where r.status NOT IN('DELETED')")
public class Requirement implements Serializable{
	private static final long serialVersionUID = -8613161674360685751L;
	
	//state members
	private long id;
	private String title;
	private String description;
	private String shortTitle;
	private List<User> creators;
	private	List<User> contributors;
	private	Date createdOn;
	private	Date lastModifiedOn;
	private	Type type;
	private	Set<Constraints> constraints;
	private	Set<String> links;
	private	List<Elaboration> elaborations;	
	private	Requirement parent;
	private	Priority priority;
	private RequirementStatus status;
	private List<AppFile> files;
	private Project	project;
	
	public Requirement(){
		initNulls();
	}
	
	public Requirement(String title, String description, String shortTitle,
			List<User> creators, List<User> contributors, Date createdOn,
			Date lastModifiedOn, Type type, Set<Constraints> constraints,
			Set<String> links, List<Elaboration> elaborations,
			Requirement parent, Priority priority, RequirementStatus status,
			List<AppFile> files, Project project) {
		super();
		this.title = title;
		this.description = description;
		this.shortTitle = shortTitle;
		this.creators = creators;
		this.contributors = contributors;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.type = type;
		this.constraints = constraints;
		this.links = links;
		this.elaborations = elaborations;
		this.parent = parent;
		this.priority = priority;
		this.status = status;
		this.files = files;
		this.project = project;
		initNulls();
	}
	
	
	public Requirement(long id, String title, String description,
			String shortTitle, List<User> creators, List<User> contributors,
			Date createdOn, Date lastModifiedOn, Type type,
			Set<Constraints> constraints, Set<String> links,
			List<Elaboration> elaborations, Requirement parent,
			Priority priority, RequirementStatus status, List<AppFile> files,
			Project project) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.shortTitle = shortTitle;
		this.creators = creators;
		this.contributors = contributors;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.type = type;
		this.constraints = constraints;
		this.links = links;
		this.elaborations = elaborations;
		this.parent = parent;
		this.priority = priority;
		this.status = status;
		this.files = files;
		this.project = project;
		initNulls();
	}


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
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="CREATORID")})  
	public List<User> getCreators() {
		return creators;
	}
	public void setCreators(List<User> creator) {
		this.creators = creator;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_CONTRIBUTORS",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="CONTRIBUTORID")})  
	public List<User> getContributors() {
		return contributors;
	}
	public void setContributors(List<User> contributor) {
		this.contributors = contributor;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDON",nullable=false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
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
    @JoinTable(name="REQUIREMENT_ELABORATIONS",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="ELABORATIONID")})  
	public List<Elaboration> getElaborations() {
		return elaborations;
	}
	public void setElaborations(List<Elaboration> elaboration) {
		this.elaborations = elaboration;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)  
    @JoinTable(name="REQUIREMENT_SUBREQUIREMENTS",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="SUBREQUIREMENTID")})  
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
    @JoinTable(name="REQUIREMENT_FILES",  
    	joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    	inverseJoinColumns={@JoinColumn(name="FILEID")})  
	public List<AppFile> getFiles() {
		return files;
	}
	public void setFiles(List<AppFile> files) {
		this.files = files;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)  
    @JoinTable(name="PROJECT_REQUIREMENTS",  
    joinColumns={@JoinColumn(name="PROJECTID")},  
    inverseJoinColumns={@JoinColumn(name="REQUIREMENTID")})  
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
	
	public void initNulls(){
		if(creators==null){
			creators = new LinkedList<User>();
		}
		if(contributors==null){
			contributors = new LinkedList<User>();
		}
		if(constraints==null){
			constraints = new HashSet<Constraints>();
		}
		if(links==null){
			links = new HashSet<String>();
		}
		if(elaborations==null){
			elaborations = new LinkedList<Elaboration>();
		}
		if(files==null){
			files = new LinkedList<AppFile>();
		}
	}
}