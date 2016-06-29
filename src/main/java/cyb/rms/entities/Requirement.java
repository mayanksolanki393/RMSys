/*package cyb.rms.entities;

public class Requirement {
	private long id;//PK ID
	private String title;//not_null , length=255	TITLE
	private String description;//length=4096	DESCRIPTION
	
	private String shortTitle	;//	length=255	SHORTTITLE	--	--	--	--
	private Set<User> creator		;//FK , not_null	--	--	REQUIREMENT_CREATOR	ManyToMany	USER
	private	Set<User> contributor	;//	FK	--	--	REQUIREMENT_CONTIBUTOR	ManyToMany	USER
	private	Date createdOn	;//	not_null	CREATEDON	--	--	--	--
			private		lastModifiedOn	Date;//	not_null	LASTMODIFIEDON	--	--	--	--
			private		type	TYPE ;//(enum)	not_null	TYPE	--	--	--	--
			private	constraints	Set<TBD>;//	TBD	TBD	TBD	--	TBD	TBD
			private	//links	TBD	TBD	TBD	TBD	--	TBD	TBD
			private	elaboration	List<elaboration>;//	TBD	TBD	TBD	--	TBD	TBD
			private	elaboratedBy	List<User>;//	FK	--	--	REQUIREMENT_ELABORATOR	ManyToMany	USER
			private	parent	Description;//	FK	--	PARENTID	--	OneToOne	REQUIREMENT
			private	priority	PRIORITY (enum)	not_null	PRIORITY	--	--	--	--
			private	status	REQUIREMENT_STATUS (enum)	not_null	STATUS	--	--	--	--
			private files	SET<File>	FK	--	--	REQUIREMENT_FILE	OneToMany	FILE

}




*/