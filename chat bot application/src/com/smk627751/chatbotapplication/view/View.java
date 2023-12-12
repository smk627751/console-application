package com.smk627751.chatbotapplication.view;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.smk627751.chatbotapplication.dao.Repository;
import com.smk627751.chatbotapplication.viewmodel.ViewModel;

public class View {
	private ViewModel viewModel;
	public View()
	{
		this.viewModel = new ViewModel(this);
	}
	public void onPrint(String s)
	{
		System.out.println(s);
	}
	public void init()
	{
		viewModel.setPage("Language");
		
		Map<String,String> map = viewModel.getMenu(viewModel.getPage());
		viewModel.getBackStack().push(viewModel.getPage());
		char choice;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				choice = sc.next().charAt(0);
				map = viewModel.navigate(choice,map);
			} catch (NullPointerException e) {
				System.out.println("Invalid choice");
			}
		}while(sc.hasNext());
	}
}
