package server;

import classes.Message;
import classes.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ReplyProcess extends Thread {
    private User user;
    private ArrayList<User> usersList;
    private boolean connected = true;

    public ReplyProcess(User user, ArrayList<User> usersList) {
        this.user = user;
        this.usersList = usersList;
    }
    
    private void sendMessage(Message msg, Socket socket) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(msg);
    }
    
    private Message receiveMessage(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (Message) ois.readObject();
    }
    
    @Override
    public void run() {
        while (connected) {
            try {
                // Receive message
                Message msg = receiveMessage(user.getSocket());
                
                // Send message
                for (User u: usersList) {
                    if (msg.getDest() == null || (msg.getDest().equals(u.getUsername()) || msg.getFrom().equals(u.getUsername())))
                        sendMessage(msg, u.getSocket());
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("[!] User disconnected: " + user.getUsername());
                
                // Close socket
                try {
                    user.getSocket().close();
                } catch (IOException ex1) {
                    System.out.println("[-] Socket not closed correctly");
                } finally {
                    usersList.remove(user);
                    connected = false;
                    System.out.println("[*] Connected users: " + Integer.toString(usersList.size()));
                }
            }
        }
    }
}
