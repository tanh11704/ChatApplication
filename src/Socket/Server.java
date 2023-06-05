package Socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1107);
        List<Socket> clients = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            clients.add(socket);

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while (true) {
                        String message = in.readLine(); 
                        if (message == null) {
                            break;
                        }
                        
                        for (Socket client : clients) {
                            if (client != socket) {
                                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                                out.println(message);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}



