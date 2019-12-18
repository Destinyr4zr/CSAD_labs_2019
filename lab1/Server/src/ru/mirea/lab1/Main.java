package ru.mirea.lab1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {

    private String message;
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(12345, 1)){
            Socket client = server.accept();
            System.out.println("Соединение получено.");
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            System.out.println("Все потоки ввода и вывода инициализированы.");
            int inputradix, outputradix;
            while(!client.isClosed()){
                String entry = in.readUTF();
                if(entry.equalsIgnoreCase("quit")){
                    System.out.println("Клиент решил оборвать соединение ...");
                    out.writeUTF("Вы отключены");
                    out.flush();
                    break;
                }
                out.writeUTF("Введите исходную СС");
                out.flush();
                Thread.sleep(2000);
                inputradix = Integer.parseInt(in.readUTF());
                out.writeUTF("Введите конечную СС");
                out.flush();
                Thread.sleep(2000);
                outputradix = Integer.parseInt(in.readUTF());
                out.writeUTF("Введите числа разделяя их '.' ',' '|' ';'");
                out.flush();
                Thread.sleep(5000);
                 entry = in.readUTF();
                System.out.println("Исходная СС: " + inputradix+"\n Конечная СС: " + outputradix
                        + "\n Введённые числа: "+entry);
                try
                {
                    NumConverter object = new NumConverter(entry, inputradix, outputradix);
                    out.writeUTF("\nРезультат:");
                    out.writeUTF(object.getResult().toString());
                }
                catch (InputMismatchException e)
                {
                    out.writeUTF("Ошибка формата ввода!\n");
                    out.writeUTF(Arrays.toString(e.getStackTrace()));
                }
                catch (Exception e)
                {
                    out.writeUTF("Ошибка!\n");
                    out.writeUTF(e.getMessage());
                    out.writeUTF(Arrays.toString(e.getStackTrace()));
                }
                System.out.println("Сервер отправил сообщение клиенту.");
                out.flush();
            }
            System.out.println("Клиент отсоединился");
            in.close();
            out.close();
            client.close();
            System.out.println("Закрытие соединений и потоков - УСПЕШНО.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
