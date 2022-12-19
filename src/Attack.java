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

	public String getName () { return name; }
	public int getDamage () { return damage; }
	public Effect getEffect () { return effect; }

	public void setName (String name) { this.name = name; }
	public void setDamage (int damage) { this.damage = damage; }
	public void setEffect (Effect effect) { this.effect = effect; }

	public String toString() {
		return "{" +
					"\"name\": " + "\"" + name + "\", " +
					"\"damage\": " + damage + ", " +
					"\"effect\": " + effect.toString() +
				"}";
	}
} 
