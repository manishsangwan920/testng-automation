package exception;


public class NoSuitableDriverFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoSuitableDriverFoundException(String message) {
		super(message);
	}
	
	public NoSuitableDriverFoundException(){
		this("");
	}

}
