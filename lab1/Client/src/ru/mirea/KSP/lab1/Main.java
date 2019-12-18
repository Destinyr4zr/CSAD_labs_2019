package ru.mirea.KSP.lab1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream())) {
            System.out.println("Клиент подсоединился к сокету.");
            System.out.println("Все потоки ввода и вывода инициализированы.");
            while (!socket.isOutputShutdown()) {
                System.out.println("\nНажмите Enter для начала\nили введите quit для выхода");
                if (reader.readLine().equalsIgnoreCase("quit")) {
                    System.out.println("Клиент отсоединяется");
                    oos.writeUTF("quit");
                    Thread.sleep(1000);
                    break;
                }
                else
                {
                    oos.writeUTF("\n");
                    Thread.sleep(100);
                    System.out.println(ois.readUTF());
                    String clientCommand = reader.readLine();
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("\n\nОтправлено << " + clientCommand + " >> на сервер.\n\n");
                    System.out.println(ois.readUTF());
                    clientCommand = reader.readLine();
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("\n\nОтправлено << " + clientCommand + " >> на сервер.\n\n");
                    System.out.println(ois.readUTF());
                    Thread.sleep(2000);
                    String clientCommand2 = reader.readLine();
                    oos.writeUTF(clientCommand2);
                    oos.flush();
                    System.out.println("\n\nОтправлено << " + clientCommand2 + " >> на сервер.\n\n");
                    System.out.println("ожидание ответа...");
                    Thread.sleep(3000);
                    System.out.println(ois.readUTF());
                    System.out.println(ois.readUTF());
                    clientCommand2 = reader.readLine();
                }
            }
            reader.close();
            oos.close();
            ois.close();
            socket.close();
            System.out.println("Закрытие каналов и соединений на стороне клиента - УСПЕШНО.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
