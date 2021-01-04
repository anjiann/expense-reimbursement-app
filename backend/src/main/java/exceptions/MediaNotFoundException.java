package exceptions;

public class MediaNotFoundException extends AbstractHttpException {


	private static int numberOfOccurances =0;
	
	
	public MediaNotFoundException() {
		super("That Media Was Not Found, Please Try Again", 404);
		numberOfOccurances++;
	}
}
