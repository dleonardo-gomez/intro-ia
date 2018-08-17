package triqui;


public class Arbol {
	
	Tablero tab;
	Arbol hijos[];
	int nHijos;
	int valor; // el valor es igual al valor propio mas el valor del mejor de los hijos
	
	int nivelesBajar = 2;
	
	public Arbol(Tablero actual)// crea el primer hijo, padre de toda la creacion
	{
		//int nivelesBajar = 1;
		char tturn;
		/*if ( turn == 'x'){
			tturn = 'o';
		}else{//*/
		
		tturn = 'x'; // ya que esto seria el equivalente al turno actual / el ultimo turno jugado
		// esto se debe a que lo unico que realmente necesitamos calcular son los proximos movimientos de o
		//no de x, siendo que es el jugador
		
		//}
		
		this.tab = new Tablero(actual);
		
		int vac = this.tab.espVac();
		this.hijos = new Arbol[vac];
		this.nHijos = 9;
		for (int a = 0 ; a < 9 ; a ++)
		{
			hijos[a] = new Arbol(this.tab , tturn, a, nivelesBajar );
		}
	}
	private Arbol(Tablero anterior, char turn, int espvac, int nivelesBajar)//hijo normal intermedio, herederos de la creacion
	{
		char tturn;
		if ( turn == 'x')
		{
			tturn = 'o';
		}
		else
		{
			tturn = 'x';
		}
		int numvac = 0;
		
		this.tab = new Tablero(anterior);
		
		
				
		for (int b = 0 ; b < 9; b++)
		{
			int idato = this.tab.tablero[b/3][b%3] + 0;
			if ( idato == 0)
			{
				if(numvac == espvac)
				{
					this.tab.tablero[b/3][b%3] = tturn;
					b = 9;
					
				}
				numvac +=1;
			}
		}
		if(nivelesBajar > 0)
		{
			int vac = this.tab.espVac();
			this.nHijos = vac;
			hijos = new Arbol[vac];
			for (int a =0 ; a<vac ; a++)
			{
				hijos[a] = new Arbol(this.tab, tturn, a, nivelesBajar-1);
			}
			System.out.println();
		}
		else
		{
			this.nHijos = 0;
		}
		
	}

	
	int recorrer(int nn)// reacorre el arbol
	{// no me vino a la mente nada gracioso para decir por aca :v
		
//		if( nn != 0 && nn != 1) System.out.println(nn+"--------");
		
		
		// para mostrar los tableros
		//this.tab.show();
		
		int cc =0 ;
		
		for(int a = 0 ; a < this.nHijos ; a ++)
		{
			cc += this.hijos[a].recorrer(nn-1);
		}
		//if (nn == 2) System.out.println(cc);
		
		int res = cc;
		//if ( nn == 0)  
			res +=  1;
		
		return res;
	}
	
	int podarArbol(int nn)// sigue el camino que merece ser seguido juju
	{
		
//		if( nn != 0 && nn != 1) System.out.println(nn+"--------");
		
		
		// para mostrar los tableros
		//this.tab.show();
		
		int cc =0 ;
		Arbol arbolMayor;
		int valorAM = 0;
		
		
		for(int a = 0 ; a < this.nHijos ; a ++)
		{
			cc += this.hijos[a].podarArbol(nn-1);
		}
		
		
		if (this.nHijos > 1) // eliga al hijo con mayor valor
		{// aunque no se como ponerle que tambien tenga en cuenta el valor del hijo mas valioso
			for(int a = 0 ; a < this.nHijos ; a ++)
			{
				int valorH = this.hijos[a].tab.heuristica();
				
				if (a == 0)
				{
					valorAM = valorH;
					arbolMayor = this.hijos[a];
				}
				else if (valorH > valorAM)
				{
					valorAM = valorH;
					arbolMayor = this.hijos[a];
				}
					
			}
		}
		
		//if (nn == 2) System.out.println(cc);
		
		int res = cc;
		//if ( nn == 0)  
			res +=  1;
		
		return res;
	}
	
	
	/*int mejoresCaminos()
	{
		int cc =0 ;
		
		for(int a = 0 ; a < this.nHijos ; a ++)
		{
			cc += this.hijos[a].recorrer();
		}
		//if (nn == 2){System.out.println(cc);}
		
		return cc + 1;
	}
	*/
}
