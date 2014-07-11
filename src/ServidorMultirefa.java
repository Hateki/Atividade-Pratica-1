
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
public class ServidorMultirefa {
    public void ExibirMensagensCliente(){
        
        String mensagemCliente;
        ServerSocket socketServidor;
        Socket socketRecepcao;
        BufferedReader doCliente;
        int contador = 1;
        
        try {
            socketServidor = new ServerSocket(6000);
            while(true){
                System.out.println("Escutando os clientes...");
                socketRecepcao = socketServidor.accept();
                System.out.println("Conexão estabelecida");
                
                doCliente = new BufferedReader(new InputStreamReader(socketRecepcao.getInputStream()));
                
                mensagemCliente = doCliente.readLine();
                
                System.out.println("A mensagem do cliente "+ contador +" é:");
                System.out.println(mensagemCliente + "\n");
                contador++;
            }
        } catch (IOException ex) {
            
        }
        
        
    }
}
