package triqui;


public class Tablero{

	public char[][] tablero;
	
	public int valor; //  valor en el que se guarda el valor de un tablero 

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


	public int heuristica()
	{
		
		char[][] t = this.tablero;
		int nx=0, no=0, cont =0;

		//Caso 1
		for(int i=0;i<3;i++){
			if(t[0][i] == 'x'){
				nx++;
			}
			else if(t[0][i] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 2
		nx=0;
		no=0;

		for(int i=0;i<3;i++){
			if(t[1][i] == 'x'){
				nx++;
			}
			else if(t[1][i] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 3
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[2][i] == 'x'){
				nx++;
			}
			else if(t[2][i] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 4
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][0] == 'x'){
				nx++;
			}
			else if(t[i][0] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 5
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][1] == 'x'){
				nx++;
			}
			else if(t[i][1] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 6
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][2] == 'x'){
				nx++;
			}
			else if(t[i][2] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 7
		nx=0;
		no=0;
		//int nnx=0,nno=0;
		for(int i=0;i<3;i++){
			if(t[i][i] == 'x'){
				nx++;
			}
			else if(t[i][i] == 'o'){
				no++;
			}

		}
		cont += comp(nx,no);

		//Caso 8
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[2-i][i] == 'x'){
				nx++;
			}
			else if(t[2-i][i] == 'o'){
				no++;
			}
		}
		cont += comp(nx,no);

		return cont;
	}

	static public int comp(int nx, int no){
		/*
		if     (no==3 && nx ==0){
		
			return -50;    
		}
		else if(no==2 && nx ==0){
			return -5;    
		}
		else if(no==1 && nx ==0){
			return -1;
		}
		else if(no >=1 && nx !=0){
			return 0;
		}
		else if(nx ==1 && no ==0){
			return 1;
		}
		else if(nx ==2 && no ==0){
			return 5;
		}
		else if(nx ==3 && no ==0){
			return 50;
		}
		//*/
		
		
		return no - nx ; // ya que debe buscar maximisar o y minimisar x 
	}

	
	
	// se modifica el tablero en la posicion espvac, agregandole el simbolo en turn (x u o)
	public void modificarTablero(int espvac, char turno) 
	{
		
		int numvac = 0;
		for (int b = 0 ; b < 9; b++)
		{
			int idato = this.tablero[b/3][b%3] + 0;
			if ( idato == 0)
			{
				if(numvac == espvac)
				{
					this.tablero[b/3][b%3] = turno;
					b = 9;
					
				}
				numvac +=1;
			}
		}
		
		this.valor = this.heuristica(); // se le da valor al tablero despues de modificarlo
	}
	
	
}

