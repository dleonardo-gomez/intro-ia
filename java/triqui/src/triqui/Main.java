package triqui;

public class Main {
	
	static Tablero tablero;
	
	
	public static void main(String[] args) {
		
		tablero = new Tablero();
		boolean partidoGanado = false;
		int numPartidas = 0;
		
		Arbol arb = new Arbol(tablero); // se crea el arbol con el primer tablero (vacio)
		
		System.out.println( " antes de podar \n tam: "+ arb.recorrer(arb.nivelesBajar+1));
		

		arb.podarArbol(arb.nivelesBajar+1,arb.nivelesBajar+1);
		
		System.out.println( " despues de podar \n tam: "+ arb.recorrer(arb.nivelesBajar+1));
		System.out.println("hijos de arb : "+ arb.hijos.length);
		
	}

}
