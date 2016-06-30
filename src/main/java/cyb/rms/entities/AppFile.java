package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cyb.rms.enums.RmsEnums.FileType;
import cyb.rms.enums.RmsEnums.FileStatus;

@Entity
@Table(name="FILES")
@NamedQuery(name="AppFile.list",query="Select af from AppFile af WHERE af.status NOT IN('DELETED')")
public class AppFile implements Serializable{
	private static final long serialVersionUID = 637694787336335869L;
	
	//state members
	private long id;
	private String filename;
	private Date addedOn ;	
	private FileStatus status;
	private User addedBy;
	private FileType fileType;
	
	//constructors
	public AppFile() {}
	
	public AppFile(String filename, Date addedOn, FileStatus status,User addedBy, FileType fileType) {
		super();
		this.filename = filename;
		this.addedOn = addedOn;
		this.status = status;
		this.addedBy = addedBy;
		this.fileType = fileType;
	}
	
	public AppFile(long id, String filename, Date addedOn, FileStatus status,User addedBy, FileType fileType) {
		super();
		this.id = id;
		this.filename = filename;
		this.addedOn = addedOn;
		this.status = status;
		this.addedBy = addedBy;
		this.fileType = fileType;
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
	
	@Column(name="FILENAME",length=255,nullable=false)
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Column(name="ADDEDON",nullable=false)
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS",nullable=false)
	public FileStatus getStatus() {
		return status;
	}
	public void setStatus(FileStatus status) {
		this.status = status;
	}
	
	@JoinColumn(name="UPLOADEDBY")
	@ManyToOne(cascade=CascadeType.ALL)
	public User getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="FILETYPE")
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
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
		AppFile other = (AppFile) obj;
		if (id != other.id)
			return false;
		return true;
	}
}