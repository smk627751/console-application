package com.smk627751.chatbotapplication;

import com.smk627751.chatbotapplication.view.View;

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
