package cyb.rms.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cyb.rms.enums.RmsEnums.Role;

@Entity
@Table(name="USERS")
@NamedQuery(name="User.list",query="Select u from User u")
public class User implements Serializable {
	private static final long serialVersionUID = -1855984087920682493L;
	
	//state members
	private long id;
	private String username;	
	private String password;
	private String email;
	private Role role;	
	private String designation;	
	private String technology;
	private Set<Project> projects;

	
	//constructors
	public User(){
		initNulls();
	}
	
	public User(String username, String password, String email, Role role,String designation, String technology, Set<Project> projects) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.designation = designation;
		this.technology = technology;
		this.projects = projects;
		initNulls();
	}

	public User(long id, String username, String password, String email,Role role, String designation, String technology,Set<Project> projects) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.designation = designation;
		this.technology = technology;
		this.projects = projects;
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
	
	@Column(name="USERNAME",length=255,nullable=false,unique=true)
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
	
	@Enumerated(EnumType.STRING)
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
	
	@ManyToMany(mappedBy="users")
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	public void initNulls(){
		if(projects == null){
			this.projects = new HashSet<Project>();
		}
	}
	
	//equals and hashcode
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}




