package com.daytwo.loopingstatements;

public class Break {
	
	public static void main(String[] args) {

		for(int i=1; i<=100; i++)
		{
			if(i==75)
			{
				break;
			}
			
			System.out.println(i);
		}
	}

}
