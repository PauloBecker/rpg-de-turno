package rpgturnos;

import java.io.Serializable;

public class Effect implements Serializable {
	public String name;
	public int duration;

	public Effect() {}

	public Effect(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName () { return name; }
	public int getDuration () { return duration; }

	public void setName (String name) { this.name = name; }
	public void setDuration (int duration) { this.duration = duration; }

	public String toString() {
		return "{" +
					"\"name\": " + "\"" + name + "\", " +
					"\"duration\": " + duration +
				"}";
	}
}
