package com.oracle.cloud.exceptions;

public class ElementNotFoundException extends RuntimeException
{
    /**
     * <b>Description</b> If element not found throw Exception
     * @param exception
     */
	public ElementNotFoundException(String exception)
	{
		super(exception);
	}
	
}
