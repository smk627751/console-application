package com.smk627751.chatbotapplication.view;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.smk627751.chatbotapplication.dao.Repository;
import com.smk627751.chatbotapplication.viewmodel.ViewModel;

public class View {
	private Repository repository;
	private ViewModel viewModel;
	public View()
	{
		this.viewModel = new ViewModel(this);
		this.repository = viewModel.getRepository();
	}
	public void onPrint(String s)
	{
		System.out.println(s);
	}
	public void init()
	{
		repository.setPage("Language");
		
		Map<String,String> map = viewModel.getMenu(repository.getPage());
		viewModel.getBackStack().push(repository.getPage());
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
