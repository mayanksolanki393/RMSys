package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {

	@JsonProperty("NEW_FEATURE")NEW_FEATURE,
	@JsonProperty("ENHANCEMENT")ENHANCEMENT,
	@JsonProperty("BUG_ISSUE")BUG_ISSUE,
	@JsonProperty("TASK")TASK,
	@JsonProperty("CUST_REQ")CUST_REQ,
	@JsonProperty("UNKNOWN")UNKNOWN;

}
