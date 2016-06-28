package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Project_status {
	
	@JsonProperty("ACTIVE")ACTIVE,
	@JsonProperty("DELETED")DELETED,
	@JsonProperty("COMPLETED")COMPLETED;

}
