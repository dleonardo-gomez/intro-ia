package triqui;

public class Main {

	public static void main(String[] args) {

		Arbol arb = new Arbol();
		
		System.out.println( "tam: "+ arb.recorrer(arb.nivelesBajar+1));
		
		
	}

}
