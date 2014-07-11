
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Cliente {
    
    public void enviarMensagem(){
        
        String frase;
        BufferedReader bf;
        DataOutputStream paraServidor;
        Socket socketCliente;
        
        System.out.println("Escreva a mensagem que deseja enviar para o servidor:");
        
        bf = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            socketCliente = new Socket("127.0.1", 6000);
            paraServidor = new DataOutputStream(socketCliente.getOutputStream());
            
            frase = bf.readLine();
            
            paraServidor.writeBytes(frase);
            
            System.out.println("Mensagem enviada com Sucesso");
            
            socketCliente.close();
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro na conexão, tente novamente");
        }
    }
    
    public static void main(String args[]){
        Cliente cliente = new Cliente();
        cliente.enviarMensagem();
    }
}
