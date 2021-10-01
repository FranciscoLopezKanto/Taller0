package taller;

import java.io.*;
import java.util.Scanner;

import ucn.*;

public class Taller01 {
	/**
	 * look for the index of the string in the list
	 * @param texto the string that will be compared with what is contained in the list
	 * @param lista the list that will contain the string to compare
	 * @param longitudLista the size of the listing
	 * @return return the position of the string within the list
	 * */
	public static int Index(String texto, String lista[], int longitudLista){
	
		    int index=-1;    
		    for (int i=0;i<longitudLista;i++){
		        if(texto.equals(lista[i])){
		            index=i;
		        }
		    }
			return index;
		}
	/**
	 * assigns the occupation to the movie theater with the position in the matrix
	 * */
	public static void AgregarAsiento(int Fila,int columna,int[][] sala) {
		sala[Fila][columna]+=1;
	}
	/**
	 * Showcase the movie theater with its available seats
	 * */
	public static void MostrarSala(int[][]Sala) {
		
		
        
        String texto="";
        for(int f=1;f<31;f++) {
            if(f>9) {
                texto+=" "+ f;
            }
            else {
                texto+="  "+ f;
            }

        }
        
        System.out.println(texto);
        for (int i = 0; i < 10; i++) {
            System.out.print(((char)(65+i)));
            for (int j = 0; j < 30; j++) {

            System.out.print(" "+Sala[i][j]+" ");
            }
            System.out.println("");
       }
        System.out.print("0=DISPONIBLE , 1= OCUPADO , 2= NO DISPONIBLE");
    }
	/**
	 * Read the Status.txt file and collect the information to be worked on later
	 * */
	public static void LecturaStatus(String[] Rut, String[] Pase, int[] Monto, int[] Funciones, String[] Horarios, String[] Pelicula, String [] Estado) throws IOException{
		ArchivoEntrada archivo2 = new ArchivoEntrada("Status.txt");  
		while(!archivo2.isEndFile()){
		    Registro registro = archivo2.getRegistro();
		    String rut = registro.getString();
		    String pase = registro.getString();
		    
		    for(int i=0; i<Rut.length;i++) {
		    	if(rut.equals(Rut[i])) {
		    		Pase[i]=pase;
		    	}
		    	else {
		    		return;
		    	}	
		    }
		}
	}
	/**
	 * Read the personas.txt file and collect the information to be worked on later
	 * */
	public static int LecturaPersonas(String[] Nombre, String[] Apellido, String [] Rut, String[] Contra, int[] Saldo) throws IOException{
		ArchivoEntrada archivo = new ArchivoEntrada("Personas.txt"); 
		int cont=0;
		while(!archivo.isEndFile()){
			
		    Registro registro = archivo.getRegistro();
		    String nombre = registro.getString();
		    String apellido = registro.getString();
		    String rut = registro.getString();
		    String contra = registro.getString();
		    int saldo = registro.getInt();

		    Nombre[cont]=nombre;
		    Apellido[cont]=apellido;
		    Rut[cont]=rut;
		    Contra[cont]=contra;
		    Saldo[cont]=saldo;
		    cont++;
		}
		return cont;
		
	}
	/**
	 * Read the file peliculas.txt and collect the information to be worked on later
	 * */
	public static int LecturaPeliculas(String[] Pelicula, String[] Estado, int[] Monto, int[] Funciones, String[]Horarios) throws IOException {
    	File archivo3 = new File("src/Peliculas.txt"); 
        FileReader texto = new FileReader(archivo3);
        BufferedReader Reader = new BufferedReader(texto);
        String linea;
        String[] partes;
        
        int contador = 0;
        while((linea = Reader.readLine())!=null){
        	partes = linea.split(",");
            Pelicula[contador]=partes[0];
            Estado[contador]=partes[1];
            Monto[contador]=Integer.parseInt(partes[2]);
            for(int i=3;i<partes.length;i+=2) {
            	Funciones[contador]=Integer.parseInt(partes[i]);
            	Horarios[contador]=partes[i+1];
            	
            }
            contador++;
        
        
        }
        Reader.close();
        return contador;
        
    }
	/**
	 * Create the movie theaters for the requested time
	 * */
	public static void CrearSala(int [][] Sala) {
   	 
        for (int i = 0; i < Sala.length; i++) {
            for (int j = 0; j < 30; j++) {
                if(i<=3 && j<=4) {
                    Sala[i][j]=2;
                }
                else if(i<=3 && j>=25) {
                    Sala[i][j]=2;
                }

                else {
                    Sala[i][j]=0;
                }
            }
        }
   }
	/**
	 * buy the ticket of the requested movie
	 * */
	public static void ComprarEntrada(String Opcion, int[][] Sala) {
			
	}
	/**
	 * check if the value is in the list to consult
	 * */
	public static boolean NotInList(String texto, String lista[], int longitudLista){ //returns true if the String is not in the array 
	    boolean x= true;
	        for (int i=0;i<longitudLista;i++){
	            if(texto.equals(lista[i])){
	                x=false;
	                break;
	                }
	            }
	    return x;
	}

