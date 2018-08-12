
progenitor(pedro,[ana,ramon,pedro,javier,josh,vilma,nicolas]).
progenitor(juan, [ben,pepe,josue,jesica,pavel,keith,kyle]).

%para buscar en una lista

% la funcion member encuentra si un elemento A esta en un lista B
% member(A,B)
padreDe(Padre,Hijo) :- progenitor(Padre, Hijos), member(Hijo,Hijos).


/*
%padreDe(Padre,Hijo) :- progenitor(Padre, Hijos), buscar(Hijo,Hijos).

buscar(Hijo,[]) :- !, fail.
buscar(Hijo,[Hijo|L]) :- true.
buscar(Hijo,[C|L]) :- buscar(Hijo,L).
*/



/*%h(Dato) :- .recibe un dato y calcula su valor... ni idea como


organizarLista(C1,C2,Resp):-
    ( h(C1) > h(C2) -> Resp = C1  )
    (   )
    .
organizarLista([C1|Cola],Resp) :-
*/

