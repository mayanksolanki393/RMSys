package cyb.rms.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cyb.rms.enums.EnumProvider.File_Status;

@Entity
@Table(name="PROJECTFILES")
public class ProjFile {
	
	private long id;//pk
	private String filename;//not_null, length=255	FILENAME
	private Date addedOn ;//not_null	ADDEDON	
	private File_Status status;//not_null	STATUS
	private User addedBy; //not_null	ADDEDBY	USERID	-	ManyToOne	USER
	
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
	
	@Column(name="STATUS",nullable=false)
	public File_Status getStatus() {
		return status;
	}
	public void setStatus(File_Status status) {
		this.status = status;
	}
	
	@JoinColumn(name="USERID")
	@ManyToOne(cascade=CascadeType.ALL)
	public User getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
	
	
	

}



