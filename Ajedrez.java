/*Clase para validar las jugadas de un tablero de ajedrez

revisaPeon(i,j,p,q), un comportamiento para cada pieza (regresa 0 si no y otro valor si es posible)
Revisa si peón en i,j puede ser movido a p,q
*/
import java.util.Scanner;
public class Ajedrez{
  //Atributos 
  //arreglo de tablero
  int[][] M;
  //banderas que nos ayudan a hacer los enroques
  boolean ReyN=false;
  boolean ReyB=false;
  boolean TorreIN=false;
  boolean TorreDN=false;
  boolean TorreIB=false;
  boolean TorreDB=false;


  //Constructor
  public Ajedrez(){
    //se inicializa la matriz bidimensional como un arreglo 8x8
    this.M=new int[8][8];
    //se llenan las casillas con 0's (sin piezas)
    for (int i=0;i<8;i++) {
      for (int j=0;j<8;j++) {
        M[i][j]=0;
      }
    }
    //se visualiza el tablero
    Visualiza();
  }

  //metodos
  //Visualiza, metodo para imprimir en pantalla el tablero con las piezas
  //se consideran los numeros negativos como piezas negras y los positivos como 
  //piezas blancas. Para hacer el cambio de números a la letra de la 
  //pieza, se ocupa un switch case considerando los tipos de pieza y su color
  public void Visualiza(){
    //Impresión del tablero
    //indicaciones
    System.out.println("LAS LETRAS MINUSCULAS SON PIEZAS NEGRAS");
    System.out.println("t = (-1)Torre , c = (-2)Caballlo, a = (-3)Alfil, q = (-4)Reina , k = (-5)Rey, p = (-6)Peon");
    System.out.println("+++++++++++++++++++++++++TABLERO+++++++++++++++++++++++++");
    //regla horizontal
    String tablero="|   |j0 |j1 |j2 |j3 |j4 |j5 |j6 |j7 |\n";
    tablero+="-------------------------------------"+"\n";
    //se recorre el arreglo M, dependiendo del número en dicha casilla, colocará
    //cierta letra en el mesaje que se imprime al final
    for (int i=0;i<8;i++) {
      tablero+="|i"+i+" | ";
      for (int j=0;j<8;j++) {
          switch(M[i][j]){
            case 0:
              tablero+="  | ";
              break;
            case 1:
              tablero+="T | ";
              break;
            case 2:
              tablero+="C | ";
              break;
            case 3:
              tablero+="A | ";
              break;
            case 4:
              tablero+="Q | ";
              break;
            case 5:
              tablero+="K | ";
              break;
            case 6:
              tablero+="P | ";
              break;
            case -1:
              tablero+="t | ";
              break;
            case -2:
              tablero+="c | ";
              break;
            case -3:
              tablero+="a | ";
              break;
            case -4:
              tablero+="q | ";
              break;
            case -5:
              tablero+="k | ";
              break;
            case -6:
              tablero+="p | ";
              break;
          }
      }
      tablero+="\n"+"-------------------------------------"+"\n";
    }
    //se imprime el mensaje del tablero junto con otras indicaciones
    System.out.println(tablero);
    System.out.println("LAS LETRAS MAYUSCULAS SON PIEZAS BLANCAS");
    System.out.println("T = (1)Torre , C = (2)Caballlo, A = (3)Alfil, Q = (4)Reina , K = (5)Rey, P = (6)Peon");
  }

  //Asigna, coloca una pieza en cierta posición del tablero validando si esta vacia o no
  //la casilla. Se considera el caso en que este vacia y en el que no esté vacia
  public boolean Asigna(int i, int j, int pieza){
    //Para colocar las piezas, si la casilla tiene un 0 es por que esta vacia, por lo tanto, 
    //se puede agregar una pieza y regresa un true
    //si no es igual a cero, indica si en la casilla hay una pieza blanca o negra y 
    //regresa un falso por que no se puede agregar una pieza ahí
    if(M[i][j]==0){
      M[i][j]=pieza;
      return true;
    }else if(M[i][j]<0){
      System.out.println("Hay una pieza negra en esa posición");
      return false;
    }else{
      System.out.println("Hay una pieza blanca en esa posición");
      return false;
    }    
  }

