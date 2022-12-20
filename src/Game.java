package rpgturnos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game implements Serializable {
	private transient static Scanner kbd = new Scanner(System.in);
	private transient static Random rand = new Random();
	private ArrayList<String> story;
	private ArrayList<Agent> players;
	private ArrayList<Agent> enemies;
	private ArrayList<Agent> bosses;
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Effect> effects = new ArrayList<Effect>();
	private Agent player, enemy;
	private int enemiesDefeated, bossesDefeated, storyCounter;
	private boolean gameOver, enemyDefeated, bossFight;

	public Game() {}

	public Game(	ArrayList<String> story,
					ArrayList<Agent> players, ArrayList<Agent> enemies, ArrayList<Agent> bosses,
					ArrayList<Item> items, ArrayList<Effect> effects,
					Agent player, Agent enemy,
					int enemiesDefeated, int bossesDefeated, int storyCounter,
					boolean gameOver, boolean enemyDefeated, boolean bossFight) {
		this.story = story;
		this.players = players;
		this.enemies = enemies;
		this.bosses = bosses;
		this.items = items;
		this.effects = effects;
		this.player = player;
		this.enemy = enemy;
		this.enemiesDefeated = enemiesDefeated;
		this.bossesDefeated = bossesDefeated;
		this.storyCounter = storyCounter;
		this.gameOver = gameOver;
		this.enemyDefeated = enemyDefeated;
		this.bossFight = bossFight;
	}

	public ArrayList<String> getStory() { return story; }
	public ArrayList<Agent> getPlayers() { return players; }
	public ArrayList<Agent> getEnemies() { return enemies; }
	public ArrayList<Agent> getBosses() { return bosses; }
	public ArrayList<Item> getItems() { return items; }
	public ArrayList<Effect> getEffects() { return effects; }
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
	public void setItems(ArrayList<Item> items) { this.items = items; }
	public void setEffects(ArrayList<Effect> effects) { this.effects = effects; }
	public void setPlayer(Agent player) { this.player = player; }
	public void setEnemy(Agent enemy) { this.enemy = enemy; }
	public void setEnemiesDefeated(int enemiesDefeated) { this.enemiesDefeated = enemiesDefeated; }
	public void setBossesDefeated(int bossesDefeated) { this.bossesDefeated = bossesDefeated; }
	public void setGameOver(boolean gameOver) { this.gameOver = gameOver; }
	public void setEnemyDefeated(boolean enemyDefeated) { this.enemyDefeated = enemyDefeated; }
	public void setBossFight(boolean bossFight) { this.bossFight = bossFight; }

	public void addText(String text) {
		story.add(text);
	}

	public void addPlayer(Agent agent) {
		players.add(agent);
	}

	public void addEnemy(Agent agent) {
		enemies.add(agent);
	}

	public void addBoss(Agent agent) {
		bosses.add(agent);
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
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
					"\"items\": " + "[" + arrayListToString(items) + "], " +
					"\"effects\": " + "[" + arrayListToString(effects) + "], " +
					"\"player\": " + player.toString() + ", " +
					"\"enemy\": " + enemy.toString() + ", " +
					"\"enemiesDefeated\": " + enemiesDefeated + ", " +
					"\"bossesDefeated\": " + bossesDefeated + ", " +
					"\"gameOver\": " + "\"" + gameOver + "\", " +
					"\"enemyDefeated\": " + "\"" + enemyDefeated + "\", " +
					"\"bossFight\": " + "\"" + bossFight + "\"" + 
				"}";
	}

	public int randomInt(int bound) {
		return rand.nextInt(bound);
	}

	public int randomChance() {
		return randomInt(100);
	}

	public void checkEffects(Agent agent) {
		Effect effect;
		Attack attack;

		for (int i = 0; i < agent.getEffects().size(); i++) {
			effect = agent.getEffects().get(i);

			if (effect.getDuration() == 0)
				agent.getEffects().remove(i);

			else {
				switch (effect.getName()) {
					case "poison":
					case "ignite":
						agent.setHp(agent.getHp() - 10);
						break;

					case "shock":
						agent.setHp(agent.getHp() - 100);
						break;
					
					case "health":
						agent.setHp(agent.getHp() + 100);
						break;

					case "mana":
						agent.setMp(agent.getMp() + 100);
						break;
					
					case "strength":
						attack = agent.getAttack();
						attack.setDamage(attack.getDamage() + 50);
						agent.setAttack(attack);
						break;

					default:
						System.out.println("invalid effect!");
						agent.getEffects().remove(i);
						break;
				}
				effect.setDuration(effect.getDuration() - 1);
			}
		}
	}

	public void applyEffect(Effect effect, Agent agent) {
		Effect found;
		int rand;

		rand = randomChance();

		if (0 <= rand && rand < 25 && !effect.getName().equals("none")) {
			
			System.out.println(	"it gave out the " + effect.getName() +
								" effect for " + effect.getDuration() + " turns!");
			found = null;
			for (int i = 0; i < agent.getEffects().size(); i++)
				if (agent.getEffects().get(i).getName().equals(effect.getName()))
					found = agent.getEffects().get(i);

			if (found != null) found.setDuration(effect.getDuration());
			else agent.addEffect(effect);
		}
	}

	public void checkHealth() {
		enemyDefeated = enemy.getHp() <= 0;
		gameOver = player.getHp() <= 0;

		if (enemyDefeated) {
			player.checkLevel(enemy.getExp());
			if (enemy.getItems().size() > 0)
				for (int i = 0; i < enemy.getItems().size(); i++)
					player.addItem(enemy.getItems().get(i));
			enemiesDefeated += 1;
		}
	}

	public void showStory() {
		if (story.size() > 0 && storyCounter < story.size()) {
			System.out.println(story.get(storyCounter));
			storyCounter++;
		}
	}

	public void showPlayers() {
		System.out.println("players:");
		for (int i = 0; i < players.size(); i++)
			System.out.println("\t" + i + " - " + players.get(i).toString());
	}

	public void choosePlayer() {
		int option;

		while (true) {
			showPlayers();
			System.out.print("option: ");
			option = kbd.nextInt();

			if (option < 0 || option >= players.size())
				System.out.println("invalid option!");
			else break;
		}
		player = players.get(option);
	}

	public void showItems(Agent agent) {
		System.out.println("items:");
		for (int i = 0; i < agent.getItems().size(); i++)
			System.out.println("\t" + i + " - " + agent.getItems().get(i).toString());
	}

	public void useItem() {
		boolean itemExists = false;
		int option;

		if (player.getItems().size() == 0) {
			System.out.println("no items to choose from!");
		} else {
			showItems(player);
			System.out.print("option: ");
			option = kbd.nextInt();

			if (option < 0 || option >= player.getItems().size()) {
				System.out.println("invalid option!");
			} else {
				applyEffect(player.getItems().get(option).getEffect(), player);
				player.getItems().remove(option);
			}
		}
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
				System.out.println(	attacker.getName() +
									" used ultimate " + attack.getName() + "!");
	
				if (defending) {
					System.out.println(target.getName() + " defended!");
					target.setHp(target.getHp() - attack.getDamage() / 4);
				} else target.setHp(target.getHp() - attack.getDamage());

				applyEffect(attack.getEffect(), target);
			}
		} else {
			attack = attacker.getAttack();
			System.out.println(	attacker.getName() +
								" used " + attack.getName() + "!");

			if (defending) {
				System.out.println(target.getName() + " defended!");
				target.setHp(target.getHp() - attack.getDamage() / 4);
			} else target.setHp(target.getHp() - attack.getDamage());

			applyEffect(attack.getEffect(), target);
		}
		checkHealth();
	}

	public Item randomItem() {
		return items.get(randomInt(items.size()));
	}

	public Agent randomEnemy() {
		int rand;
		Agent chosen;

		chosen = enemies.get(randomInt(enemies.size()));

		rand = randomChance();
		if (0 <= rand && rand < 25) chosen.addItem(randomItem());
		rand = randomChance();
		if (0 <= rand && rand < 25) chosen.addItem(randomItem());

		return chosen;
	}

	public Agent randomBoss() {
		int rand;
		Agent chosen;

		chosen = bosses.get(randomInt(bosses.size()));

		chosen.addItem(randomItem());
		chosen.addItem(randomItem());
		rand = randomChance();
		if (0 <= rand && rand < 50) chosen.addItem(randomItem());

		return chosen;
	}

	public void encounter() {
		int option, rand;
		Attack attack;

		if (bossFight) enemy = randomBoss();
		else enemy = randomEnemy();

		while (true) {
			checkEffects(player);
			checkEffects(enemy);

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
					rand = randomChance();

					if (0 <= rand && rand < 50) {
						// player attack, enemy attack
						turn(player, enemy, false, false);
						if (!enemyDefeated) turn(enemy, player, false, false);

					} else if (50 <= rand && rand < 75) {
						// player attack, enemy defense
						if (!enemyDefeated) turn(player, enemy, true, false);

					} else {
						// player attack, enemy ultimate
						turn(player, enemy, false, false);
						if (!enemyDefeated) turn(enemy, player, false, true);
					}
					break;

				case 2:
					rand = randomChance();

					if (0 <= rand && rand < 50) {
						// player defense, enemy attack
						turn(enemy, player, true, false);
					} else if (50 <= rand && rand < 75) {
						// player defense, enemy ulltimate
						turn(enemy, player, true, true);
					}	// player defense, enemy defense
					break;

				case 3:
					rand = randomChance();

					if (0 <= rand && rand < 50) {
						// player ultimate, enemy attack
						turn(player, enemy, false, true);
						if (!enemyDefeated) turn(enemy, player, false, false);

					} else if (50 <= rand && rand < 75) {
						// player ultimate, enemy defense
						turn(player, enemy, true, true);
					} else {
						// player ultimate, enemy ultimate
						turn(player, enemy, false, true);
						if (!enemyDefeated) turn(enemy, player, false, true);
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
