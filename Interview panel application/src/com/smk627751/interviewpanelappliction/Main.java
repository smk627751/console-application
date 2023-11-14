package com.smk627751.interviewpanelappliction;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class applicant{
	String name;
	String qualification;
	
	applicant(String name, String qualification)
	{
		this.name = name;
		this.qualification = qualification;
	}
	public String toString()
	{
		return this.name;
	}
}
public class Main {
	
	static Queue<applicant> applicants = new LinkedList();
	static Scanner sc = new Scanner(System.in);
	static Timer timer = new Timer();
	public static void startInterview()
	{
		System.out.println("Interview Started");
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if(!applicants.isEmpty())
				{
					System.out.println("Interview completed for " + applicants.peek());
					applicants.poll();
				}
			}
		};
		if(!applicants.isEmpty())
		{
			timer.schedule(task, 5000,5000);
		}
	}
	public static void main(String[] args) {
		int choice;
		do {
			System.out.println("+===============================+");
			System.out.println("|\s"+"1.Add an applicant"+"\t\t|");
			System.out.println("|\s"+"2.view waiting applicants"+"\t|");
			System.out.println("|\s"+"3.Start the Interview"+"\t\t|");
			System.out.println("+===============================+");
			System.out.println();
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					System.out.println("Enter the name:");
					String name = sc.next();
					System.out.println("Enter the qualication:");
					String qualification = sc.next();
					applicants.add(new applicant(name,qualification));
					break;
				}
				
				case 2 :{
					System.out.println(applicants);
					break;
				}
				case 3:{
					startInterview();
					break;
				}
				case 4:{
					timer.cancel();
					System.out.println("Interview ended..");
					break;
				}
			}
		}while(choice != 0);
	}

}
