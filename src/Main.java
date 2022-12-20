package rpgturnos;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Scanner kbd = new Scanner(System.in);
	private static Game game;

	public static void save(String file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.println("game saved in " + file);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load(String file) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			game = (Game) in.readObject();

			in.close();
			fileIn.close();
			System.out.println("game loaded from " + file);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println(file + " not found!");
			c.printStackTrace();
		}
	}

	public static void newGame() {

		/**/

		ArrayList<String> story = new ArrayList<String>();
		ArrayList<Agent> players = new ArrayList<Agent>();
		ArrayList<Agent> enemies = new ArrayList<Agent>();
		ArrayList<Agent> bosses = new ArrayList<Agent>();
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Effect> effects = new ArrayList<Effect>();
		Agent player = new Agent();
		Agent enemy = new Agent();
		int enemiesDefeated = 0;
		int bossesDefeated = 0;
		int storyCounter = 0;
		boolean gameOver = false;
		boolean enemyDefeated = false;
		boolean bossFight = false;

		game = new Game(	story, players, enemies, bosses,
							items, effects,
							player, enemy,
							enemiesDefeated, bossesDefeated, storyCounter,
							gameOver, enemyDefeated, bossFight);

		game.addText("you arrive at the train station. There are hostile passengers aboard!");

		game.addText("it seems like they aren't calming down!");
		game.addText("there must be a machinist responsible for this human zoo...");
		game.addText("you can feel an evil presence approaching!");
		game.addText("hopefully now they see who is the train master");

		game.addPlayer(new Agent(
			"fighter",
			175, 175, 0, 0, 10, 100, 100, 20,
			new Attack("punch", 20, new Effect("none", 0)),
			new Attack("slap", 40, new Effect("shock", 3)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addPlayer(new Agent(
			"mage",
			100, 100, 0, 0, 10, 150, 150, 30,
			new Attack("fire spell", 10, new Effect("ignite", 3)),
			new Attack("ice spell", 60, new Effect("freeze", 6)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addPlayer(new Agent(
			"archer",
			125, 125, 0, 0, 10, 125, 125, 25,
			new Attack("piercing shot", 20, new Effect("none", 3)),
			new Attack("triple arrow", 30, new Effect("poison", 3)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addEnemy(new Agent(
			"passenger",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("push", 10, new Effect("none", 0)),
			new Attack("shove", 20, new Effect("stunt", 3)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addEnemy(new Agent(
			"accountant",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("pen stab", 20, new Effect("bleed", 3)),
			new Attack("suitcase slam", 40, new Effect("stunt", 3)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addEnemy(new Agent(
			"old lady",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("popcorn", 0, new Effect("heal", 1)),
			new Attack("dark magic", 40, new Effect("ignite", 6)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addBoss(new Agent(
			"super old lady",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("sucker punch", 30, new Effect("bleed", 3)),
			new Attack("dark magic", 60, new Effect("ignite", 6)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addBoss(new Agent(
			"operator lady",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("calm down", 0, new Effect("none", 0)),
			new Attack("SIT DOWN", 120, new Effect("stunt", 6)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addBoss(new Agent(
			"machinist",
			100, 100, 10, 25, 0, 0, 0, 0,
			new Attack("ALL ABOARD", 30, new Effect("stunt", 3)),
			new Attack("CHOO CHOO", 120, new Effect("stunt", 6)),
			new ArrayList<Item>(),
			new ArrayList<Effect>()
		));

		game.addItem(new Item(
			"health potion",
			new Effect("health", 1)
		));

		game.addItem(new Item(
			"mana potion",
			new Effect("mana", 1)
		));

		game.addItem(new Item(
			"strength potion",
			new Effect("strength", 1)
		));

		save("./new_game.txt");


		load("./new_game.txt");
	}

	public static void idle() {
		int option;

		while (true) {
			System.out.println("options:");
			System.out.println("\t0 - explore");
			System.out.println("\t1 - use item");
			System.out.println("\t2 - inspect self");
			System.out.println("\t3 - save game");
			System.out.println("\t4 - load game");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					break;

				case 1:
					break;

				case 2:
					System.out.println(game.getPlayer().toString());
					break;

				case 3:
					save("./save.txt");
					break;

				case 4:
					load("./save.txt");
					break;

				default:
					System.out.println("invalid option!");
					break;
			}

			if (option == 0) break;
		}
	}

	public static void main(String[] args) {
		while (true) {
			newGame();
			game.choosePlayer();
			game.showStory();

			while (game.getBossesDefeated() < 3) {
				game.setEnemiesDefeated(0);

				while (game.getEnemiesDefeated() < 3) {
					if (game.getGameOver()) break;
					idle();

					System.out.println("you found an enemy!");
					game.encounter();
					if (game.getEnemyDefeated()) {
						System.out.println("enemy defeated!");
						game.showStory();
					}
				}

				if (game.getGameOver()) break;
				idle();

				System.out.println("boss battle!");
				game.setBossFight(true);
				game.encounter();
				game.setBossFight(false);

				if (game.getEnemyDefeated()) {
						System.out.println("boss defeated!");
						game.showStory();
				}
			}

			if (game.getGameOver()) System.out.println("game over!");
			else System.out.println("victory!");
		}
	}
}
