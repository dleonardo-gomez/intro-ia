%Se inicia "menu."
%Toca crearlo dinamico por que aja :v
:-dynamic(pinza/0).
:-dynamic(pinza/1).
:-dynamic(robot/1).
:-dynamic(cajaEn/2).
:-dynamic(cajasHabitacion/2).

% ya no se si sean necesarios
%:-dynamic(estado/3).
%:-dynamic(objetivo/3).

%estado(cajaEn(azul,h1),cajaEn(verde,h1),robot(h1)).
%objetivo(cajaEn(azul,h2),cajaEn(verde,h1),robot(h2)).
estado(H1,H2,Hr):- cajaEn(azul,H1);cajaEn(verde,H2);posRobot(Hr) .
objetivo(H1,H2,Hr):- cajaObjEn(azul,H1),cajaObjEn(verde,H2),posObjRobot(Hr) .


%lo que se sabe objetivo
cajaObjEn(azul,h2).
cajaObjEn(verde,h1).
cajasObjHabitacion(h1,[azul,verde]).
cajasObjHabitacion(h2,[]).

%Posicion del robot objetivo
objRobot(h2).
posObjRobot(H):- objRobot(H).

%"objetos"
caja(azul).
caja(verde).
habitacion(h1).
habitacion(h2).
puerta(h1,h2).

%lo que se sabe"
cajaEn(azul,h1).
cajaEn(verde,h1).
cajasHabitacion(h1,[azul,verde]).
cajasHabitacion(h2,[]).


%Estados de la pinza (Siempre toma el primero cuando inicia el programa, no se pueden borrar
%los otros por que causa un error raro por algo dinamico)
pinza().
pinza(azul).
pinza(verde).
%Posicion del robot
robot(h1).
posRobot(H):- robot(H).

%Comprueba si existe puerta
existePuerta(X,Y):-
    (   puerta(X,Y); puerta(Y,X)),true.

%Comprueba si esta vacia
plib():-
    pinza()
    %,write('yes'),nl
    .

%Coloca no si la pinza esta llena
/*plib():-
    not(pinza()),
    write('no'),
    nl.
*/
%dice que caja tiene
plib(Cajita):-
    pinza(Cajita)
    %,write('ni'),nl
    ,!.

%el menu
ejecutar(Opcion):-
    Opcion == 1,coger(),menu;
    Opcion == 2,plib(),menu;
    Opcion == 3,mover(),menu;
    Opcion == 4,posicion,menu;
    Opcion == 5,soltar(),menu;
    Opcion == 6,cajasCuarto(),menu;
    Opcion == 0,false,fail,!.

menu:-
    write('1. coger: (Nombre de la caja)'),nl,
    write('2. vacia pinza ?'),nl,
    write('3. Mover de _ a _ '),nl,
    write('4. Posicion robot '),nl,
    write('5. Soltar '),nl,
    write('0. salir'),
    read(Opcion),
    ejecutar(Opcion).

my_remove_one_element(X, [X|Xs], Xs).

my_remove_one_element(X, [Y|Ys], [Y|Zs]):-
    my_remove_one_element(X, Ys, Zs).
%

%Se agregan cosas a la pinza
llenarPinza(Cajita):-
    assert(pinza(Cajita)),
    !.
%Se quita la pinza vacia, se quita la habitacion de la caja, Este no se para que sirve lo dejare ahi por si acaso
%elimina las cajas de la habitacion, esto es lo que quiero arreglar arriba, agrega la lista de cajas a la habitacion
eliminarCoger(Cajita):-
    retractall(pinza()),
    retractall(cajaEn(Cajita,_)),
    cajasHabitacion(H,M),
    my_remove_one_element(Cajita,M, L),
    retractall(cajasHabitacion(H,_)),
    assert(cajasHabitacion(H,L)),
    !.

%Se coloca que se llena la pinza
agregarCoger(Cajita):-
    llenarPinza(Cajita),
    !.
%La caja que quiere leer, la caja, mira si la caja existe, obtiene posicion del robot, mira si
%ambos estan en el mismo cuarto, mira si la pinza esta vacia
%se hace lo de arriba, se hace lo de arriba
coger():-
    write('Elemento a coger'),
    read(Cajita),
    caja(Cajita),
    posRobot(H),
    cajaEn(Cajita,H),
    plib()
    ,eliminarCoger(Cajita),
    agregarCoger(Cajita).

coger(Cajita):-
    caja(Cajita),
    posRobot(H),
    cajaEn(Cajita,H),
    plib(),
    eliminarCoger(Cajita),
    agregarCoger(Cajita).

%Era para ver la posicion del robot creo que no se usa mas
posicion:-
    posRobot(H),
    write(H),
    nl.

%se elimina el robot del cuarto donde esta
eliminarMover(De):-
    retractall(robot(De)),
    !.

%se agrega el robot al cuarto que quiere ir
agregarMover(A):-
    assert(robot(A)),
    !.

%Se piden los cuartos, comprueba si hay puerta, y pues lo mismo de arriba
mover():-
    write('De'),
    read(De),
    write('a'),
    read(A),
    existePuerta(De,A),
    eliminarMover(De),
    agregarMover(A).

mover(De,A):-
    existePuerta(De,A),
    eliminarMover(De),
    agregarMover(A).