  //Juega
  /*El metodo juega se encarga de desarrollar el juego desde la toma de piezas
  se pide una cantidad de piezas, se revisa el caso en que el número de piezas
  sea menor a 0.
  Se toman las piezas validando que las piezas y las posiciones se encuentren dentro
  de los valores permitidos por el programa.
  El movimiento de piezas se ve representado por una casilla y a que otra casilla
  se moverá. Se consideran los casos de que el movimiento sea exitoso o no.
  */
  public void Juega(){
    //se inician variables auxiliares
    int pieza,posI,posJ,i=0,posP,posQ;
    Scanner read = new Scanner(System.in);

    System.out.println("+++++++++++++++++++++LECTURA DE DATOS++++++++++++++++++++");    
    //instrucciones y pedida de datos
    System.out.println("Ingresa la cantidad de piezas que agregaras");
    int cantidadPiezas=read.nextInt();
    while(cantidadPiezas<1){
      cantidadPiezas=read.nextInt();
    }

    System.out.println("Ingresa las piezas y su posición, ocuparemos la siguente nomenclatura: ");
    System.out.println("---Torre:    1---");
    System.out.println("---Caballo:  2---");
    System.out.println("---Alfil:    3---");
    System.out.println("---Reina:    4---");
    System.out.println("---Rey:      5---");
    System.out.println("---Peon:     6---");
    System.out.println("¡¡¡¡¡¡¡¡IMPORTANTE!!!!!!!!");
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println("Numeros POSITIVOS son piezas BLANCAS y NEGATIVOS son piezas NEGRAS");
    System.out.print("El tablero esta programado para tener las piezas negras arriba, por lo cual");
    System.out.println(" los peones negros solo se pueden mover para abajo y los blancos para arriba");
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println("LAS LETRAS MAYUSCULAS SON PIEZAS BLANCAS");
    System.out.println("T = (1)Torre , C = (2)Caballlo, A = (3)Alfil, Q = (4)Reina , K = (5)Rey, P = (6)Peon");
    System.out.println("LAS LETRAS MINUSCULAS SON PIEZAS NEGRAS");
    System.out.println("t = (-1)Torre , c = (-2)Caballlo, a = (-3)Alfil, q = (-4)Reina , k = (-5)Rey, p = (-6)Peon");
    System.out.print("++++++++++++++LEA las reglas mencionadas arriba para CUALQUIER jugada, debido a la falta ");
    System.out.println("de especificaciones el ajedrez se desarrollo con estas restricciones++++++++++++++++++++++++++");
    System.out.println("continuar? 1: si");
    int cont=0;
    do{
      cont=read.nextInt();
    }while(cont!=1);
    //se toman los datos mientras aun haya piezas por colocar, para esto ocupamos el contador
    //i, que nos ayudará a saber cuantas piezas se han colocado
    System.out.println("*****************TOMANDO LOS DATOS*******************");  
    while(i<cantidadPiezas) {
      System.out.println("Ingresa un numero de pieza entre 1 y 6");
      //pide y revisa una pieza y que esta se encuentre dentro de las permitidas
      do{
        System.out.println("pieza: ");
        pieza=read.nextInt();
      }while(pieza==0 || pieza>6 || pieza<-6);
      
      System.out.println("Ingresa una posición para i y j entre 0 y 7");
      //pide y revisa una posición y que esta se encuentre dentro del tablero
      do{
        System.out.print("i: ");
        posI=read.nextInt();
      }while(posI<0 || posI>7);

      //pide y revisa una posición y que esta se encuentre dentro del tablero
      do{
        System.out.print("j: ");
        posJ=read.nextInt();
      }while(posJ<0 || posJ>7);

      //si se pudó asignar la pieza, se aumenta el contador, en caso contrario
      //no toma en cuenta esa asignación
      if(Asigna(posI,posJ,pieza)){
        i++;
      }
      //se visualiza el tablero
      Visualiza();
    }
    
    System.out.println("+++++++++++++++++++++INICIANDO JUEGO+++++++++++++++++++++++");  
    int continua=0;
    //do while para jugar, mientras conituna se tome como 1, se seguira jugando
    do{
      //se piden las posiciones de la pieza que se quiere mover y a donde se quiere mover
      System.out.println("Ingresa una posición para i, j, p y q entre 0 y 7");
      System.out.print("i: ");
      posI=read.nextInt();

      System.out.print("j: ");
      posJ=read.nextInt();

      System.out.print("p: ");
      posP=read.nextInt();

      System.out.print("q: ");
      posQ=read.nextInt();

      //con las posiciones, se revisa si es posible realizar la jugada o no
      //en caso positivo, se mueve la pieza
      //en caso negativo, se arroja un mensaje
      if(RevisaTirada(posI,posJ,posP,posQ)!=0){
        Mover(posI,posJ,posP,posQ);
      } else{
        System.out.println("¡¡¡¡No se puede hacer ese movimiento!!!\n");
      }
      //se visualiza el tablero y se indica si se desea seguir jugando
      Visualiza();
      System.out.println("****************************¿Quiere hacer otra jugada? 1: si");
      continua=read.nextInt();
    }while(continua==1);
  }

