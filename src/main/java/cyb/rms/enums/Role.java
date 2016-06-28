package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
	
	@JsonProperty("ADMIN")ADMIN,
	@JsonProperty("MANAGER")MANAGER,
	@JsonProperty("USER")USER;

}
