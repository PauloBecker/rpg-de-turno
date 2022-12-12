package rpgturnos;

public abstract class Character implements Being {
	public String name, eff;
	public int hp, lvl, dmg, def;

	public void atk() {}
	public void sup() {}
	public void stat() {}
}
