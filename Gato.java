import java.util.Scanner;
public class Gato{
  //Atributos
  int[][] M;

  //Constructor
  public Gato(){
    M= new int[3][3];
    for (int i=0;i<3 ;i++ ) {
      for (int j=0;j<3 ;j++ ) {
        M[i][j]=0;
      }
    }
  }

  //metodos
  public void Visualiza(){
    String tablero="";
    for (int i=0;i<3 ;i++ ) {
      
      for (int j=0;j<3 ;j++ ) {
        if(M[i][j]==0){
          tablero+=" ";
        }else if(M[i][j]==1){
          tablero+="X";
        }else{
          tablero+="O";
        }
        if(j!=2){
          tablero+=" | ";
        }
      }
      if(i!=2){
        tablero+="\n__________\n";
      }
      tablero+="\n";
    }
    System.out.println(tablero);
  }

  public int Asigna(int i, int j, int jugador){
    if(Retorna(i,j)==0){
      M[i][j]= jugador;
      return 1;
    }else{
      System.out.println("Esa casilla ya esta ocupada");
      return 0;
    }

  }

  public int Retorna(int i, int j){
    return M[i][j];
  }

  public int RevisaGano(){
    for (int i=0;i<3;i++) {
      for(int j=0;j<3;j++){
        //horizontal
        if(M[i][0]==M[i][1] && M[i][0]==M[i][2]){
          return M[i][0];
        //vertical
        }else if(M[0][i]==M[1][i] && M[0][i]==M[2][i]){
          return M[0][i];
        }
      }
    }
    if(M[0][0]==M[1][1] && M[0][0]==M[2][2]){
      return M[0][0];
    }else if(M[2][0]==M[1][1] && M[2][0]==M[0][2]){
      return M[2][0];
    }else{
      boolean bandera=true;
      for (int i=0;i<3;i++) {
        for (int j=0;j<3;j++) {
          if(M[i][j]==0){
            bandera=false;
          }
        }
      }
      if(bandera){
        return 3;
      }else{
        return 0;
      }
    }

  }
   
  public void Juega(){
    Scanner read = new Scanner(System.in);
    Visualiza();
    System.out.println("¿Quieres tirar con 1 o con 2?");
    int jugador;
    do{
      jugador = read.nextInt();
    }while(jugador!=1 && jugador!=2);

    int gana=0,i,j,validar;
    System.out.println("****************INICIANDO EL JUEGO******************");
    do{
      System.out.println("Jugador "+jugador+" ingresa las coordenadas de donde quieras tirar");
      do{
        System.out.print("i:");
        i=read.nextInt();  
      }while(i<0 || i>2);
      do{
        System.out.print("j:");
        j=read.nextInt();  
      }while(j<0 || j>2);
      
      validar=Asigna(i,j,jugador);
      Visualiza();
      gana=RevisaGano();

      if(validar==1 && gana==0){
        jugador++;
        if(jugador>2){
          jugador=1;
        }
      }
    }while(gana==0);

    if(gana==3){
      System.out.println("¡¡¡¡Gato!!!!");
    }else{
      System.out.println("Ganó el jugador "+jugador);
    }
  }

  public static void main(String[] args) {
    Gato g = new Gato();
    g.Juega();
  }
}