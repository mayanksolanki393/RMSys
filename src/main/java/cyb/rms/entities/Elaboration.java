package cyb.rms.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cyb.rms.enums.EnumProvider.Elaboration_Status;

public class Elaboration {
	private long id;
	private String title;
	private String elaboration;
	private User elaborationBy;
	private Date createdOn; 
	private Date lastModifiedOn;
	private Elaboration_Status status; 
	//private Set<File> file//fk  	ELABORATION_FILE	OneToMany	FILE
	
	@Column(name="ID")
	public long getId()
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	
	@Column(name="TITLE",length=4096,nullable=false)
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	@Column(name="TITLE",length=255,nullable=false)
	public String getElaboration() 
	{
		return elaboration;
	}
	public void setElaboration(String elaboration) 
	{
		this.elaboration = elaboration;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public User getElaborationBy()
	{
		return elaborationBy;
	}
	public void setElaborationBy(User elaborationBy)
	{
		this.elaborationBy = elaborationBy;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATEDON",nullable=false)
	public Date getCreatedOn()
	{
		return createdOn;
	}
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="LASTMODIFIEDON",nullable=false)
	public Date getLastModifiedOn() 
	{
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Date lastModifiedOn)
	{
		this.lastModifiedOn = lastModifiedOn;
	}
	
	@Column(name="STATUS",nullable=false)
	public Elaboration_Status getStatus()
	{
		return status;
	}
	public void setStatus(Elaboration_Status status)
	{
		this.status = status;
	}
	
	

}




