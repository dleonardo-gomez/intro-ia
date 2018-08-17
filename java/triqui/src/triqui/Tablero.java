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


	public int heuristica()
	{

		char[][] t = this.tablero;
		int nx=0, no=0, cont =0;

		//Caso 1
		for(int i=0;i<3;i++){
			if(t[0][i] == 'X'){
				nx++;
			}
			else if(t[0][i] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 2
		nx=0;
		no=0;

		for(int i=0;i<3;i++){
			if(t[1][i] == 'X'){
				nx++;
			}
			else if(t[1][i] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 3
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[2][i] == 'X'){
				nx++;
			}
			else if(t[2][i] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 4
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][0] == 'X'){
				nx++;
			}
			else if(t[i][0] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 5
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][1] == 'X'){
				nx++;
			}
			else if(t[i][1] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 6
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[i][2] == 'X'){
				nx++;
			}
			else if(t[i][2] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		//Caso 7
		nx=0;
		no=0;
		int nnx=0,nno=0;
		for(int i=0;i<3;i++){
			if(t[i][i] == 'X'){
				nx++;
			}
			else if(t[i][i] == 'O'){
				no++;
			}

		}
		cont += comp(nx,no);

		//Caso 8
		nx=0;
		no=0;
		for(int i=0;i<3;i++){
			if(t[2-i][i] == 'X'){
				nx++;
			}
			else if(t[2-i][i] == 'O'){
				no++;
			}
		}
		cont += comp(nx,no);

		return cont;
	}

	static public int comp(int nx, int no){
		if(no==3 && nx ==0){
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

		return 0;
	}
}