%Saca la lista del cuarto y la agrega a la lista del nuevo cuarto
compararSoltar(H,Cajita,L):-
    write(Cajita),
    cajasHabitacion(H,M),
    L = [M|Cajita].

    %Cajita == azul,cajasHabitacion(H,M), member(verde,M), L = [verde,azul];
    %					Cajita == verde,cajasHabitacion(H,M), member(azul,M), L = [azul,verde];
    %					Cajita ==
%Falta agregar la caja al cuato cuando se agrega

%Se mira la pos del roboto (Creo que no es necesario, si algo luego se quita, pero por ahora dejelo ahi),
%elimina la pinza de la garra
eliminarSoltar(Cajita):-
    posRobot(H),
    write(H),
    retractall(pinza(Cajita)),
    !.

%La posicion del robot (aqui si importa), agrega a L(lista antigua) la nueva caja,
%elimina las cajas de la habitacion, agrega la nueva lista (L), a la caja le agrega el cuarto al que corresponde
agregarSoltar(Cajita):-
    posRobot(H),
    compararSoltar(H,Cajita,L),
    retractall(cajasHabitacion(H,_)),
    assert(cajasHabitacion(H,L)),
    write(H),
    write(L),
    assert(cajaEn(Cajita,H)),
    !.

%Mira si la pinza tiene algo, hace lo de arriba, hace lo de arriba, se agrega la pinza vacia
soltar():-
    plib(Cajita),
    write(Cajita),
    agregarSoltar(Cajita),
    eliminarSoltar(Cajita),
    assert(pinza()).

%Imprime la lista de caja del cuarto que lee H
cajasCuarto():-
    write('Cuarto'),
    read(H),
    cajasHabitacion(H,L),
    write(L),nl.


%---------------------------------------------------------------------------------


%estado(H1,H2,Hr):- cajaEn(azul,H1),cajaEn(verde,H2),posRobot(Hr) .
% objetivo(H1,H2,Hr):- cajaObjEn(azul,H1),cajaObjEn(verde,H2),posObjRobot(Hr).

cicloACompObjetivo():-
    %alguna de estas se cumpla

    estado(CAa,CAv,CAr),objetivo(COa,COv,COr),

    (( CAa \== COa) ; (CAv \== COv);( CAr \== COr ) ),

    %entonces decide actuar
    accion(),
    cicloACompObjetivo()
    .


%[coger(1),cambiarCuarto(2),soltar(3)].

%-----
accion():- aCoger(AC,Col),aCCuarto(ACC,H1,H2),aSoltar(AS),
    (
                                                  ( AC > ACC , AC > AS ,  write('coger ') ,coger(Col)   );% algo le llama la atencion en las cajas regadas en el piso, por lo que decide recojer una
                                                  ( AC < ACC , AC > AS ,  write('mover') ,mover(H1,H2) );% le dan unas extrañas ganas de cambiar de cuarto, por lo que cambia de cuarto
                                                  ( AC > ACC , AC < AS ,  write('soltar'),soltar()     ) % decide que realmente quiere soltar el cubo que tiene
                                              ) .


% importancia 15, ya que verdaderamente es lo que nuestro pequeño rob
% mas desea en la vida

aCoger(Imp,azul):- %para la caja azul -- probado
    plib(),
      estado(H ,_,H) ,% conocer la ubicacion del robot y del cubo azul
    objetivo(H2,_,_),%estado objetivo cubo
    H \== H2 , % en caso de ser diferente, significa que el cubo quiere cambiar
    %Col is (azul),%para la caja azul
    Imp is 15.

aCoger(Imp,verde):- %para la caja verde
    plib(),
      estado(_,H ,H) ,% conocer la ubicacion del robot
    objetivo(_,H2,_),%estado objetivo cubo
    H \== H2 , % en caso de ser diferente, significa que el cubo quiere cambiar
    %Col is (verde),% para la caja verde
    Imp is 15.

%importancia 10
aCCuarto(Imp,H,H2):- %con caja
   not(plib()),
   plib(Col),
   cajaObjEn(Col,H2),
   estado(_,_,H),
   H \== H2,
   %H is H,
   %H2 is HH,
   Imp is 10
   , write('con caja'),nl
   ,!
   .
% estado(_,_,H),objetivo(_,_,H2),H \==
% H2,existePuerta(H,H2),write('funciona'),nl.
aCCuarto(Imp,H,H2):- %sin caja -- probado
    plib(),
    estado(_,_,H),
    objetivo(_,_,H2),
    H \== H2,
    existePuerta(H,H2),
    %H is H,
    %H2 is H2,
    Imp is 10
    , write('sin caja'),nl
    ,!
    .

% importancia 20, va a querer soltar lo que traiga para poder hacer algo
% mas, pero solo si el cubo asi lo quiere
% % Caja: no me sueltes Rob-senpai
% % Rob: nunca te soltaria caja-chan
%   *procede cambiar de cuarto y soltar a caja-chan*
% por favor quitar esto antes de entregar
aSoltar(Imp):- %probado
    not(plib()), % pinza no vacia
    plib(Col), % para saber cual es el cubo en la pinza
    estado(_,_,H),% saber ubicacion de rob
    cajaObjEn(Col,H), % el objetivo es el cuarto en el que esta rob
    Imp is 20.

%not(plib()),plib(Col),estado(_,_,H),cajaObjEn(Col,H)
