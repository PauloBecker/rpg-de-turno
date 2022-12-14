package rpgturnos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner kbd = new Scanner(System.in);

	static ArrayList<Agent> players = new ArrayList<Agent>();
	static ArrayList<Agent> enemies = new ArrayList<Agent>();
	static ArrayList<Agent> bosses = new ArrayList<Agent>();

	static Agent player;

	static int encounters = 0;

	public static void printPlayers() {
		System.out.println("players:");

		for (int i = 0; i < players.size(); i++) {
			System.out.println("\t" + i + " - " + players.get(i).name);
		}
	}

	public static void choosePlayer() {
		int option;
		do {
			printPlayers();
			System.out.print("option: ");
			option = kbd.nextInt();

			if (option < 0 || option >= players.size())
				System.out.println("invalid option!");

		} while (option < 0 || option >= players.size());

		player = players.get(option);
	}

	public static void idle() {
		int option;
		do {
			System.out.println("options:");
			System.out.println("\t0 - explore");
			System.out.println("\t1 - use item");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					System.out.println("you found an enemy!");
					break;

				case 1:
					break;

				default:
					System.out.println("invalid option!");
					break;
			}
		} while (option != 0);
	}

	public static void encounter(Agent agent) {
		int option;
		do {
			System.out.println("options:");
			System.out.println("\t0 - run away");
			System.out.println("\t1 - attack");
			System.out.println("\t2 - defend");
			System.out.println("\t3 - use item");
			System.out.println("\t4 - ultimate");
			System.out.println("\t5 - inspect");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					System.out.println("you ran away!");
					break;

				case 1:
					break;

				case 2:
					break;

				case 3:
					break;

				case 4:
					break;

				case 5:
					break;

				default:
					System.out.println("invalid option!");
					break;
			}
		} while (option != 0);
	}

	public static void main(String[] args) {
		players.add(new Agent("fighter0", "punch", 100, 100, 20, 10, 40, 0, null, new Effect("dizzyness", 3)));
		players.add(new Agent("fighter1", "punch", 100, 100, 20, 10, 40, 0, null, new Effect("dizzyness", 3)));
		players.add(new Agent("fighter2", "punch", 100, 100, 20, 10, 40, 0, null, new Effect("dizzyness", 3)));
		players.add(new Agent("fighter3", "punch", 100, 100, 20, 10, 40, 0, null, new Effect("dizzyness", 3)));
		choosePlayer();

		for (int i = 0; i < 3; i++) {
			while (encounters < 3) {
				idle();
				System.out.println("enemy " + encounters);
				encounter(new Agent("fighter", "punch", 100, 100, 20, 10, 40, 0, null, new Effect("dizzyness", 3)));
				encounters += 1;
			}

			System.out.println("boss " + (i + 1));
			encounters = 0;
		}
	}
}
