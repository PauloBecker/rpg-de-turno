package rpgturnos;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

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
		/*
		ArrayList<String> story = new ArrayList<String>();
		ArrayList<Agent> players = new ArrayList<Agent>();
		ArrayList<Agent> enemies = new ArrayList<Agent>();
		ArrayList<Agent> bosses = new ArrayList<Agent>();
		Agent player = new Agent();
		Agent enemy = new Agent();
		int enemiesDefeated = 0;
		int bossesDefeated = 0;
		boolean gameOver = false;
		boolean enemyDefeated = false;
		boolean bossFight = false;

		game = new Game(	story, players, enemies, bosses,
							player, enemy, enemiesDefeated, bossesDefeated,
							gameOver, enemyDefeated, bossFight);

		game.addPlayer(new Agent(
			"fighter",
			200, 150, 0, 0, 10, 100, 100, 20,
			new Attack("punch", 20, new Effect("none", 0)),
			new Attack("slap", 40, new Effect("shock", 3)),
			new ArrayList<Effect>()
		));

		game.addPlayer(new Agent(
			"mage",
			100, 100, 0, 0, 10, 150, 150, 30,
			new Attack("fire spell", 10, new Effect("ignite", 3)),
			new Attack("ice spell", 60, new Effect("freeze", 6)),
			new ArrayList<Effect>()
		));

		game.addPlayer(new Agent(
			"archer",
			150, 125, 0, 0, 10, 125, 125, 25,
			new Attack("piercing shot", 20, new Effect("none", 3)),
			new Attack("triple arrow", 30, new Effect("poison", 0)),
			new ArrayList<Effect>()
		));
		*/

		load("./new_game.txt");
		game.choosePlayer();
	}

	public static void idle() {
		int option;

		do {
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
		} while (option != 0);
	}

	public static void main(String[] args) {
		Agent enemy;

		while (true) {
			newGame();
			while (game.getBossesDefeated() < 4) {
				game.setEnemiesDefeated(0);

				while (game.getEnemiesDefeated() < 4) {
					if (game.getGameOver()) break;
					idle();

					System.out.println("you found an enemy!");
					game.setEnemy(new Agent(
						"rat",
						100, 100, 10, 25, 0, 0, 0, 0,
						new Attack("bite", 10, new Effect("stunt", 3)),
						new Attack("tail whip", 20, new Effect("none", 0)),
						new ArrayList<Effect>()
					));
					game.encounter();
					if (game.getEnemyDefeated()) System.out.println("enemy defeated!");

				}

				if (game.getGameOver()) break;
				idle();

				System.out.println("boss battle!");
				game.setEnemy(new Agent(
					"boss",
					100, 100, 100, 25, 0, 0, 0, 0,
					new Attack("stomp", 100, new Effect("none", 3)),
					new Attack("roar", 200, new Effect("fear", 3)),
					new ArrayList<Effect>()
				));
				game.setBossFight(true);
				game.encounter();
				game.setBossFight(false);
			}

			if (game.getGameOver()) System.out.println("game over!");
			else System.out.println("victory!");
		}
	}
}
