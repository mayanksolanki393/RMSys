package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnumProvider {

	public enum Role
	{
	  ADMIN, MANAGER, USER 
	}
	
	public enum Project_Status
	{
	  ACTIVE, COMPLETED, DELETED 
	}
	
	public enum Elaboration_Status 
	{
	  ACTIVE, DELETED

	}
	
	
	public enum File_Status
	{
	   ACTIVE, DELETED;

	}
	
	public enum Type
	{
    	@JsonProperty("NEW FEATURE")NEW_FEATURE, ENHANCEMENT, BUG_ISSUE, TASK, CUST_REQ, UNKNOWN

	}
	
	/**
	 * 
	 * @author saurabhpa
	 * 
	 *Internal Project Constraints: Technical, Technology, Budget, Resource, etc.
	 *Internal Corporate Constraints: Financial, Marketing, Export, etc.
	 *External Constraints: Logistics, Environment, Laws and Regulations, etc.
	 */
	
	public enum Constraints
	{
		
		TECHNICAL_CONSTRAINTS,ECONOMICAL_CONSTRAINTS,LEGAL_CONSTRAINTS,OPERATIONAL_CONSTRAINTS,SCHEDULING_CONSTRAINTS,
		INTERNAL_CORPORATE_CONSTRAINTS,INTERNAL_PROJECT_CONSTRAINTS,EXTERNAL_CONSTRAINTS;
	
		
	
	}
	
	public enum Priority 
	{
		BLOCKER, CRITICAL, MAJOR, MINOR, TRIVIAL, TBD;
		
		//TBD -- To Be Decided
	}
	
	public enum Requirement_Status {
		
		OPEN, ASSIGNED, IMPLEMENTATION, DEV_TESTING, SUSPENDED,
		QA_TESTING, REOPEN, CLOSED, DELETED;

	}
}
