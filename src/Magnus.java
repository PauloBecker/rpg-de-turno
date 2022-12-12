
public class Magnus extends Character {

	public void atk(int ataque) {
		switch(ataque) {
		case 1: //Ataque x
			System.out.println("Testando ataque 1");			

		case 2: //Ataque y
			System.out.println("Testando ataque 2");
			
		case 3: 
			System.out.println("Testando ataque 3");			

		case 4:
			System.out.println("Testando ataque 4");		
						
}
	}
		public void sup(int ataqueEspecial) {
			switch(ataqueEspecial) {
				case 1: //Ataque especial x
			System.out.println("Testando ataque especial 1");
			
				case 2: //Ataque especial y
					System.out.println("Testando ataque especial 2");
					
				case 3:
					System.out.println("Testando ataque especial 3");
					
				case 4:
					System.out.println("Testando ataque especial 4");
			}
		}
		
		public void stat(int info) {
			switch(info) {
			case 1: //Habilidades
				System.out.println("Inserindo informações x sobre y coisa do Magnus");
				
			case 2: //
				System.out.println("Inserindo informações x sobre y coisa do Magnus");
				
			case 3: //
				System.out.println("Inserindo informações x sobre y coisa do Magnus");
			}
		}
}
