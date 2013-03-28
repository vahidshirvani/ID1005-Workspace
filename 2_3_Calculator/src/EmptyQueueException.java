// EmptyQueueException.java

class EmptyQueueException extends IllegalStateException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmptyQueueException ()
	{
		super ();
	}

	EmptyQueueException (String message)
	{
		super (message);
	}
}