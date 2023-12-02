package com.smk627751.chatbotapplication;

import java.util.Map;
import java.util.Scanner;

import com.smk627751.chatbotapplication.view.View;
import com.smk627751.chatbotapplication.viewmodel.ViewModel;

public class ChatBotapplication {
	private View view;
	ChatBotapplication()
	{
		this.view = new View();
	}
	
	public void init()
	{
		view.getRepository().setPage("Language");
		
		Map<String,String> map = view.getMenu(view.getRepository().getPage());
		view.getBackStack().push(view.getRepository().getPage());
		char choice;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				choice = sc.next().charAt(0);
				map = view.navigate(choice,map);
			} catch (NullPointerException e) {
				System.out.println("Invalid choice");
			}
		}while(sc.hasNext());
	}
	public static void main(String[] args){
		ChatBotapplication obj = new ChatBotapplication();
		obj.init();
	}

}