    public static void main(String[] args) throws IOException {
    	try (Scanner entrada = new Scanner(System.in)) {
			int cont = 0;
			int [][] Sala1M =new int [10][30];
			int [][] Sala1T =new int [10][30];
			int [][] Sala2M =new int [10][30];
			int [][] Sala2T =new int [10][30];
			int [][] Sala3M =new int [10][30];
			int [][] Sala3T =new int [10][30];
			String [][] Horario = new String [3][2];
			//Clientes.txt
			String[] Rut = new String[999];
			String[] Contra = new String[999];
			String[] Nombre = new String[999];
			String[] Apellido = new String[999];
			int[] Saldo = new int[999];
			int precio=0;
			//Status.txt
			String[] Pase = new String[999];
			//Peliculas.txt
			String[] Pelicula = new String[999];
			String[] Estado = new String[999];
			int [] Monto = new int[999];
			int[] Funciones = new int [99];
			String[]Horarios = new String[99];
			
			String []guardarpelicula = new String [Pelicula.length];
			String []guardarHorario = new String [Horario.length];
			int []guardarfila = new int [999];
			int []guardarcolumna = new int [999];
			
			
			int contPelis = LecturaPeliculas(Pelicula,Estado,Monto,Funciones,Horarios);
			LecturaStatus(Rut,Pase,Monto,Funciones,Horarios,Pelicula,Estado);
			cont=LecturaPersonas(Nombre,Apellido,Rut,Contra,Saldo);
			CrearSala(Sala1M);
			CrearSala(Sala1T);
			CrearSala(Sala2M);
			CrearSala(Sala2T);
			CrearSala(Sala3M);
			CrearSala(Sala3T);
			
			System.out.println("Ingrese su Rut");
			String usuario= entrada.nextLine();
			boolean Stay = NotInList(usuario,Rut,cont);
			while(Stay!=true){
			    System.out.println("Rut no encontrado");
			    usuario= entrada.nextLine();
			    }
			int pos = Index(usuario,Rut,cont);
			System.out.println("Ingrese su contraseña");
			String Password= entrada.nextLine();
			String PasswordVerifique=Contra[pos];
			while(!Password.equals(PasswordVerifique)){
			    System.out.println("Contraseña incorrecta");
			    
			    Password= entrada.nextLine();
				}
			
			//menu usuario
			if(Stay==true && usuario!="ADMIN") {
				System.out.println("--------MENU---------");
				System.out.println("1) Comprar Entrada");
				System.out.println("2) Informacion del usuario");
				System.out.println("3) Devolucion Entrada");
				System.out.println("4) Cartelera");
				System.out.println("5) Salir");
				System.out.println("Ingrese Opcion(1,2,3,4): ");
				String opcion = entrada.nextLine();
				
				if(opcion=="1") {
					
					System.out.println("Ingrese pelicula");
					String peli = entrada.nextLine();
					for(int i = 0; i<Pelicula.length;i++) {
						if(peli.equals(Pelicula[i])) {
							if(Estado[i].equals("estreno")) {
								precio = 5500;
							}
							if(Estado[i].equals("liberada")) {
								precio = 4000;
							}
							System.out.println("Horarios(M= Mañana),(T= Tarde)"+ Horario[i]);
							System.out.println("Salas"+ Funciones[i]);
							System.out.println("Ingrese Horario");
							String horario = entrada.nextLine();
							System.out.println("Ingrese Sala");
							String sala = entrada.nextLine();
							if(horario=="M" && sala =="1") {
								MostrarSala(Sala1M);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											Saldo[pos]-=precio*0.15;
											AgregarAsiento(f,c,Sala1M);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									
									else {
										System.out.println("Saldo Insuficiente");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
								
							}
							else if(horario=="T" && sala =="1") {
								MostrarSala(Sala1T);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											Saldo[pos]-=precio*0.15;
											AgregarAsiento(f,c,Sala1T);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									else {
										System.out.println("Saldo Insuficiente ");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
							}
							else if(horario=="M" && sala =="2") {
								MostrarSala(Sala2M);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											Saldo[pos]-=precio*0.15;
											AgregarAsiento(f,c,Sala2M);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									else {
										System.out.println("Saldo Insuficiente ");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
							}
							else if(horario=="T" && sala =="2") {
								MostrarSala(Sala2T);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											Saldo[pos]-=precio*0.15;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									else {
										System.out.println("Saldo Insuficiente");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
							}
							else if(horario=="M" && sala =="3") {
								MostrarSala(Sala3M);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											Saldo[pos]-=precio*0.15;
											AgregarAsiento(f,c,Sala3M);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									else {
										System.out.println("Saldo Insuficiente");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
							}
							else if(horario=="T" && sala =="3") {
								MostrarSala(Sala3T);
								System.out.println("Ingrese Numero de Fila");
								int f = entrada.nextInt();
								System.out.println("Ingrese Numero de Columna");
								int c = entrada.nextInt();
								
								System.out.println("Precio a pagar:"+precio);
								System.out.println("Desea Realizar la compra? Si(Y),No(N): ");
								String afirmacion = entrada.nextLine();
								if(afirmacion=="Y" ) {
									if(Saldo[pos]>precio) {
										if(Pase[pos].equals("HABILITADO")) {
											precio = (int)(precio*0.15);
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala3T);
											System.out.println("Compra Exitosa");
										}
										else {
											Saldo[pos]-=precio;
											AgregarAsiento(f,c,Sala2T);
											System.out.println("Compra Exitosa");
										}
										guardarpelicula[pos]=peli;
										guardarHorario[pos]=horario;
										guardarfila[pos]=f;
										guardarcolumna[pos]=c;
									}
									else {
										System.out.println("Saldo Insuficiente");
									}

								}
								else if(afirmacion=="N") {
									return;
								}
								else {
									System.out.println("Respuesta mal ingresada :P");
								}
							}
							
						}
					
					}
					//ComprarEntrada();
				}
				else if(opcion=="2") {
					System.out.println("------INFORMACION DEL CLIENTE------");
					System.out.print("Rut:"+Rut[pos]+"Nombre:"+Nombre[pos]+"Apellido:"+Apellido[pos]+"Saldo"+Saldo[pos]+"Entradas Compradas:"+"Peliculas:"+guardarpelicula[pos]+"Horario"+guardarHorario[pos]);
					System.out.print("Asiento(fila)(Columna):"+guardarfila[pos]+guardarcolumna[pos]);
					//InfoUsuario();
				}
				else if(opcion=="3") {
					System.out.print("Entradas de :" +Nombre[pos]+" "+Apellido[pos]);
					System.out.print("Peliculas:"+guardarpelicula[pos]);
					//DevolucionEntrada();
					System.out.print("ingresa la pelicula a reembolsar");
					String PeliRembolso = entrada.nextLine();
					if (PeliRembolso.equals(guardarpelicula[pos])){
						Saldo[pos]+=precio*0.8;
						Sala2M[guardarfila[pos]][guardarcolumna[pos]]=0; //libera el asiento usado
						
						System.out.println("entrada reembolsada");
					}					
				}
				else if(opcion=="4") {
					System.out.println("-------------CARTELERA-------------");
					for(int i=0;i<Pelicula.length;i++) {
						
						System.out.println("Pelicula:"+Pelicula[i]+"Horario"+Horarios[i]);
					}
					}
				else if(opcion=="5") {
					System.exit(0);	
					//MostrarCartelera();	
				}
				else {
					System.out.println("Opcion no valida");
				}
				
				}
			else if(usuario.equals("ADMIN") && Password.equals("ADMIN")) {
				
			
				//menu admin
				System.out.println("--------MENU---------");
				System.out.println("1) Taquilla");
				System.out.println("2) Informacion cliente");
				System.out.println("3) Salir");
				System.out.println("Ingrese Opcion(1,2,3,): ");
				String opcion = entrada.nextLine();
				if(opcion=="1") {
					for(int b=0;b<contPelis-1;b++) {
						System.out.println(Pelicula[contPelis]+"recaudo un total de :"+Monto[contPelis]+"$");
					}
					
				}
				
				if(opcion=="2") {
					System.out.println("Ingrese el Rut del cliente");
					usuario= entrada.nextLine();
					boolean Stay1 = NotInList(usuario,Rut,cont);
					while(Stay1!=true){
					    System.out.println("Rut no encontrado");
					    usuario= entrada.nextLine();
					}
					pos = Index(usuario,Rut,cont);
					System.out.println("------INFORMACION DEL CLIENTE------");
					System.out.print("Nombre:"+Nombre[pos]+"Apellido:"+Apellido[pos]+"Saldo"+Saldo[pos]+"Entradas Compradas:"+"Peliculas:"+guardarpelicula[pos]+"Horario"+guardarHorario[pos]);
					System.out.print("Asiento(fila)(Columna):"+guardarfila[pos]+guardarcolumna[pos]);
					//InfoUsuarioadmin();
				}
				else if(opcion=="3") {
					System.exit(0);
				}
    		}
    	}

}
}