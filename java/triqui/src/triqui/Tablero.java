package triqui;


public class Tablero{
	
	public char[][] tablero;
	
	@SuppressWarnings("unused")
	
	public Tablero()// para crear nuevos y bonitos tableros
	{
		tablero = new char[3][3];
		
		for (char[] cs : tablero) 
		{
			for (char cs2 : cs) 
			{
				cs2 = ' ';
			}
		}
	}
	
	public Tablero(Tablero tab) // para copiar tableros
	{
		tablero = new char[3][3];
		
		for (int a =0 ; a < 3 ; a ++) 
		{
			for (int b = 0; b < 3 ; b++ ) 
			{
				tablero[a][b] = tab.tablero[a][b];
			}
		}
	}
	
	public void show() {
		int i = 1;
		System.out.println();
		System.out.println(" |1|2|3|");
		for (char[] cs : tablero) {
			System.out.print(i+"|");
			i++;
			for (char cs2 : cs) {
				System.out.print(cs2 + "|");
			}

			System.out.println();
		}
		System.out.println();
	}
	
	public int espVac() {
		int total = 0;
		Tablero tab = this;
		for (int b = 0 ; b < 9; b++)
		{
			char espacio = ' ';
			char comp = tab.tablero[b/3][b%3];
			
			int icomp = comp + 0;
			//int iespacio = espacio +0;
			//System.out.print("'"+icomp+"'" + 0+"'");
			
			if ( icomp == 0/*iespacio*/ )
			{
				//System.out.println("hola");
				total +=1;
			}
			//System.out.println("fin");
		}
		
		
		return total;
	}
	
	
	
	int heuristica()
	{
		return 0;
	}
}
