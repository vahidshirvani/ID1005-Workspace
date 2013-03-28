// FullQueueException.java

class FullQueueException extends IllegalStateException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FullQueueException ()
	{
		super ();
	}

	FullQueueException (String message)
	{
		super (message);
	}
}