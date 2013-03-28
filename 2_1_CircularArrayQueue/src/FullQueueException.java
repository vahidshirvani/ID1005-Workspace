// FullQueueException.java

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