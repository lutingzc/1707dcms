package com.lutingting.commom;

public class CmsAssert {
	
	public static void AssertTrue(boolean express,String msg){
		if(!express)
			throw new CmcException(msg);
	}

	public static void AssertTrueHtml(boolean express, String msg) {
		// TODO Auto-generated method stub
		if(!express)
			throw new CmcExceptionHtml(msg);
	}
}
