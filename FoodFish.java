package Assignment7_5;

import java.util.Scanner;

class Food extends Thread{
	@Override
	public void run() {
		System.out.println("FOOD");
	}
}

class Fish extends Thread{
	@Override
	public void run() {
		System.out.println("FISH");
	}
}

class For extends Thread{
	@Override
	public void run() {
		System.out.println("FOOD FOR FISH");
	}
}

class Number extends Thread{
	int num;
	public Number(int num) {
		this.num = num;
	}
	@Override
	public void run() {
		System.out.println(num);
	}
}

public class FoodFish {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Thread t = null;
		int num = scanInt(sc, "Enter number: ");
		
		for (int i = 1; i <= num; i++) {
			if (i % 2 == 0) {
				t = new Food();
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (i % 3 ==0) {
				t = new Fish();
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (i % 5 == 0) {
				t = new For();
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				t = new Number(i);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		sc.close();
		
	}
	
	static int scanInt(Scanner sc, String prompt) {
		int num = 0;
		boolean pass = false;
		while (!pass) {
			System.out.print(prompt);
			try {
				num = sc.nextInt();
				pass = true;
			} catch(Exception e){
				System.out.println("Input must be a number.");
			}
			if (sc.hasNextLine()) sc.nextLine();
		}
		return num;
	}
}