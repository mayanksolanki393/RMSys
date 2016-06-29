package cyb.rms.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cyb.rms.enums.EnumProvider.Role;
@Entity
//@table is nessary to create better tables
@Table(name="USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855984087920682493L;
	private long id;//PK
	private String username;//notnull length=255	USERNAME	
	private String password;//notnull length=255	PASSWORD
	private String email;//notnull length=255	EMAIL
	private Role role;// (enum)	not_null	ROLE	
	private String designation;	//length=255	DESIGNATION	
	private String technology;//		length=255	TECHNOLOGY	--
	private Set<Project> projects;//FK
	

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
	@Column(name="USERNAME",length=255,nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="PASSWORD",length=255,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="EMAIL",length=255,nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="ROLE",nullable=false)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name="DESIGNATION",length=255)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="TECHNOLOGY",length=255)
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	 @ManyToMany(cascade=CascadeType.ALL)  
	    @JoinTable(name="USER_PROJECT",  
	    joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},  
	    inverseJoinColumns={@JoinColumn(name="project_id", referencedColumnName="id")})  
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	//Join Table :USERS_PROJECTS TYPE manyTomany
	
	
	
}




