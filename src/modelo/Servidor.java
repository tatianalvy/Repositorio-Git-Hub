/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    ServerSocket servidor;
    Socket cliente;
    Map<String, String> mapDic; //almacena el diccionario

    public Servidor() {
    }

    public Servidor(ServerSocket servidor, Socket cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    private void cargaDiccionario() { //se inicializa
        mapDic = new HashMap<>();
        mapDic.put("Angular", "Es un Framework");
        mapDic.put("Eclipse", "Plataforma de Software");
        mapDic.put("Meteor", "Es un Framework para aplicaciones web");
        mapDic.put("Netbeans", "Entorno de desarrollo");
    }

    public void iniciarSocketServidor(int puerto) { //gestionalas solicitudes,tomando um puerto
        try {
            cargaDiccionario();
            DataInputStream entrada;   //declaracion objetos
            DataOutputStream salida;

            System.out.println("Esperando conexión en el puerto: " + puerto);
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();

            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            // String clientedic;
            String clienteIng;
            double resultado;

            while (true) {
                clienteIng = entrada.readUTF(); // se lee palabra enviada por el cliente
                System.out.println("Cliente: " + clienteIng);

                if (clienteIng.equalsIgnoreCase("salida")) { // Si envía "salida", se cierra la conexión y se rompe el bucle
                    cliente.close();
                    break;
                }

                try {
                    double number = Double.parseDouble(clienteIng);
                    resultado = Math.pow(number, 2);
                    String respuesta = "El cuadrado de " + number + " es: " + resultado;
                    salida.writeUTF(respuesta); // Envía la respuesta al cliente
                } catch (NumberFormatException e) {
                    salida.writeUTF("No válido, ingrese un número.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();//
        }
    }

    public static void main(String[] args) {
        Servidor obj1 = new Servidor();
        obj1.iniciarSocketServidor(100);
    }
}
