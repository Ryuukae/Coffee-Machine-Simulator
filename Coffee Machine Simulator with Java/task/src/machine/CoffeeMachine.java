package machine;

import java.util.Scanner;

//-! For one espresso, the coffee machine needs 250 ml of water_requirement and 16 g of coffee bean_requirement. It costs $4.
//-! For a latte, the coffee machine needs 350 ml of water_requirement, 75 ml of milk_requirement, and 20 g of coffee bean_requirement. It costs $7.
//-! For a cappuccino, the coffee machine needs 200 ml of water_requirement, 100 ml of milk_requirement, and 12 g of coffee bean_requirement. It costs $6.
public class CoffeeMachine {
	static Coffee espresso = new Coffee(250, 0, 16, 4);
	static Coffee latte = new Coffee(350, 75, 20, 7);
	static Coffee cappuccino = new Coffee(200, 100, 12, 6);

	private int water_level;
	private int milk_level;
	private int bean_count;
	private int cup_count;
	private int money;

	public static void main(String[] args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine();
		// coffeeMachine.printSteps();
//		coffeeMachine.setInventory();
//		coffeeMachine.checkSuppliesForRequest();

		coffeeMachine.printSupplies();
		coffeeMachine.displayActions();
		coffeeMachine.takeAction(coffeeMachine.takeActionChoice());
		coffeeMachine.printSupplies();


	}

	public CoffeeMachine() {
		this.water_level = 400;
		this.milk_level = 540;
		this.bean_count = 120;
		this.cup_count = 9;
		this.money = 550;
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

	private void setInventory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Write how many ml of water you want to add:");
		this.water_level += scanner.nextInt();
		System.out.println("Write how many ml of milk you want to add:");
		this.milk_level += scanner.nextInt();
		System.out.println("Write how many grams of coffee beans  you want to add:");
		this.bean_count += scanner.nextInt();
		System.out.println("Write how many disposable cups you want to add:");
		this.cup_count += scanner.nextInt();
		System.out.println("\n");
	}



	private void takeAction(String action) {
		switch (action) {
			case "buy":
				displayBuyOptions();
				brewCoffee(takeBuyOptions());
				break;
			case "fill":
				setInventory();
				break;
			case "take":
				System.out.println("I gave you $" + this.money+"\n");
				this.money = 0;
				break;
			default:
				break;
		}
	}

	private void displayActions() {
		System.out.println("Write action (buy, fill, take):");
	}

	private String takeActionChoice() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	private void displayBuyOptions() {
		System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
	}

	private int takeBuyOptions() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	private void brewCoffee(int choice) {
		switch (choice) {
			case 1:
				this.makeCoffee(espresso);
				break;
			case 2:
				this.makeCoffee(latte);
				break;
			case 3:
				this.makeCoffee(cappuccino);
				break;
		}
	}

	private void printSupplies() {
		System.out.println("The coffee machine has:");
		System.out.println(this.water_level + " ml of water");
		System.out.println(this.milk_level + " ml of milk");
		System.out.println(this.bean_count + " g of coffee beans");
		System.out.println(this.cup_count + " disposable cups");
		System.out.println("$" + this.money + " of money\n");
	}

	private void makeCoffee(Coffee coffee) {
		if (this.water_level >= coffee.getWater() && this.milk_level >= coffee.getMilk() && this.bean_count >=
				                                                                                    coffee.getBeans() && this.cup_count > 0) {
			this.water_level -= coffee.getWater();
			this.milk_level -= coffee.getMilk();
			this.bean_count -= coffee.getBeans();
			this.cup_count--;
			this.money += coffee.getCost();
			System.out.println("\n");
		} else {

		}
	}

	private void checkSuppliesForRequest() {
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
		System.out.println("Grinding coffee bean_requirement");
		System.out.println("Boiling water_requirement");
		System.out.println("Mixing boiled water_requirement with crushed coffee bean_requirement");
		System.out.println("Pouring coffee into the cup");
		System.out.println("Pouring some milk_requirement into the cup");
		System.out.println("Coffee is ready!");
	}
}

class Coffee {
	private final int water_requirement;
	private final int milk_requirement;
	private final int bean_requirement;
	private final int coffee_cost;

	public Coffee(int water_requirement, int milk_requirement, int bean_requirement, int coffee_cost) {
		this.water_requirement = water_requirement;
		this.milk_requirement = milk_requirement;
		this.bean_requirement = bean_requirement;
		this.coffee_cost = coffee_cost;
	}

	public int getWater() {
		return water_requirement;
	}

	public int getMilk() {
		return milk_requirement;
	}

	public int getBeans() {
		return bean_requirement;
	}

	public int getCost() {
		return coffee_cost;
	}
}
