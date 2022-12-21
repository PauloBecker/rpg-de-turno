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
			System.out.println("game saved in \"" + file + "\"");

		} catch (IOException e) {
			System.out.println("could not open \"" file + "\"!");
			// e.printStackTrace();
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
			System.out.println("\"" + file + "\" not found!");
			// e.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("\"" + file + "\" not a valid save file!");
			//c.printStackTrace();
		}
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
					game.useItem();
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
			load("./new_game.txt");
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
