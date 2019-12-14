import entity.Stat;
import org.hibernate.Session;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Throwable{
        SocketProcess_DB socketProcess_db;

        ServerSocket serverSocket_db = new ServerSocket(8008);
        System.out.println("Server start!");

        while (true){
            Socket socket_db = serverSocket_db.accept();

            System.out.println("Client connect!");

            socketProcess_db = new SocketProcess_DB(socket_db);

            new Thread(socketProcess_db).start();
        }
    }
}
