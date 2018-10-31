package com.caps.dev.exception;

public class NewPasswordMismatchException extends RuntimeException {
	String msg="New Password Mismatch, Process Terminated ! ";
	
	@Override
	public String toString() {
		return this.msg;
	}
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}
