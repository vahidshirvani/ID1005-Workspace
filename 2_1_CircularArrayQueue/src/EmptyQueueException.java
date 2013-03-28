// EmptyQueueException.java

class EmptyQueueException extends IllegalStateException
{
	EmptyQueueException ()
	{
		super ();
	}

	EmptyQueueException (String message)
	{
		super (message);
	}
}