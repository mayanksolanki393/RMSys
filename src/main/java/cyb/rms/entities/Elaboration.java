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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cyb.rms.enums.RmsEnums.ElaborationStatus;

@Entity
@Table(name = "ELABORATIONS")
@NamedQuery(name="Elaboration.list",query="Select el from Elaboration el WHERE el.status NOT IN('DELETED')")
public class Elaboration implements Serializable {
	
	private static final long serialVersionUID = 4112167127514138700L;
	
	//state members
	private long id;
	private String title;
	private String elaboration;
	private User elaborationBy;
	private Date createdOn;
	private Date lastModifiedOn;
	private ElaborationStatus status;
	private List<AppFile> files;
	
	//constructor
	public Elaboration(){
		initNulls();
	}
	
	public Elaboration(String title, String elaboration, User elaborationBy,
			Date createdOn, Date lastModifiedOn, ElaborationStatus status,
			List<AppFile> files) {
		super();
		this.title = title;
		this.elaboration = elaboration;
		this.elaborationBy = elaborationBy;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.status = status;
		this.files = files;
		initNulls();
	}

	public Elaboration(long id, String title, String elaboration,
			User elaborationBy, Date createdOn, Date lastModifiedOn,
			ElaborationStatus status, List<AppFile> files) {
		super();
		this.id = id;
		this.title = title;
		this.elaboration = elaboration;
		this.elaborationBy = elaborationBy;
		this.createdOn = createdOn;
		this.lastModifiedOn = lastModifiedOn;
		this.status = status;
		this.files = files;
		initNulls();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "TITLE", length = 255, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ELABORATION", length = 4096, nullable = false)
	public String getElaboration() {
		return elaboration;
	}
	public void setElaboration(String elaboration) {
		this.elaboration = elaboration;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ELABORATEDBY")
	public User getElaborationBy() {
		return elaborationBy;
	}

	public void setElaborationBy(User elaborationBy) {
		this.elaborationBy = elaborationBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDON", nullable = false)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMODIFIEDON", nullable = false)
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	public ElaborationStatus getStatus() {
		return status;
	}

	public void setStatus(ElaborationStatus status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ELABORATIONS_FILES", 
		joinColumns = { 
			@JoinColumn(name = "ELABORATIONID")}, 
			inverseJoinColumns = {@JoinColumn(name = "FILEID") 
		})
	public List<AppFile> getFiles() {
		return files;
	}

	public void setFiles(List<AppFile> files) {
		this.files = files;
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
		Elaboration other = (Elaboration) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void initNulls(){
		if(files==null){
			files = new LinkedList<AppFile>();
		}
	}
}
