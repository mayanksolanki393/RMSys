package cyb.rms.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Priority {
	
	@JsonProperty("BLOCKER")BLOCKER,
	@JsonProperty("CRITICAL")CRITICAL,
	@JsonProperty("MAJOR")MAJOR,
	@JsonProperty("MINOR")MINOR,
	@JsonProperty("TRIVIAL")TRIVIAL,
	@JsonProperty("TBD")TBD;
	
	
	//TBD -- To Be Decided

}
