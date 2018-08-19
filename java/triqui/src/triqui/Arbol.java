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
		
		
		this.tab = new Tablero(anterior);
		
		
		this.tab.modificarTablero(espvac, tturn); // se modifica el tablero en la posicion espvac
		
		
		if(nivelesBajar > 0)
		{
			int vac = this.tab.espVac();
			this.nHijos = vac;
			hijos = new Arbol[vac];
			for (int a =0 ; a<vac ; a++)
			{
				hijos[a] = new Arbol(this.tab, tturn, a, nivelesBajar-1);
			}
			//System.out.println();
		}
		else
		{
			this.nHijos = 0;
		}
		
	}

	
	int recorrer(int nn)// reacorre el arbol
	{// no me vino a la mente nada gracioso para decir por aca :v
		
//		if( nn != 0 && nn != 1) System.out.println(nn+"--------");
		
		
		// para mostrar los tableros y su nivel
		/*
		System.out.println("--------------------\n" +nn );
		this.tab.show();
		//*/
		
		int cc =0 ;
		
		//if (this.nHijos > 0) System.out.println(nn+"num hijos : "+ this.hijos.length);
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
	
	int podarArbol(int nn,int raiz)// sigue el camino que merece ser seguido juju
	{
		Arbol hijoFavorito = null ; 
		int mayVal = 0 ;
		if (nn> 0)
		{
			for(int a = 0 ; a < this.nHijos ; a ++)
			{
				int valorH = this.hijos[a].podarArbol(nn-1,raiz);
				
				if (a == 0)
				{
					mayVal = valorH;
					hijoFavorito = this.hijos[a];
				}
				else if (valorH > mayVal)
				{
					mayVal = valorH;
					hijoFavorito =  this.hijos[a];
				}
					
			}
		}
		else if(nn == 0)
		{
			return this.valor;
		}
		
		int val = this.valor + mayVal;
		
		if (nn != raiz)
		{
			this.hijos = new Arbol[1];
			this.hijos[0] = hijoFavorito;
			this.nHijos = 1;
		}
		return val;
		
	}
	
}
