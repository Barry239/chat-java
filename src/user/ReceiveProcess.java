package user;

import chat.SelectEmoji;
import classes.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.DefaultListModel;

public class ReceiveProcess extends Thread {
    private Socket socket;
    private DefaultListModel model;

    public ReceiveProcess(Socket socket, DefaultListModel model) {
        this.socket = socket;
        this.model = model;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();
                SelectEmoji selectEmoji = new SelectEmoji();
                selectEmoji.select(msg, model);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("[-] Error while receiving message");
            }
        }
    }
}
