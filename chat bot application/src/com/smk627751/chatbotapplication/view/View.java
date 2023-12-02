package com.smk627751.chatbotapplication.view;

import java.util.Map;
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
	public Repository getRepository() {
		return repository;
	}
	public Map<String,String> getMenu(String page)
	{
		return viewModel.getMenu(page);
	}
	public Stack<String> getBackStack()
	{
		return viewModel.getBackStack();
	}
	public Map<String,String> navigate(char choice, Map<String,String> map)
	{
		return viewModel.navigate(choice, map);
	}
}
