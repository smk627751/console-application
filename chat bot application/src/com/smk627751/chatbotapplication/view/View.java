package com.smk627751.chatbotapplication.view;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.smk627751.chatbotapplication.dao.Repository;

public class View {
	private static Stack<String> backStack = new Stack<>();
	private static Repository repository;
	
	public View()
	{
		setRepository(new Repository());
	}
	public Map<String,String> getMenu(String page)
	{
		Map<String,String> m = getRepository().getData().get(page);
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
		if(getBackStack().size() > 1)
		{
			System.out.println("Press - for back");
		}
		
		return m;
	}
	
	public Map<String,String> navigate(char choice, Map<String,String> map)
	{
		if(map == null) return getMenu(getBackStack().peek());
		
		switch(choice)
		{
			case '-':
			{
				if(getBackStack().size() > 1)
				{
					getBackStack().pop();
					getRepository().setPage(getBackStack().peek());;
				}
				else {
					System.out.println("go next");
				}
				break;
			}
			default:{
				getRepository().setPage(map.get(choice+""));
				if(getBackStack().peek().equals("Place order") || getBackStack().peek().equals("ஆர்டர் வைக்கவும்"))
				{
					Map<String,Integer> orders = getRepository().getOrders();
					orders.put(getRepository().getPage(),orders.getOrDefault(getRepository().getPage(), 0)+1);
					getRepository().setOrders(orders);
				}
				else if(getRepository().getPage().equals("View cart") || getRepository().getPage().equals("கார்ட்டை பார்க்கவும்"))
				{
					for(Map.Entry<String, Integer> order : getRepository().getOrders().entrySet())
					{
						System.out.println(order.getKey()+" x"+order.getValue());
					}
				}
				else if(getRepository().getPage().equals("Payment") || getRepository().getPage().equals("கட்டணம்"))
				{
					Long total = (long) 0;
					System.out.println("+=======================+");
					for(Map.Entry<String, Integer> order : getRepository().getOrders().entrySet())
					{
						String tab = "\t\s\s";
						if(order.getKey().length() > 7)
						{
							tab = "\s\s";
						}
						Long amount = (getRepository().getPrice().get(order.getKey())*order.getValue());
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
					getBackStack().push(getRepository().getPage());
				}
				break;
			}
		}
		if(!getBackStack().isEmpty())
		{
			map = getMenu(getBackStack().peek());
		}
		return map;
	}
	public Repository getRepository() {
		return repository;
	}
	public void setRepository(Repository repository) {
		View.repository = repository;
	}
	public Stack<String> getBackStack() {
		return backStack;
	}
	public void setBackStack(Stack<String> backStack) {
		View.backStack = backStack;
	}
}
