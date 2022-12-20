package rpgturnos;

import java.io.Serializable;

public class Item implements Serializable {
	public String name;
	public Effect effect;

	public Item() {}

	public Item(String name, Effect effect) {
		this.name = name;
		this.effect = effect;
	}

	public String getName () { return name; }
	public Effect getEffect () { return effect; }

	public void setName (String name) { this.name = name; }
	public void setEffect (Effect effect) { this.effect = effect; }

	public String toString() {
		return "{" +
					"\"name\": " + "\"" + name + "\", " +
					"\"effect\": " + effect.toString() +
				"}";
	}
}
