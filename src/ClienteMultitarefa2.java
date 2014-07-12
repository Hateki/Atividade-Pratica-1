
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pontofrio
 */
public class ClienteMultitarefa2 {

    public void enviarMensagem() {

        String frase;
        BufferedReader bf;
        DataOutputStream paraServidor;
        Socket socketCliente;
        int contador = 1;

        try {
            while (true) {
                socketCliente = new Socket("127.0.1", 6000);
                paraServidor = new DataOutputStream(socketCliente.getOutputStream());
                
                frase ="Cliente 2: " + "Mensagem " + contador;
                contador++;

                paraServidor.writeBytes(frase);

                System.out.println("Mensagem enviada com Sucesso");
                System.out.println(frase);
                
                socketCliente.close();
            }
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro na conexão, tente novamente");
        }
    }

    public static void main(String args[]) {
        ClienteMultitarefa2 clienteMultiTarefa = new ClienteMultitarefa2();
        clienteMultiTarefa.enviarMensagem();
    }
}
