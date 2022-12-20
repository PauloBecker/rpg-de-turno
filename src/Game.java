package rpgturnos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Game implements Serializable {
	private transient static Scanner kbd = new Scanner(System.in);
	private ArrayList<String> story;
	private  ArrayList<Agent> players;
	private ArrayList<Agent> enemies;
	private ArrayList<Agent> bosses;
	private Agent player, enemy;
	private int enemiesDefeated, bossesDefeated;
	private boolean gameOver, enemyDefeated, bossFight;

	public Game() {}

	public Game(	ArrayList<String> story,
					ArrayList<Agent> players, ArrayList<Agent> enemies, ArrayList<Agent> bosses,
					Agent player, Agent enemy,
					int enemiesDefeated, int bossesDefeated,
					boolean gameOver, boolean enemyDefeated, boolean bossFight) {
		this.story = story;
		this.players = players;
		this.enemies = enemies;
		this.bosses = bosses;
		this.player = player;
		this.enemy = enemy;
		this.enemiesDefeated = enemiesDefeated;
		this.bossesDefeated = bossesDefeated;
		this.gameOver = gameOver;
		this.enemyDefeated = enemyDefeated;
		this.bossFight = bossFight;
	}

	public ArrayList<String> getStory() { return story; }
	public ArrayList<Agent> getPlayers() { return players; }
	public ArrayList<Agent> getEnemies() { return enemies; }
	public ArrayList<Agent> getBosses() { return bosses; }
	public Agent getPlayer() { return player; }
	public Agent getEnemy() { return enemy; }
	public int getEnemiesDefeated() { return enemiesDefeated; }
	public int getBossesDefeated() { return bossesDefeated; }
	public boolean getGameOver() { return gameOver; }
	public boolean getEnemyDefeated() { return enemyDefeated; }
	public boolean getBossFight() { return bossFight; }

	public void setStory(ArrayList<String> story) { this.story = story; }
	public void setPlayers(ArrayList<Agent> players) { this.players = players; }
	public void setEnemies(ArrayList<Agent> enemies) { this.enemies = enemies; }
	public void setBosses(ArrayList<Agent> bosses) { this.bosses = bosses; }
	public void setPlayer(Agent player) { this.player = player; }
	public void setEnemy(Agent enemy) { this.enemy = enemy; }
	public void setEnemiesDefeated(int enemiesDefeated) { this.enemiesDefeated = enemiesDefeated; }
	public void setBossesDefeated(int bossesDefeated) { this.bossesDefeated = bossesDefeated; }
	public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
	public void setEnemyDefeated(boolean enemyDefeated) { this.enemyDefeated = enemyDefeated; }
	public void setBossFight(boolean bossFight) { this.bossFight = bossFight; }

	public void addPlayer(Agent agent) {
		players.add(agent);
	}

	public void addEnemy(Agent agent) {
		enemies.add(agent);
	}

	public void addBoss(Agent agent) {
		bosses.add(agent);
	}

	public String arrayListToString(ArrayList list) {
		String out = "";

		if (list.size() != 0) {
			out = "";

			for (int i = 0; i < list.size(); i++) {
				out += list.get(i).toString();

				if (i != list.size() - 1)
					out += ", ";
			}
		}

		return out;
	}

	public String toString() {
		return "{" +
					"\"story\": " + "[" + arrayListToString(story) + "], " +
					"\"players\": " + "[" + arrayListToString(players) + "], " +
					"\"bosses\": " + "[" + arrayListToString(bosses) + "], " +
					"\"player\": " + player.toString() + ", " +
					"\"enemy\": " + enemy.toString() + ", " +
					"\"enemiesDefeated\": " + enemiesDefeated + ", " +
					"\"bossesDefeated\": " + bossesDefeated + ", " +
					"\"gameOver\": " + "\"" + gameOver + "\", " +
					"\"enemyDefeated\": " + "\"" + enemyDefeated + "\", " +
					"\"bossFight\": " + "\"" + bossFight + "\"" + 
				"}";
	}

	public int randomize() {
		return (int) Math.random() * 100;
	}

	public void checkEffects(Agent agent) {
		Effect effect;

		for (int i = 0; i < agent.getEffectsList().size(); i++) {
			effect = agent.getEffectsList().get(i);

			if (effect.getDuration() == 0)
				agent.getEffectsList().remove(i);

			else {
				switch (effect.getName()) {
					case "poison":
						agent.setHp(agent.getHp() - 5);
						break;

					case "ignite":
						agent.setHp(agent.getHp() - 100);
						break;

					case "shock":
						agent.setHp(agent.getHp() - 100);
						break;

					default:
						break;
				}
				effect.setDuration(effect.getDuration() - 1);
			}
		}
	}

	public void checkHealth() {
		enemyDefeated = enemy.getHp() <= 0;
		gameOver = player.getHp() <= 0;

		if (enemyDefeated) {
			checkLevel();
			enemiesDefeated += 1;
		}
	}

	public void checkLevel() {
		Attack attack;

		player.setExp(player.getExp() + enemy.getExp());

		while (player.getExp() >= player.getMaxExp()) {
			player.setHp(player.getHp() * 2);
			player.setMaxHp(player.getMaxHp() * 2);
			player.setLvl(player.getLvl() + 1);
			player.setExp(player.getExp() - player.getMaxExp());
			player.setMaxExp(player.getMaxExp() * 2);
			player.setMp(player.getMp() * 2);
			player.setMaxMp(player.getMaxMp() * 2);
			player.setUltMp(player.getUltMp() * 2);

			attack = player.getAttack();
			attack.setDamage(attack.getDamage() * 2);
			player.setAttack(attack);

			attack = player.getUltimate();
			attack.setDamage(attack.getDamage() * 2);
			player.setUltimate(attack);

			System.out.println("player has levelled up!");
		}
	}

	public void printPlayers() {
		System.out.println("players:");
		for (int i = 0; i < players.size(); i++)
			System.out.println("\t" + i + " - " + players.get(i).toString());
	}

	public void choosePlayer() {
		int option;

		while (true) {
			printPlayers();
			System.out.print("option: ");
			option = kbd.nextInt();

			if (option < 0 || option >= players.size())
				System.out.println("invalid option!");
			else break;
		}
		player = players.get(option);
	}

	public void turn(	Agent attacker, Agent target,
								boolean defending, boolean ultimate) {
		Attack attack;

		if (ultimate) {
			if (attacker.getMp() - attacker.getUltMp() < 0)
				System.out.println("not enough mana!");

			else {
				attacker.setMp(attacker.getMp() - attacker.getUltMp());
				attack = attacker.getUltimate();

				if (defending)
					target.setHp(target.getHp() - attack.getDamage() / 4);
				else
					target.setHp(target.getHp() - attack.getDamage());

				if (!attack.getEffect().getName().equals("none"))
					target.addEffect(attack.getEffect());
			}
		} else {
			attack = attacker.getAttack();
			if (defending)
				target.setHp(target.getHp() - attack.getDamage() / 4);
			else
				target.setHp(target.getHp() - attack.getDamage());

			if (!attack.getEffect().getName().equals("none"))
				target.addEffect(attack.getEffect());
		}

		checkHealth();
	}

	public void encounter() {
		int option, rand;
		Attack attack;

		while (true) {
			System.out.println("options:");
			System.out.println("\t0 - run away");
			System.out.println("\t1 - attack");
			System.out.println("\t2 - defend");
			System.out.println("\t3 - ultimate");
			System.out.println("\t4 - use item");
			System.out.println("\t5 - inspect self");
			System.out.println("\t6 - inspect enemy");

			System.out.print("option: ");
			option = kbd.nextInt();

			switch (option) {
				case 0:
					if (bossFight)
						System.out.println("there is no escape!");
					else
						System.out.println("you ran away!");

					break;

				case 1:
					System.out.println("player attacked!");

					rand = randomize();

					if (0 <= rand && rand < 50) {
						turn(player, enemy, false, false);

						if (!enemyDefeated) {
							System.out.println("enemy attacked too!");
							turn(enemy, player, false, false);
						}
					} else if (50 <= rand && rand < 75) {
						if (!enemyDefeated) {
							System.out.println("enemy defended!");
							turn(player, enemy, true, false);
						}
					} else {
						turn(player, enemy, false, false);

						if (!enemyDefeated) {
							System.out.println("enemy used ultimate!");
							turn(enemy, player, false, true);
						}
					}

					break;

				case 2:
					System.out.println("player defended!");

					rand = randomize();

					if (0 <= rand && rand < 50) {
						System.out.println("enemy attacked!");
						turn(enemy, player, true, false);
					} else if (50 <= rand && rand < 75) {
						System.out.println("enemy defended too!");
					} else {
						System.out.println("enemy used ultimate!");
						turn(enemy, player, true, true);
					}

					break;

				case 3:
					System.out.println("player used ultimate!");
					
					rand = randomize();

					if (0 <= rand && rand < 50) {
						turn(player, enemy, false, true);

						if (!enemyDefeated) {
							turn(enemy, player, false, false);
							System.out.println("enemy attacked!");
						}
					} else if (50 <= rand && rand < 75) {
						System.out.println("enemy defended!");
						turn(player, enemy, true, true);
					} else {
						turn(player, enemy, false, true);

						if (!enemyDefeated) {
							System.out.println("enemy used ultimate too!");
							turn(enemy, player, false, true);
						}
					}

					break;

				case 4:
					break;

				case 5:
					System.out.println(player.toString());
					break;

				case 6:
					System.out.println(enemy.toString());
					break;

				default:
					System.out.println("invalid option!");
					break;
			}

			if (option == 0 && !bossFight || gameOver || enemyDefeated) break;
		}
	}
}
