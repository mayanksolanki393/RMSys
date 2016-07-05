package cyb.rms.rest.view;

public class UserView {
	public static interface Minimal{}
	public static interface WithoutProject extends Minimal{}
	public static interface Detailed extends WithoutProject{}	
}
