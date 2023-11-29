package com.smk627751.interviewpanelappliction.dto;

public class Applicant{
	private String name;
	private String qualification;
	
	public Applicant(String name, String qualification)
	{
		this.name = name;
		this.qualification = qualification;
	}
	public String toString()
	{
		return this.name;
	}
}
