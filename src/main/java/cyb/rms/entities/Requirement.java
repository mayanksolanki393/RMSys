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
import javax.persistence.FetchType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import cyb.rms.enums.RmsEnums;
import cyb.rms.enums.RmsEnums.Constraints;
import cyb.rms.enums.RmsEnums.Priority;
import cyb.rms.enums.RmsEnums.RequirementStatus;
import cyb.rms.enums.RmsEnums.Type;
import cyb.rms.rest.view.RequirementView;

@Entity
@Table(name="REQUIREMENTS")
@NamedQuery(name="Requirement.list",query="Select r from Requirement r where r.status NOT IN('DELETED')")
public class Requirement implements Serializable{
	private static final long serialVersionUID = -8613161674360685751L;
	
	//state members
	@JsonView(RequirementView.Minimal.class)
	private long id;
	
	@JsonView(RequirementView.Minimal.class)
	private String title;
	
	@JsonView(RequirementView.Detailed.class)
	private String description;
	
	@JsonView(RequirementView.Minimal.class)
	private String shortTitle;
	
	@JsonView(RequirementView.Detailed.class)
	private List<User> creators;
	
	@JsonView(RequirementView.Detailed.class)
	private	List<User> contributors;
	
	@JsonView(RequirementView.Minimal.class)
	private	Date createdOn;
	
	@JsonView(RequirementView.Minimal.class)
	private	Date lastModifiedOn;
	
	@JsonView(RequirementView.Minimal.class)
	private	Type type;
	
	@JsonView(RequirementView.Detailed.class)
	private	Set<Constraints> constraints;
	
	@JsonView(RequirementView.Detailed.class)
	private	Set<String> links;
	
	@JsonView(RequirementView.Detailed.class)
	private	List<Elaboration> elaborations;	
	
	@JsonView(RequirementView.Minimal.class)
	private	Requirement parent;
	
	@JsonView(RequirementView.Minimal.class)
	private	Priority priority;
	
	@JsonView(RequirementView.Minimal.class)
	private RequirementStatus status;
	
	@JsonView(RequirementView.Detailed.class)
	private List<AppFile> files;
	
	@JsonView(RequirementView.Detailed.class)
	private Project	project;
	
	@JsonIgnore
	private List<Requirement> childRequirements;
	
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
		if(id!=0)
		this.id = id;
	}
	
	@Column(name="TITLE",nullable=false,length=255)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title != null)
		this.title = title;
	}
	
	@Column(name="DESCRIPTION",length=4096)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		//if(description != null)
		this.description = description;
	}
	
	@Column(name="SHORTTITLE",length=255)
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		//if(shortTitle!=null)
		this.shortTitle = shortTitle;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)  
    @JoinTable(name="REQUIREMENT_CREATOR",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="CREATORID")}) 
	@Fetch(FetchMode.SELECT)
	public List<User> getCreators() {
		return creators;
	}
	public void setCreators(List<User> creator) {
		//if(creator!=null)
		this.creators = creator;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)  
    @JoinTable(name="REQUIREMENT_CONTRIBUTORS",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="CONTRIBUTORID")}) 
	@Fetch(FetchMode.SELECT)
	public List<User> getContributors() {
		return contributors;
	}
	public void setContributors(List<User> contributor) {
		//if(contributor!=null)
		this.contributors = contributor;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATEDON",nullable=false)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		//if(createdOn!=null)
		this.createdOn = createdOn;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTMODIFIEDON",nullable=false)
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		//if(lastModifiedOn!=null)
		this.lastModifiedOn = lastModifiedOn;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE",nullable=false)
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		//if(type!=null)
		this.type = type;
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name="CONSTRAINTS")
	@Fetch(FetchMode.SELECT)
	public Set<Constraints> getConstraints() {
		return constraints;
	}
	public void setConstraints(Set<Constraints> constraints) {
		//if(constraints!=null)
		this.constraints = constraints;
	}
	
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="LINKS")
	@Fetch(FetchMode.SELECT)
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		//if(links!=null)
		this.links = links;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    @JoinTable(name="REQUIREMENT_ELABORATIONS",  
    joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="ELABORATIONID")}) 
	@Fetch(FetchMode.SELECT)
	public List<Elaboration> getElaborations() {
		return elaborations;
	}
	public void setElaborations(List<Elaboration> elaboration) {
		//if(elaborations!=null)
		this.elaborations = elaboration;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    @JoinTable(name="REQUIREMENT_SUBREQUIREMENTS",  
    joinColumns={@JoinColumn(name="SUBREQUIREMENTID")},  
    inverseJoinColumns={@JoinColumn(name="REQUIREMENTID")}) 
	public Requirement getParent() {
		return parent;
	}
	public void setParent(Requirement parent) {
		//if(parent!=null)
		this.parent = parent;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="PRIORITY")
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		//if(priority!=null)
		this.priority = priority;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	public RequirementStatus getStatus() {
		return status;
	}
	public void setStatus(RequirementStatus status) {
		//if(status!=null)
		this.status = status;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    @JoinTable(name="REQUIREMENT_FILES",  
    	joinColumns={@JoinColumn(name="REQUIREMENTID")},  
    	inverseJoinColumns={@JoinColumn(name="FILEID")})
	@Fetch(FetchMode.SELECT)
	public List<AppFile> getFiles() {
		return files;
	}
	public void setFiles(List<AppFile> files) {
		//if(files!=null)
		this.files = files;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
    @JoinTable(name="PROJECT_REQUIREMENTS",  
    joinColumns={@JoinColumn(name="PROJECTID")},  
    inverseJoinColumns={@JoinColumn(name="REQUIREMENTID")})
	@Fetch(FetchMode.SELECT)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		//if(project!=null)
		this.project = project;
	}
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	public List<Requirement> getChildRequirements() {
		return childRequirements;
	}

	public void setChildRequirements(List<Requirement> childRequirements) {
		//if(childRequirements!=null)
		this.childRequirements = childRequirements;
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
		if(childRequirements==null){
			childRequirements = new LinkedList<>();
		}
	}
}