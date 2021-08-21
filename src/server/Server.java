package server;

import classes.Message;
import classes.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private int port;
    public ArrayList<User> usersList = new ArrayList();

    public Server(int port) {
        this.port = port;
    }
    
    private String receiveUsername(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message msg = (Message) ois.readObject();
        return msg.getMessage();
    }
    
    @Override
    public void run() {
        try {
            // Create server socket
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Running server on port " + port);
            
            while (true) {
                System.out.println("Waiting for connections...");
                
                // Accept connection
                Socket socket = ss.accept();
                
                // Receive username
                String username = receiveUsername(socket);
                
                // Add user
                User user = new User(socket, username);
                usersList.add(user);
                System.out.println("[+] User connected: " + username);
                System.out.println("[*] Connected users: " + Integer.toString(usersList.size()));
                
                // Create server process
                ReplyProcess replyProcess = new ReplyProcess(user, usersList);
                replyProcess.start();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("[-] Error while running server");
        }
    }
}
