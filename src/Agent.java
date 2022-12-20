package rpgturnos;

import java.io.Serializable;
import java.util.ArrayList;

public class Agent implements Serializable {
	private String name;
	private int hp, maxHp, lvl, exp, maxExp, mp, maxMp, ultMp;
	private Attack attack, ultimate;
	private ArrayList<Item> items;
	private ArrayList<Effect> effects;

	public Agent() {}

	public Agent(	String name,
					int hp, int maxHp, int lvl, int exp, int maxExp, int mp, int maxMp, int ultMp,
					Attack attack, Attack ultimate,
					ArrayList<Item> items,
					ArrayList<Effect> effects) {
		this.name = name;
		this.hp = hp;
		this.maxHp = maxHp;
		this.lvl = lvl;
		this.exp = exp;
		this.maxExp = maxExp;
		this.mp = mp;
		this.maxMp = maxMp;
		this.ultMp = ultMp;
		this.attack = attack;
		this.ultimate = ultimate;
		this.items = items;
		this.effects = effects;
	}

	public String getName () { return name; }
	public int getHp () { return hp; }
	public int getMaxHp () { return maxHp; }
	public int getLvl () { return lvl; }
	public int getExp () { return exp; }
	public int getMaxExp () { return maxExp; }
	public int getMp () { return mp; }
	public int getMaxMp () { return maxMp; }
	public int getUltMp () { return ultMp; }
	public Attack getAttack () { return attack; }
	public Attack getUltimate () { return ultimate; }
	public ArrayList<Item> getItems () { return items; }
	public ArrayList<Effect> getEffects () { return effects; }

	public void setName (String name) { this.name = name; }
	public void setHp (int hp) { this.hp = hp; }
	public void setMaxHp (int maxHp) { this.maxHp = maxHp; }
	public void setLvl (int lvl) { this.lvl = lvl; }
	public void setExp (int exp) { this.exp = exp; }
	public void setMaxExp (int maxExp) { this.maxExp = maxExp; }
	public void setMp (int mp) { this.mp = mp; }
	public void setMaxMp (int maxMp) { this.maxMp = maxMp; }
	public void setUltMp (int ultMp) { this.ultMp = ultMp; }
	public void setAttack (Attack attack) { this.attack = attack; }
	public void setUltimate (Attack ultimate) { this.ultimate = ultimate; }
	public void setitems (ArrayList<Item> items) { this.items = items; }
	public void setEffects (ArrayList<Effect> effects) { this.effects = effects; }

	public void addItem(Item item) {
		items.add(item);
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
	}

	public void checkLevel(int gainedExp) {
		exp += gainedExp;

		while (exp >= maxExp) {
			hp *= 2;
			maxHp *= 2;
			lvl++;
			exp -= maxExp;
			maxExp *= 2;
			mp *= 2;
			maxMp *= 2;
			ultMp *= 2;
			attack.setDamage(attack.getDamage() * 2);
			ultimate.setDamage(ultimate.getDamage() * 2);
		}
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
					"\"name\": " + "\"" + name + "\", " +
					"\"hp\": " + hp + ", " +
					"\"maxHp\": " + maxHp + ", " +
					"\"lvl\": " + lvl + ", " +
					"\"exp\": " + exp + ", " +
					"\"maxExp\": " + maxExp + ", " +
					"\"mp\": " + mp + ", " +
					"\"maxMp\": " + maxMp + ", " +
					"\"ultMp\": " + ultMp + ", " +
					"\"attack\": " + attack.toString() + ", " +
					"\"ultimate\": " + ultimate.toString() + ", " +
					"\"items\": [" + arrayListToString(items) + "]" + ", " +
					"\"effects\": [" + arrayListToString(effects) + "]" +
				"}";
	}
}