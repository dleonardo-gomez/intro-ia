


%lista de elementos de larbol
estado(cajaEn(azul,h1),cajaEn(verde,h1),robot(h1)).
objetivo(cajaEn(azul,h2),cajaEn(verde,h1),robot(h2)).




nodo([coger,cambiarCuarto,soltar]).


/*
%saca el mayor valor de una lista, tuve que copiar un codigo casi completamente   para lograr entender esto....
mayor(Ccab, [],Ccab) .
mayor(Ccab, [Cab|L],May ):- Ccab > Cab, mayor(Ccab,L,May) .
mayor(Ccab, [Cab|L],May ):- Ccab =< Cab, mayor(Cab,L,May) .
mayor([Cab|L],May) :-mayor(Cab,L,May) .
*/

%hola = [4,5,6,7].

%hola([1,2,3,4,5].
%saca el mayor valor de una lista
mayor(Ccab, [],Ccab) .

mayor(Ccab, [Cab|L],May ):-
    heu(Ccab,Cab,Val1,Val2),
    Val1 > Val2,
    %Ccab > Cab,
    mayor(Ccab,L,May).

mayor(Ccab, [Cab|L],May ):-
    heu(Ccab,Cab,Val1,Val2),
    Val1 =< Val2,
    %Ccab =< Cab,
    mayor(Cab,L,May).

cMayor([Cab|L],May) :-
    mayor(Cab,L,May).
% ----------------------------- de aqui para abajo estoy seguro que
%esta bien

heu(C1,C2,V1,V2):- heuri(C1,V1),heuri(C2,V2).

% saca el tamaño de la lista Prioridades, lo guarda en Ubi y lo pasa
% a listPri, que ira reduciendo el valor hasta llegar al punto en el
% que este ubicado el valor, retornandolo por Val
heuri(CC,Val):-
    listPri(CC ,Val).





/*
estado(cajaEn(azul,h1),cajaEn(verde,h1),robot(h1)).
objetivo(cajaEn(azul,h2),cajaEn(verde,h1),robot(h2)).
*/

%nodo([coger(1),cambiarCuarto(2),soltar(3)]).

%estas funciones deciden cual es la suiguiente accion a tomar
accion(N,Col):- aCoger(AC,Col),aCCuarto(ACC),aSoltar(AS),AC > ACC , AC > AS , N is 1 .

accion(N,_):- aCoger(AC),aCCuarto(ACC),aSoltar(AS),AC < ACC , AC > AS , N is 2 .

accion(N,Col):- aCoger(AC),aCCuarto(ACC),aSoltar(AS),AC > ACC , AC < AS , N is 3 .

cActCuartoC(Ret, Col):- Col = azul , estado([Ret|_],_,_).
cActCuartoC(Ret, Col):- Col = verde , estado(_,[Ret|_],_).
cActCuartoRob(Ret):- estado(_,_,[Ret|[]]).

cObjCuartoC(Ret,Col):- Col = azul , objetivo([Ret|_],_,_).
cObjCuartoC(Ret,Col):- Col = verde , objetivo(_,[Ret|_],_).
cObjCuartoRob(Ret):- objetivo(_,_,[Ret|[]]).

%importancia 15
%cajaEn(azul,h2)
aCoger(Imp,Col):-
    cActCuartoRob(H) ,% conocer la ubicacion del robot
    Col is azul,%para la caja azul
    cActCuartoC(H,Col),%estado actual caja
    cObjCuartoC(H2,Col),%estado final
    H \== H2 , % en caso de ser diferente, significa que el cubo quiere cambiar
    plib,
    Imp is 15.

aCoger(Imp,Col):-
    cActCuartoRob(H) ,% conocer la ubicacion del robot
    Col is verde,% para la caja verde
    cActCuartoC(H,Col),%estado actual caja
    cObjCuartoC(H2,Col),%estado final
    H \== H2 , % en caso de ser diferente, significa que el cubo quiere cambiar
    plib,
    Imp is 15.

%importancia 10
aCCuarto(Imp):- %con caja
   noplib,
   Imp is 10.

aCCuarto(Imp):- %sin caja
   cActCuartoRob(H),
   cObjCuartoRob(H2),
   H \== H2,
   H is H2, %no se si esto sea sufuciente para hacerlo cambiar de cuarto
   Imp is 10.

% importancia 20, va a querer soltar lo que traiga para poder hacer algo
% mas

aSoltar(Imp):-
    noplib, % pinza no vacia
    cuboenpinza(Col), % para saber cual es el cubo en la pinza
    cActCuartoRob(H),% saber ubicacion de rob
    cObjCuartoC(H,Col), % el objetivo es el cuarto en el que esta rob
    Imp is 20
    .


