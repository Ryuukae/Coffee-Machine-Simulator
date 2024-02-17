package machine;

import java.util.Scanner;

//-! The coffee made on this coffee machine contains 200 ml of water, 50 ml of milk, and 15 g of coffee beans.
public class CoffeeMachine {
	private int water_level;
	private int milk_level;
	private int bean_count;

	public static void main(String[] args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine();
     // coffeeMachine.printSteps();
		coffeeMachine.takeInventory();
		coffeeMachine.checkSuppliesForRequest();

	}

	public CoffeeMachine() {
	}
	private void calculateIngredients() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Write how many cups of coffee you will need:");
		int cups = scanner.nextInt();
		int water = 200 * cups;
		int milk = 50 * cups;
		int beans = 15 * cups;
		System.out.println("For " + cups + " cups of coffee you will need:");
		System.out.println(water + " ml of water");
		System.out.println(milk + " ml of milk");
		System.out.println(beans + " g of coffee beans");
	}

	private void takeInventory(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Write how many ml of water the coffee machine has:");
		this.water_level = scanner.nextInt();
		System.out.println("Write how many ml of milk the coffee machine has:");
		this.milk_level = scanner.nextInt();
		System.out.println("Write how many grams of coffee beans the coffee machine has:");
		this.bean_count = scanner.nextInt();
	}

	private void checkSuppliesForRequest(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Write how many cups of coffee you will need:");
		int cups = scanner.nextInt();
		int minCups = Math.min(this.water_level / 200, Math.min(this.milk_level / 50, this.bean_count / 15));
		if (cups == minCups) {
			System.out.println("Yes, I can make that amount of coffee");
		} else if (cups < minCups) {
			System.out.println("Yes, I can make that amount of coffee (and even " + (minCups - cups) + " more than that)");
		} else {
			System.out.println("No, I can make only " + minCups + " cup(s) of coffee");
		}
	}

	private void printSteps() {
		System.out.println("Starting to make a coffee");
		System.out.println("Grinding coffee beans");
		System.out.println("Boiling water");
		System.out.println("Mixing boiled water with crushed coffee beans");
		System.out.println("Pouring coffee into the cup");
		System.out.println("Pouring some milk into the cup");
		System.out.println("Coffee is ready!");
	}
}