  //RevisaTirada
  /*Se validan los diferentes casos para el movimiento de una pieza
  1.- Que la casilla de donde se desea mover exista
  2.- Que la casilla a donde se desea mover exista
  3.- Que en la casilla de donde nos moveremos exista una pieza
  4.- Si es que la casilla de donde nos movemos y la casilla a la que nos movemos
      son la misma casilla
  5.- Si es un movimiento para "comer" una pieza, que la pieza que vaya a ser comida
      no sea del mismo color que la pieza que va a comer
  6.- Comportamiento de la pieza de acuerdo a que pieza es*/
  public int RevisaTirada(int i, int j, int p, int q){
    int bandera=1;
    //Revisa si i,j y p,q están el el tablero
    if(i<0 || i>7){
      System.out.println("Esa posición i no existe");
      return 0;
    }
    if(j<0 || j>7){
      System.out.println("Esa posición j no existe");
      return 0;
    }
    if(p<0 || p>7){
      System.out.println("Esa posición p no existe");
      return 0;
    }
    if(q<0 || q>7){
      System.out.println("Esa posición q no existe");
      return 0;
    }
    //Revisa que en i,j exista UNA pieza
    if(M[i][j]==0){
      System.out.println("En ese lugar no existe una pieza");
      return 0;
    }
    //si i=p y j=q significa que ingresaron las mismas coordenadas
    if(i==p && j==q){
      System.out.println("No se puede mover al mismo lugar");
      return 0;
    }
    //Revisa que si en i,j esta una pieza negativa en p,q no sea así (igual para las positivas)  
    if((M[i][j]<0 && M[p][q]<0) || (M[i][j]>0 && M[p][q]>0)){
      System.out.println("En ese lugar hay una pieza del mismo color, no se puede mover a ese lugar");
      return 0;
    }
    //revisa por comportamiento
    switch(Math.abs(M[i][j])){
      case 1:
        System.out.println("RevisaTorre");
        bandera=RevisaTorre(i,j,p,q);
        break;
      case 2:
        System.out.println("RevisaCaballo");
        bandera=RevisaCaballo(i,j,p,q);
        break;
      case 3:
        System.out.println("RevisaAlfil");
        bandera=RevisaAlfil(i,j,p,q);
        break;  
      case 4:
        System.out.println("RevisaReina");
        bandera=RevisaReina(i,j,p,q);
        break;
      case 5:
        System.out.println("RevisaRey");
        bandera=RevisaRey(i,j,p,q);
        break;
      case 6:
        System.out.println("RevisaPeon");
        bandera=RevisaPeon(i,j,p,q);
        break;
      default:
        System.out.println("Error de pieza");
        break;
    }

    return bandera;
  }

  //Mover
  /*La función mover mueve una pieza limpiando la casilla que deja libre, se consideran
  los siguientes casos:
  1.- Si se trata de una "comida al paso"
  2.- Si no se realizó la comida al paso en el turno en el que debía ser movido
  En dichos casos hace operaciones adicionales, pero siempre termina moviendo la pieza*/
  public void Mover(int i, int j, int p, int q){
    //Si se hace una comida al paso
    
    if(M[i][j]==-6 && M[p][q]==7){
      M[p-1][q]=0;
    }else if(M[i][j]==6 && M[p][q]==-7){
      M[p+1][q]=0;
    }
    //si en ij hay un peon blanco y en pq hay una señal
    //de que este peon se movió dos lugares, se limpia esta señal
    //lo mismo ocurre si hay un peon blanco con su señal
    if(M[i][j]==6 && M[i+1][j]==7){
      M[i+1][j]=0;
    }
    if(M[i][j]==-6 && M[i-1][j]==-7){
      M[i-1][j]=0; 
    }
    //mueve la pieza y limpia la casilla en la que estaba
    M[p][q]=M[i][j];
    M[i][j]=0;

  }

