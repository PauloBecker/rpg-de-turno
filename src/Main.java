package rpgturnos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	Scanner kbd = new Scanner(System.in);
	static ArrayList<Being> seres = new ArrayList<Being>();

	public static void main(String[] args) {
		seres.add(new Character1());
		System.out.println(seres.get(0));
	}
}
