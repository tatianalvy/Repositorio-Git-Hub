/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Asus
 */
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

            DataOutputStream salida;        //salida
            DataInputStream entrada;       //flujos de datos entrada datos
            //String numero ;               //almacena palabra ingresada usuario
            //String resultado;   //almacena res servidor                del
            String entradaUsuario;

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));//crear objeto para leer desde consola

            this.cliente = new Socket("127.0.0.1", puerto);

            salida = new DataOutputStream(this.cliente.getOutputStream());
            entrada = new DataInputStream(this.cliente.getInputStream());

            while (true) {
                System.out.print("Ingresa un número o 'salida' para finalizar): ");
                entradaUsuario = teclado.readLine();

                if (entradaUsuario.equalsIgnoreCase("salida")) {
                    salida.writeUTF("salida");
                    cliente.close();
                    break;
                }

                try {
                    double numero = Double.parseDouble(entradaUsuario);
                    salida.writeUTF(String.valueOf(numero));
                    String respuesta = entrada.readUTF();
                    System.out.println("SERVIDOR: " + respuesta); // Imprime la respuesta del servidor.
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un número válido.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }

    public static void main(String[] args) {
        Cliente obj1 = new Cliente(100);
        obj1.iniciarSocketCliente();
    }

}