  //metodos para revisar
  //RevisaPeon
  /*Para validar el movimiento de un peón se valida lo siguiente
  1.- El color de la pieza, para determinar hacia donde se puede mover la pieza
  2.- Si el movimiento en vertical es de dos casillas, debe cumplir las condiciones
      necesarias para hacer el paso doble
  3.- Si solo se mueve una casilla hacia delante
  4.- Si hace un movimiento en diagonal para comer a una pieza*/
  public int RevisaPeon(int i,int j, int p, int q){
    //si es un peon negro
    if(M[i][j]<0){
      //si esta en su posición inicial, lo dejará avanzar 2 lugares
      if(p==i+2 && q==j && i==1){
        M[i+1][j]=-7;
        return 1;
      }else
      //solo se mueve hacia enfrente
      if(p==i+1 && q==j){
        return 1;
      //si come a una pieza
      }else if(p==i+1 && (q==j+1 || q==j-1)){
        if(M[p][q]>0){
          //para el comer al paso
          return 1;  
        }else{
          return 0;
        }
      }else{
        return 0;
      }
    //si es un peon blanco
    }else{
      //si esta en su posición inicial, lo dejará avanzar 2 lugares
      if(p==i-2 && q==j && i==6){
        M[i-1][j]=7;
        return 1;
      }else
      //solo se mueve hacia enfrente
      if(p==i-1 && q==j){
        return 1;
      //si come a una pieza que
      }else if(p==i-1 && (q==j+1 || q==j-1)){
        if(M[p][q]<0){
          return 1;  
        }else{
          return 0;
        }
      }else{
        return 0;
      }
    }
  }

  //RevisaTorre
  /*Se consideran dos principales casos para el movimiento de una torre
  Que se mueva en horizontal o que se mueva en vertical
  Si se mueve en horizontal, se revisa si se mueve a la derecha o a la izquierda para
  recorrer su camino y revisar que no se encuentren piezas en el camino, dado que la torre
  no salta piezas.
  Si se mueve en vertical, se revisa si se mueve hacia arriba o hacia abajo para
  recorrer su camino y revisar que no se encuentren piezas en el camino, dado que la torre
  no salta piezas.
  Se considera un caso especial en el que el número 7 es la marca de un peón que se movió
  dos casillas, solo es una marca por lo cual puede saltar esta señal la torre*/
  public int RevisaTorre(int i,int j, int p, int q){
    int bandera=1;
    /*se genera una variable que nos ayudará a movernos por el tablero
    revisando si se puede o no mover la torre así
    existen cuatro posibilidades, que se mueva arriba, abajo, izquiera o derecha
    Para esto separaremos en dos grupos, movimiento horizontal en el que i y p son iguales
    y el otro grupo es movimiento vertical, j y q son iguales
    Se va recorriendo el arreglo desde i,j hasta p,q revisando que no haya piezas en el camino
    o que si hay, solo sea la pieza destino para comer esa pieza*/
    int k;
    //horizontal, la coordenada en i queda igual
    if(i==p){
      k=j;
      //si se mueve a una coordenada menor en j
      if(j>q){
        k--;
        while(k>=q && bandera!=0){
          if(M[i][k]!=0){
            if(k==q || M[i][k]==7 || M[i][k]==-7){
              bandera=1;
            }else{
              bandera=0;
            }
          }
          k--;
        }
      //si se mueve a una coordenada mayor en j
      }else{
        k++;
        while(k<=q && bandera!=0){
          if(M[i][k]!=0 ){
            if(k==q || M[i][k]==7 || M[i][k]==-7){
              bandera=1;
            }else{
              bandera=0;
            }
          }
          k++;
        }
      }
    //vertical
    }else if(j==q){
      k=i;
      //si se mueve a una coordenada menor en i
      if(i>p){
        k--;
        while(k>=p && bandera!=0){
          if(M[k][j]!=0){
            if(k==p  || M[k][j]==7 || M[k][j]==-7){
              bandera=1;
            }else{
              bandera=0;
            }
          }
          k--;
        }
      //si se mueve a una coordenada mayor en i
      }else{
        k++;
        while(k<=p && bandera!=0){
          if(M[k][j]!=0){
            if(k==p  || M[k][j]==7 || M[k][j]==-7){
              bandera=1;
            }else{
              bandera=0;
            }
          }
          k++;
        }
      }
    }else{
      bandera=0;
    }
    //validación para el enroque, si se mueve alguna torre que esta en las esquinas
    //mandar la señal de que no se podrá mover para el enroque
    if(bandera==1){
      if(M[i][j]<0 && i== 0 && j==0){
        TorreIN=true;
      }else if(M[i][j]<0 && i== 0 && j==7){
        TorreDN=true;
      }else if(M[i][j]>0 && i== 7 && j==0){
        TorreIB=true;
      }else if(M[i][j]>0 && i== 7 && j==7){
        TorreDB=true;
      }
    }
    return bandera;
  }

