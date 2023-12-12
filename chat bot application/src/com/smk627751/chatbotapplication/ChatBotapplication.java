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
	
	public static void main(String[] args){
		ChatBotapplication obj = new ChatBotapplication();
		obj.view.init();
	}

}
