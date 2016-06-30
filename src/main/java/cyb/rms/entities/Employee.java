package cyb.rms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
//@table is nessary to create better tables
@Table(name="EMPLOYEES")
//this query is used by the generic dao in list() function naming convention = [CLASSNAME].list
@NamedQuery(name="Employee.list",query="Select e from Employee e")
//not implementing serializable may cause errors in some application server
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -263582897195219698L;
	private long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private double salary;
	
	
	//must implement four constructors
	//one default constructor
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	//one based on id
	public Employee(long id) {
		super();
		this.id = id;
	}
	
	//one with every thing except id
	public Employee(String firstName, String lastName, Date dateOfBirth,
			double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
	}
	
	//one with every thing
	public Employee(long id, String firstName, String lastName,
			Date dateOfBirth, double salary) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
	}
	
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
	
	@Column(name="FIRSTNAME",length=20,nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LASTNAME",length=20,nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATEOFBIRTH",length=20,nullable=false)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name="SALARY")
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	//Must override equals and hash code
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", salary=" + salary + "]";
	}
}