  //RevisaCaballo
  /*siendo un caballo, existen 8 posibles lugares a los que se puede mover
    sin importar si salta o no piezas, por eso no se valida eso
    primero se revisa la posibilidad de que se mueva dos unidades hacia arriba o 
    hacia abajo y después que se mueva una unidad a la izquierda o a la derecha, cualquier
    combinación de esas posibilidades es valida
    después revisa si se mueve dos unidades a la izquierda o derecha y después una unidad
    arriba o abajo, cualquier combinación de estas es posible*/
  public int RevisaCaballo(int i,int j, int p, int q){
    //se genera la bandera
    int bandera=1;
    if((p==i+2 || p==i-2) && (q==j+1 || q==j-1) ){
      bandera=1;
    }else if((q==j+2 || q==j-2) && (p==i+1 || p==i-1) ){
      bandera=1;
    }else{
      bandera=0;
    }
    return bandera;
  }

  //RevisaAlfil
  //hay cuatro casos que se revisa, que se mueva en diagonal a alguno de los 4 cuadrantes
  //cualesquiera, tomando como referencia a i,j, las opciones son que:
  //p negativo, q negativo
  //p negativo, q positivo
  //p positivo, q negativo
  //p positivo, q positivo
  //se exploran los 4 cosas, primero verificando la coordenada p y luego q
  //reduciendo un poco la cantidad de condicionales
  //si durante su trayecto hacia p,q hay alguna pieza, salta el error de no saltar piezas
  //exceptuando si es que la coordenada p,q tiene una pieza, entonces se come a la pieza
  public int RevisaAlfil(int i,int j, int p, int q){
    int bandera=1;
    //si el movimiento es en diagonal
    if(Math.abs(i-p)==Math.abs(j-q)){
      //calcula la cantidad de casillas que se movera e inicializa una variable en 1
      int paso=Math.abs(i-p);
      int k=1;
      if(p<i){
        if(q<j){
          while(k<=paso && bandera!=0){
            if(M[i-k][j-k]!=0){
              if((i-k==p && j-k==q) || M[i-k][j-k]==7 || M[i-k][j-k]==-7){
                bandera=1;
              }else{
                bandera=0;
              }
            }
            k++;
          }
        }else{
          while(k<=paso && bandera!=0){
            if(M[i-k][j+k]!=0){
              if((i-k==p && j+k==q) || M[i-k][j+k]==7 || M[i-k][j+k]==-7){
                bandera=1;
              }else{
                bandera=0;
              }
            }
            k++;
          }
        }
      }else{
        if(q<j){
          while(k<=paso && bandera!=0){
            if(M[i+k][j-k]!=0){
              if((i+k==p && j-k==q) || M[i+k][j-k]==7 || M[i+k][j-k]==-7){
                bandera=1;
              }else{
                bandera=0;
              }
            }
            k++;
          }
        }else{
          while(k<=paso && bandera!=0){
            if(M[i+k][j+k]!=0){
              if((i+k==p && j+k==q) || M[i+k][j+k]==7 || M[i+k][j+k]==-7){
                bandera=1;
              }else{
                bandera=0;
              }
            }
            k++;
          }
        }
      }
      
    //si no es en diaonal, se sale por que el alfil no se puede mover así
    }else{
      bandera=0;
    }
    return bandera;
  }

  //RevisaReina
  //la reina se comporta como una torre o como alfil, por lo cual
  //reutilizaremos los códigos
  //se revisa si tiene comportamiento de torre
  //si no cumple, revisa si tiene comportamiento de alfil
  //si no cumple, no se puede mover
  public int RevisaReina(int i,int j, int p, int q){
    int bandera=1;
    if(RevisaTorre(i,j,p,q)!=0){
      bandera=1;
    }else if(RevisaAlfil(i,j,p,q)!=0){
      bandera=1;
    }else{
      bandera=0;
    }
    return bandera;
  }

