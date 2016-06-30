package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RmsEnums {

	public enum Role
	{

		@JsonProperty("ROLE_ADMIN")ROLE_ADMIN,
		@JsonProperty("ROLE_MANAGER")ROLE_MANAGER,
		@JsonProperty("ROLE_USER")ROLE_USER;
	}
	
	public enum ProjectStatus
	{

		@JsonProperty("ACTIVE")ACTIVE,
		@JsonProperty("DELETED")DELETED,
		@JsonProperty("COMPLETED")COMPLETED;
	}
	
	public enum ElaborationStatus 
	{

		@JsonProperty("ACTIVE")ACTIVE,
		@JsonProperty("DELETED")DELETED;

	}
	
	
	public enum FileStatus
	{

		@JsonProperty("ACTIVE")ACTIVE,
		@JsonProperty("DELETED")DELETED;

	}
	
	public enum Type
	{
		@JsonProperty("NEW_FEATURE")NEW_FEATURE,
		@JsonProperty("ENHANCEMENT")ENHANCEMENT,
		@JsonProperty("BUG_ISSUE")BUG_ISSUE,
		@JsonProperty("TASK")TASK,
		@JsonProperty("CUST_REQ")CUST_REQ,
		@JsonProperty("UNKNOWN")UNKNOWN;

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
		
		@JsonProperty("TECHNICAL CONSTRAINTS")TECHNICAL_CONSTRAINTS,
		@JsonProperty("ECONOMICAL CONSTRAINTS")ECONOMICAL_CONSTRAINTS,
		@JsonProperty("LEGAL CONSTRAINTS")LEGAL_CONSTRAINTS,
		@JsonProperty("OPERATIONAL CONSTRAINTS")OPERATIONAL_CONSTRAINTS,
		@JsonProperty("SCHEDULING CONSTRAINTS")SCHEDULING_CONSTRAINTS,
		@JsonProperty("INTERNAL CORPORATE CONSTRAINTS")INTERNAL_CORPORATE_CONSTRAINTS,
		@JsonProperty("INTERNAL PROJECT CONSTRAINTS")INTERNAL_PROJECT_CONSTRAINTS,
		@JsonProperty("EXTERNAL CONSTRAINTS")EXTERNAL_CONSTRAINTS;
	
		
	
	}
	
	public enum Priority 
	{
		@JsonProperty("BLOCKER")BLOCKER,
		@JsonProperty("CRITICAL")CRITICAL,
		@JsonProperty("MAJOR")MAJOR,
		@JsonProperty("MINOR")MINOR,
		@JsonProperty("TRIVIAL")TRIVIAL,
		@JsonProperty("TBD")TBD;
	}
	
	public enum RequirementStatus {
		
		@JsonProperty("OPEN")OPEN,
		@JsonProperty("ASSIGNED")ASSIGNED,
		@JsonProperty("IMPLEMENTATION")IMPLEMENTATION,
		@JsonProperty("DEV_TESTING")DEV_TESTING,
		@JsonProperty("SUSPENDED")SUSPENDED,
		@JsonProperty("QA_TESTING")QA_TESTING,
		@JsonProperty("REOPEN")REOPEN,
		@JsonProperty("CLOSED")CLOSED,
		@JsonProperty("DELETED")DELETED;

	}
	
	public enum FileType {
		@JsonProperty("ELABORATION FILE") ELABORATION_FILE,
		@JsonProperty("REQUIREMENT FILE") REQUIREMENT_FILE
	}
}
