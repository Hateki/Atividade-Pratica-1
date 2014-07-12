
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
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
public class ServidorMultirefa {

    private ServerSocket socketServidor;
    private Socket socketRecepcao;
    private int numeroClientes;

    public ServidorMultirefa() {
        numeroClientes = 1;
    }

    public void exibirMensagensCliente() {
        int contador = 1;
        Thread tratarRequisição;

        try {
            socketServidor = new ServerSocket(6000);
            while (true) {
                //System.out.println("Escutando os clientes na porta 6000...");
                socketRecepcao = socketServidor.accept();
                Locale locale = new Locale("pt", "BR");
                GregorianCalendar calendar = new GregorianCalendar();
                SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);
                String data = formatador.format(calendar.getTime());
                //System.out.println("Conexão estabelecida");
                tratarRequisição = new Thread(new TratadorTarefas(socketRecepcao,data));
                tratarRequisição.start();
            }
        } catch (IOException ex) {

        } finally {
            try {
                socketServidor.close();
            } catch (IOException ex) {

            }
        }
    }

    public void mostrarMensagem(Socket socketRecepcao,String dataChegada) throws IOException {
        BufferedReader doCliente = new BufferedReader(new InputStreamReader(socketRecepcao.getInputStream()));
        String mensagemCliente = doCliente.readLine();
        System.out.println(mensagemCliente);
        System.out.println("Chegada da mensagem : " + dataChegada  + "\n");
    }
    
    public static void main(String args[]) {
        ServidorMultirefa servidorMultirefa = new ServidorMultirefa();
        servidorMultirefa.exibirMensagensCliente();
    }

    private class TratadorTarefas implements Runnable {

        Socket socketCliente;
        String dataChegada;

        public TratadorTarefas(Socket socketCliente, String chegada) {
            this.socketCliente = socketCliente;
            dataChegada = chegada;
        }

        @Override
        public void run() {
            try {
                mostrarMensagem(socketCliente,dataChegada);
            } catch (IOException ex) {

            }
        }
    }
}
