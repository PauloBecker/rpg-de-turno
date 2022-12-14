package rpgturnos;

import java.util.ArrayList;

public class Agent {
	public String name, attackName, ultimateName;
	public Effect attackEff, ultimateEff;
	public ArrayList<Effect> eff;
	public int hp, maxHp, lvl, exp, maxExp, dmg, ult, def, mp, maxMp;

	public Character(	String name, String attackName,
						int maxHp, int maxExp, int maxMp,
						int dmg, int ult, int def,
						Effect attackEff, Effect ultimateEff) {
		this.name = name;
		this.attackName = attackName;
		this.maxHp = maxHp;
		this.maxExp = maxExp;
		this.maxMp = maxMp;
		this.dmg = dmg;
		this.ult = ult;
		this.def = def;

		this.attackEff = attackEff;
		this.ultimateEff = ultimateEff;

		eff = new ArrayList<Effect>();
		lvl = 0;
		exp = 0;
		hp = maxHp;
		mp = maxMp;
	}

	public Attack attack() {
		return new Attack(attackName, dmg, attackEff);
	}

	public Attack ultimate() {
		return new Attack(ultimateName, ult, ultimateEff);
	}

	public void defend() {}
	public void inspect() {}

	public void levelUp() {
		lvl += 1;
	}

	public int getHp() {
		return hp;
	}

	public int getLvl() {
		return lvl;
	}

	public int getExp() {
		return exp;
	}

	public int getDmg() {
		return dmg;
	}

	public int getDef() {
		return def;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void setDef(int def) {
		this.def = def;
	}
}
