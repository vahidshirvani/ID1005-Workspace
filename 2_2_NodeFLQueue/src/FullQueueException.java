// FullQueueException.java

@SuppressWarnings("serial")
class FullQueueException extends IllegalStateException
{
	FullQueueException ()
	{
		super ();
	}

	FullQueueException (String message)
	{
		super (message);
	}
}