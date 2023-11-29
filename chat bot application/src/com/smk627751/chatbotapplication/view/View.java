package com.smk627751.chatbotapplication.view;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.smk627751.chatbotapplication.dao.Repository;

public class View {
	private Stack<String> backStack = new Stack<>();
	public Stack<String> getBackStack() {
		return backStack;
	}
	public void setBackStack(Stack<String> backStack) {
		this.backStack = backStack;
	}
	private Repository repository;
	
	public View()
	{
		this.repository = Repository.getInstance();
	}
	public Map<String,String> getMenu(String page)
	{
		Map<String,String> m = repository.getData().get(page);
		System.out.println("+=======================+");
		if(m != null)
		for(Map.Entry<String, String> e : m.entrySet())
		{
			System.out.print("|\s"+e.getKey()+"\s|\t"+e.getValue());
			if(e.getValue().length() <= 7)
			{
				System.out.println("\t\t|");
			}
			else
			{
				System.out.println("\t|");
			}
		}
		System.out.println("+=======================+");
		if(backStack.size() > 1)
		{
			System.out.println("Press - for back");
		}
		
		return m;
	}
	
	public Map<String,String> navigate(char choice, Map<String,String> map)
	{
		if(map == null) return getMenu(backStack.peek());
		
		switch(choice)
		{
			case '-':
			{
				if(backStack.size() > 1)
				{
					backStack.pop();
					repository.setPage(backStack.peek());;
				}
				else {
					System.out.println("go next");
				}
				break;
			}
			default:{
				repository.setPage(map.get(choice+""));
				if(backStack.peek().equals("Place order") || backStack.peek().equals("ஆர்டர் வைக்கவும்"))
				{
					Map<String,Integer> orders = repository.getOrders();
					orders.put(repository.getPage(),orders.getOrDefault(repository.getPage(), 0)+1);
					repository.setOrders(orders);
				}
				else if(repository.getPage().equals("View cart") || repository.getPage().equals("கார்ட்டை பார்க்கவும்"))
				{
					for(Map.Entry<String, Integer> order : repository.getOrders().entrySet())
					{
						System.out.println(order.getKey()+" x"+order.getValue());
					}
				}
				else if(repository.getPage().equals("Payment") || repository.getPage().equals("கட்டணம்"))
				{
					Long total = (long) 0;
					System.out.println("+=======================+");
					for(Map.Entry<String, Integer> order : repository.getOrders().entrySet())
					{
						String tab = "\t\s\s";
						if(order.getKey().length() > 7)
						{
							tab = "\s\s";
						}
						Long amount = (repository.getPrice().get(order.getKey())*order.getValue());
						System.out.println("|\t"+order.getKey()+tab+ amount +"\t|");
						total += amount;
					}
					System.out.println("|-----------------------|");
					System.out.println("|\ttotal:\t\s\s"+total+"\t|");
					System.out.println("+=======================+");
					Scanner sc = new Scanner(System.in);
					System.out.println("Press 0 to pay..");
					if(sc.next().charAt(0)== '0')
					{
						System.out.println("Thank you for visiting....");
						System.exit(0);
					}
				}
				else
				{
					backStack.push(repository.getPage());
				}
				break;
			}
		}
		if(!backStack.isEmpty())
		{
			map = getMenu(backStack.peek());
		}
		return map;
	}
	public Repository getRepository() {
		return repository;
	}
}
