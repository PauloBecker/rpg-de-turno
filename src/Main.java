package rpgturnos;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
	static Scanner kbd = new Scanner(System.in);
	static ArrayList<String> lore = new ArrayList<String>();
	static ArrayList<Agent> players = new ArrayList<Agent>();
	static ArrayList<Agent> enemies = new ArrayList<Agent>();
	static ArrayList<Agent> bosses = new ArrayList<Agent>();

	static Agent player, enemy;
	static Attack attack;

	static int enemiesDefeated, bossesDefeated;
	static boolean gameOver, enemyDefeated;

	public static boolean randomize(int percentage) {
		return Math.random() * 100.0 < percentage;
	}

	public static void checkHealth() {
		enemyDefeated = enemy.hp <= 0;
		gameOver = player.hp <= 0;

		if (enemyDefeated) {
			checkLevel();
			enemiesDefeated++;
		}
	}

	public static void checkLevel() {
		Attack oldAttack;
		Attack oldUltimate;

		player.setExp(player.getExp() + enemy.getExp());

		while (player.getExp() > player.getMaxExp()) {
			player.setExp(player.getExp() - player.getMaxExp());

			player.setMaxHp(player.getMaxHp() * 2);
			player.setMaxExp(player.getMaxExp() * 2);
			player.setMaxMp(player.getMaxMp() * 2);
			player.setUltMp(player.getUltMp() * 2);

			oldAttack = player.getAttack();
			oldUltimate = player.getUltimate();
			player.setAttack(new Attack(oldAttack.getName(), oldAttack.getDamage() * 2, oldAttack.getEffect()));
			player.setUltimate(new Attack(oldUltimate.getName(), oldUltimate.getDamage() * 2, oldUltimate.getEffect()));
		}
	}

	public static void printPlayers() {

	}

	public static void printPlayers() {
		System.out.println("players:");

		for (int i = 0; i < players.size(); i++) {
			System.out.println("\t" + i + " - " + players.get(i).toString());
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
			System.out.println("\t2 - inspect");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					break;

				case 1:
					break;

				case 2:
					System.out.println(player.toString());
					break;

				default:
					System.out.println("invalid option!");
					break;
			}

		} while (option != 0);
	}

	public static void setup() {
		players.add(new Agent(
			"fighter",
			150, 150, 0, 0, 10, 100, 100, 20,
			new Attack("punch", 20, new Effect("none", 0)),
			new Attack("slap", 40, new Effect("dizzyness", 3)),
			new ArrayList<Effect>()
		));

		players.add(new Agent(
			"mage",
			100, 100, 0, 0, 10, 150, 150, 30,
			new Attack("fire spell", 10, new Effect("ignite", 3)),
			new Attack("ice spell", 60, new Effect("freeze", 6)),
			new ArrayList<Effect>()
		));

		players.add(new Agent(
			"archer",
			125, 125, 0, 0, 10, 125, 125, 25,
			new Attack("piercing shot", 20, new Effect("none", 3)),
			new Attack("triple arrow", 30, new Effect("poison", 0)),
			new ArrayList<Effect>()
		));

		enemiesDefeated = 0;
		bossesDefeated = 0;
		gameOver = false;
	}

	public static void encounter() {
		int option;

		do {
			System.out.println(player.toString());
			System.out.println(enemy.toString());

			System.out.println("options:");
			System.out.println("\t0 - run away");
			System.out.println("\t1 - attack");
			System.out.println("\t2 - defend");
			System.out.println("\t3 - ultimate");
			System.out.println("\t4 - use item");
			System.out.println("\t5 - inspect");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					System.out.println("you ran away!");
					break;

				case 1:
					attack = player.getAttack();
					enemy.hp -= attack.damage;
					if (!attack.effect.name.equals("none"))
						enemy.effectsList.add(attack.effect);

					// tweak for enemy ultimate
					attack = enemy.getAttack();
					player.hp -= attack.damage;
					if (!attack.effect.name.equals("none"))
						player.effectsList.add(attack.effect);

					break;

				case 2:
					attack = enemy.getAttack();
					player.hp -= attack.damage / 4;
					if (!attack.effect.name.equals("none"))
						player.effectsList.add(attack.effect);

					break;

				case 3:
					attack = player.getUltimate();
					enemy.hp -= attack.damage;
					if (!attack.effect.name.equals("none"))
						enemy.effectsList.add(attack.effect);

					// tweak for enemy ultimate
					attack = enemy.getAttack();
					player.hp -= attack.damage;
					if (!attack.effect.name.equals("none"))
						player.effectsList.add(attack.effect);
					break;

				case 4:
					break;

				case 5:
					System.out.println(enemy.toString());
					break;

				default:
					System.out.println("invalid option!");
					break;
			}

		checkHealth();

		} while (option != 0 && !enemyDefeated && !gameOver);
	}

	public static void main(String[] args) {
		while (true) {
			setup();
			choosePlayer();

			while (bossesDefeated < 4) {
				enemiesDefeated = 0;

				while (enemiesDefeated < 4) {
					if (gameOver) break;
					idle();

					System.out.println("you found an enemy!");

					enemy = new Agent(
						"rat",
						100, 100, 3, 25, 0, 0, 0, 0,
						new Attack("bite", 10, new Effect("dizzyness", 3)),
						new Attack("tail whip", 20, new Effect("none", 0)),
						new ArrayList<Effect>()
					);

					encounter();

					if (enemyDefeated) System.out.println("enemy defeated!");
				}

				if (gameOver) break;
				idle();

				System.out.println("boss battle!");
				/*
				bossFight();
				enemy = new Agent(
									"boss",
									100, 100, 3, 25, 0, 0, 0, 0,
									new Attack("stomp", 100, new Effect("none", 3)),
									new Attack("roar", 200, new Effect("fear", 3)),
									new ArrayList<Effect>()
								);
				*/
			}

			if (gameOver) System.out.println("game over!");
			else System.out.println("victory!");
		}
	}
}
