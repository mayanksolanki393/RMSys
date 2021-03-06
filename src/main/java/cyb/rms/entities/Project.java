package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonView;

import cyb.rms.enums.RmsEnums.ProjectStatus;
import cyb.rms.rest.view.ProjectView;
import cyb.rms.rest.view.UserView;
import cyb.rms.rest.view.ProjectView.ProjectDetailsView;

@Entity
@Table(name="PROJECTS")
@NamedQuery(name="Project.list",query="Select p from Project p where p.status NOT IN('DELETED')")
public class Project implements Serializable{
	private static final long serialVersionUID = 1581627407212776620L;
	
	//state members
	@JsonView(ProjectView.List.class)
	private long id;
	
	@JsonView(ProjectView.List.class)
	private String title;
	
	@JsonView(ProjectView.ProjectDetailsView.class)
	private String description;	
	
	@JsonView(ProjectView.List.class)
	private String shortTitle;	
	
	@JsonView(ProjectView.List.class)
	private User creator;
	
	@JsonView(ProjectView.List.class)
	private Date createdOn;
	
	@JsonView(ProjectView.List.class)
	private Date lastModifiedOn;
	
	@JsonView(ProjectView.List.class)
	private ProjectStatus status;
	
	@JsonView(ProjectView.ProjectDetailsView.class)
	private List<User> users;
	
	@JsonView(ProjectView.ProjectDetailsView.class)
	private List<Requirement> requirements;	
	
	//constructors
	public Project(){
		initNulls();
	}
	
	public Project(String title, String description, String shortTitle,
			User creator, Date createdOn, Date lastModifiedOn,
			ProjectStatus status, List<User> users) {
		super();
		this.title = title;
		this.description = description;
		this.shortTitle = shortTitle;
		this.creator = creator;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.status = status;
		this.users = users;
		initNulls();
	}
	
	public Project(long id, String title, String description,
			String shortTitle, User creator, Date createdOn,
			Date lastModifiedOn, ProjectStatus status, List<User> users) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.shortTitle = shortTitle;
		this.creator = creator;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.status = status;
		this.users = users;
		initNulls();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column(name="LASTMODIFIEDON",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
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
	

	 @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	 @JoinTable(name="USERS_PROJECTS",  
	    joinColumns={@JoinColumn(name="PROJECTID")},  
	    inverseJoinColumns={@JoinColumn(name="USERID")})
	@JsonView(UserView.WithoutProject.class)
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@OneToMany(mappedBy="project",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
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
	
	public void initNulls(){
		if(users==null){
			users = new LinkedList<>();
		}
		if(requirements==null){
			requirements = new LinkedList<>();
		}
	}
}
