package org.oracle.cloud.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserList
{
private static ConcurrentHashMap<String, UserInfo> userDetails=null;
private static boolean initStatus=false;
public  void init()
{
	if (initStatus)
	{
		return;
	}
	if (userDetails==null)
	{
		userDetails=new ConcurrentHashMap<String, UserInfo>();
	}
	initStatus=true;
}

public static UserInfo getUser(String env)
{
	UserInfo userInfo=null;
	try 
	{
		userInfo=userDetails.get(env);
	} 
	catch (Exception e)
	{
		userInfo=null;
		throw new RuntimeException("No user in this Env");
	}
	return userInfo;
}


public  void putUser(String env,UserInfo userInfo)
{
	
		init();
	
	if (!userDetails.containsKey(env))
	{
		userDetails.put(env, userInfo);
	}	
}
}
