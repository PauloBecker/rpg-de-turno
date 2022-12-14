package rpgturnos;

public class Attack {
	public String name;
	public int damage;
	public Effect effect;

	public Attack(String name, int damage, Effect effect) {
		this.name = name;
		this.damage = damage;
		this.effect = effect;
	}
}