  //RevisaRey
  /*El primer caso que se considera es si el movimiento de de más de dos casillas
  para esto el movimiento debe ser en horizontal, para hacer el enrroque del rey
  con la torre.
  Primero se secciona el código dependiendo si es un enrroque del rey blanco o del
  negro.
  Después se considera que sea un enroque corto y otro caso valida el enroque largo,
  y que se cumplan las condiciones de que el rey y la torre no se hayan movido hasta
  el momento del enrroque.
  Si el movimiento no es mayor a 1, y es de 1
  Revisa si se da un comportamiento como el de la reyna (movimiento hacia todas
  direcciones) y que cumpla con que la distancia tanto de en horizontal como en 
  vertical sean menores o iguales a 1, para considerar movimientos rectos y 
  diagonales.
  A continuación, si cumple con las anteriores restricciones, se revisan las casillas
  adyacentes a la casilla a la que nos moveremos, si es que existe casilla, para 
  revisar que no exista un rey cerca de otro, dado que deben estar separados por lo
  menos una casilla*/
  public int RevisaRey(int i,int j, int p, int q){
    int bandera=1;
    //hay que validar el enroque
    //si se mueve dos lugares en horizontal
    if(Math.abs(j-q)>1){
      //si es una pieza negra
      if(M[i][j]<0 && i==0 && j==4){
        //si se va a mover hacia la derecha (viendo la pantalla)
        if(q>j){
          if(!ReyN && !TorreDN){
            ReyN=true;
            TorreDN=true;
            //movimiento de la torre
            Mover(0,7,0,5);
            bandera=1;

          //si ya se movieron las piezas
          }else{
            bandera=0;
          }
        //si se mueve hacia la izquierda (viendo la pantalla)
        }else{
          if(!ReyN && !TorreIN){
            ReyN=true;
            TorreIN=true;
            //movimiento de la torre
            Mover(0,0,0,3);
            bandera=1;

          //si ya se movieron las piezas
          }else{
            bandera=0;
          }
        }
      //si es una pieza blanca
      }else if(M[i][j]>0  && i==7 && j==4){
        //si se va a mover hacia la derecha (viendo la pantalla)
        if(q>j){
          if(!ReyB && !TorreDB){
            ReyB=true;
            TorreDB=true;
            //movimiento de la torre
            Mover(7,7,7,5);
            bandera=1;

          //si ya se movieron las piezas
          }else{
            bandera=0;
          }
        //si se mueve hacia la izquierda (viendo la pantalla)
        }else{
          if(!ReyB && !TorreIB){
            ReyB=true;
            TorreIB=true;
            //movimiento de la torre
            Mover(7,0,7,3);
            bandera=1;
            
          //si ya se movieron las piezas
          }else{
            bandera=0;
          }
        }
      }else{
        bandera=0;
      }

    }else{
      //el rey se comporta como la reina, solo que se mueve de una sola casilla a la vez
      if(RevisaReina(i,j,p,q)!=0 && Math.abs(i-p)<=1 && Math.abs(j-q)<=1 ){
        //pero hay que recordar que el rey debe estar alejado de otro rey por una casilla
        if(p+1<8 && q+1<8){
          if(Math.abs(M[p+1][q+1])==6 && p+1!=i && q+1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(p+1<8 && q-1>-1){
          if(Math.abs(M[p+1][q-1])==6 && p+1!=i && q-1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(p-1>-1 && q+1<8){
          if(Math.abs(M[p-1][q+1])==6 && p-1!=i && q+1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(p-1>-1 && q-1>-1){
          if(Math.abs(M[p-1][q-1])==6 && p-1!=i && q-1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(q+1<8){
          if(Math.abs(M[p][q+1])==6 && p!=i && q+1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(q-1>-1){
          if(Math.abs(M[p][q-1])==6 && p!=i && q-1!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(p+1<8){ 
          if(Math.abs(M[p+1][q])==6 && p+1!=i && q!=j){ 
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        }
        if(p-1>-1){
          if(Math.abs(M[p-1][q])==6 && p-1!=i && q!=j){
            System.out.println("El rey debe estar separado 1 casilla de otro rey");
            bandera=0;
          }
        } 
      }else{
        bandera=0;
      }
      //se valida si se mueve o no el rey para prender su movimiento
      if(bandera==1){
        //si se va a mover el rey negro
        if(M[i][j]<0){
          ReyN=true;
        //si se va a mover el rey blanco
        }else{
          ReyB=true;
        }
      }
    }

    return bandera;
  }

  //main
  /*Metodo estatico que genera un objeto de tipo Ajedrez y llama al metodo
  juega de ese objeto*/
  public static void main(String[] args) {
    Ajedrez a = new Ajedrez();
    a.Juega();
  }
}