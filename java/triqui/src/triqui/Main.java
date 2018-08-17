package triqui;

public class Main {
	
	static Tablero tablero;
	
	
	public static void main(String[] args) {
		
		tablero = new Tablero();
		boolean partidoGanado = false;
		int numPartidas = 0;
		
		Arbol arb = new Arbol(tablero); // se crea el arbol con el primer tablero (vacio)
		
		System.out.println( "tam: "+ arb.recorrer(arb.nivelesBajar+1));
		
		/*
		while (numPartidas < 9 && !partidoGanado)
		{
			
		}
		*/
		
	}

}
