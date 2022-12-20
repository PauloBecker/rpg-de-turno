package rpgturnos;

import java.io.Serializable;
import java.util.ArrayList;

public class Agent implements Serializable {
	private String name;
	private int hp, maxHp, lvl, exp, maxExp, mp, maxMp, ultMp;
	private Attack attack, ultimate;
	private ArrayList<Effect> effectsList;

	public Agent() {}

	public Agent(	String name,
					int hp, int maxHp, int lvl, int exp, int maxExp, int mp, int maxMp, int ultMp,
					Attack attack, Attack ultimate,
					ArrayList<Effect> effectsList) {
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
		this.effectsList = effectsList;
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
	public ArrayList<Effect> getEffectsList () { return effectsList; }

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
	public void setEffectsList (ArrayList<Effect> effectsList) { this.effectsList = effectsList; }

	public void addEffect(Effect effect) {
		effectsList.add(effect);
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
					"\"effects\": [" + arrayListToString(effectsList) + "]" +
				"}";
	}
}