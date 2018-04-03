package com.oracle.cloud.exceptions;

@SuppressWarnings("serial")
public class ActionNotSupportedException extends RuntimeException
{
/**
 * <b>Description</b> Throws Action not supported Exception
 */
	public ActionNotSupportedException(String exception)
	{
		super(exception);
	}
}
