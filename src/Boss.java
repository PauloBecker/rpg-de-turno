package rpgturnos;

public abstract class Boss extends Enemy {
	public String name, eff;
	public int hp, lvl, dmg, def;

	public void atk() {}
	public void sup() {}
	public void stat() {}
}