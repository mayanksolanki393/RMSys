package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Requirement_status {
	
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
