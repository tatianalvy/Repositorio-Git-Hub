/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    int puerto;
    Socket cliente;

    public Cliente(int puerto) {
        this.puerto = puerto;
    }

    public Cliente() {
    }

    public Cliente(int puerto, Socket cliente) {
        this.puerto = puerto;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public void iniciarSocketCliente() {
        try {

            DataOutputStream salida;
            DataInputStream entrada;
            String numero ;  
            String resultado;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));//crear objeto para leer desde consola

            this.cliente = new Socket("127.0.0.1", puerto);

            salida = new DataOutputStream(this.cliente.getOutputStream());
            entrada = new DataInputStream(this.cliente.getInputStream());
            
            
          
            while (true) {
                System.out.print("\nIngrese un numero para calcular o 'salida' para finalizar: ");
                numero = teclado.readLine(); 
                salida.writeUTF(numero); 

                if (numero.equalsIgnoreCase("salida")) { // Si ingresa salida se cierra la conexión y bucpe
                    System.out.println("\n ** CONEXION CERRADA *");
                    cliente.close();    ///cierre conexion
                    break;              
                }

                resultado = entrada.readUTF();         //
                System.out.println("SERVIDOR: " + resultado);//imprime respuesta
            }

        } catch (Exception e) {
            System.out.println("\n ** INGRESE UN NUMERO VALIDO *");
        }
    }

    public static void main(String[] args) {
        Cliente obj1 = new Cliente(100);
        obj1.iniciarSocketCliente();
    }
}

//no es conexion es cliente.permite captar informacion del computador... lo que ingresa usuario
//hacer que el bufer reciba por teclado como captar la informacion el el buffer reader
//del otro lado un while que este trabajando en el iniciar servidor, unaa vez que hya conexion debe haber intereccion con el teclado
                    //el output input,entrada,salida... generando un ciclo
