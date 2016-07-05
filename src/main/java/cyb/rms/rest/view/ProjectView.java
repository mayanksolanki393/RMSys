package cyb.rms.rest.view;

import org.springframework.beans.factory.annotation.Required;

public class ProjectView {
	public static interface List extends UserView.Minimal{}
	public static interface ProjectDetailsView extends List,RequirementView.Minimal{}
}
