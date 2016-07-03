package cyb.rms.rest.view;

public class RequirementView {
	public static interface Minimal{}
	public static interface Detailed extends Minimal,UserView.Minimal,FileView.Minimal,ProjectView.List,ElaborationView.Minimal{}
}
