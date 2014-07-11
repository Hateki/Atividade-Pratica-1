
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pontofrio
 */
public class ServidorMultirefa implements Runnable {

    BufferedReader doCliente;
    String mensagemCliente;
    ServerSocket socketServidor;
    Socket socketRecepcao;
    int numeroClientes;

    public ServidorMultirefa() {
        numeroClientes = 1;
    }

    public void exibirMensagensCliente() {
        int contador = 1;
        Thread tratarRequisição;

        try {
            socketServidor = new ServerSocket(6000);
            while (true) {
                System.out.println("Escutando os clientes...");
                socketRecepcao = socketServidor.accept();
                System.out.println("Conexão estabelecida");
                doCliente = new BufferedReader(new InputStreamReader(socketRecepcao.getInputStream()));
                mensagemCliente = doCliente.readLine();
                tratarRequisição = new Thread(this);
                tratarRequisição.start();
            }
        } catch (IOException ex) {

        }
    }

    public void mostrarMensagem() throws IOException {
        System.out.println("");
        System.out.println(mensagemCliente + "\n");
    }

    @Override
    public void run() {
        try {
            mostrarMensagem();
        } catch (IOException ex) {

        }
    }

    public static void main(String args[]) {
        ServidorMultirefa servidorMultirefa = new ServidorMultirefa();
        servidorMultirefa.exibirMensagensCliente();
    }
}
