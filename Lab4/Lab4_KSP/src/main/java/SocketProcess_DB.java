import entity.Stat;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SocketProcess_DB implements Runnable{

    private Socket socket;
    private InputStream inputStream;

    private In_Stat in_stat = new In_Stat();
    private Line line;

    ArrayList<Stat> statList;

    private boolean check_command = false;
    private boolean check_convert = false;

    SocketProcess_DB(Socket s) throws Throwable{
        this.socket = s;
        this.inputStream = s.getInputStream();
    }

    @Override
    public void run() {
        try {
            serverInputHeaders();
            serverResponce();
        }catch (Throwable throwable){
            System.out.println("Client disconnected!");
        }finally {
            try{
                socket.close();
            }catch (Throwable throwable){
                System.out.println("Client can not disconnected!");
            }
        }
    }

    private void serverResponce() throws Throwable{
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println("Access-Control-Allow-Origin: *");
        //
        output.println();

        if (check_command) {
            for (int i = 0; i < statList.size(); i++) {
                output.print(statList.get(i).getDate() + " ");
                output.print(statList.get(i).getDistance() + " ");
                output.print(statList.get(i).getTime() + " ");
            }

            check_command = false;
        }else{
            output.println(" ");
        }

        if (check_convert){
            output.println("" + line.getResult() + "");
        }

        output.flush();

        System.out.println("Client disconnected!");
    }

    private void serverInputHeaders() throws Throwable{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while (!bufferedReader.ready()) ;

        in_stat.setIn_line(bufferedReader.readLine());

        String[] line1 = in_stat.getIn_line().split(" ");
        String[] line2 = line1[1].split("/");

        if (line2[1].equals("load")){
            statList = in_stat.load_db();
            check_command = true;
        }

        String[] line3 = line2[1].split(",");

        if (line3[0].equals("delete")){
            in_stat.setDate(line3[1]);

            in_stat.delete_db(in_stat.getDate());
        }

        else if (line3[0].equals("insert")){
            in_stat.setDate(line3[1]);
            in_stat.setDistance(line3[2]);
            in_stat.setTime(line3[3]);

            in_stat.insert_db(in_stat.getDate(), in_stat.getDistance(), in_stat.getTime());
        }else if (line3[0].equals("update")){
            in_stat.setDate(line3[1]);
            in_stat.setDistance(line3[2]);
            in_stat.setTime(line3[3]);

            in_stat.update_db(in_stat.getDate(), in_stat.getDistance(), in_stat.getTime());
        }else if (line3[0].equals("convert")){
            line = new Line(line2[1]);

            line.setNumber_input(line3[1]);

            line.setIn_num_system(Integer.parseInt(line3[2]));
            line.setOut_num_system(Integer.parseInt(line3[3]));
            if (line.getIn_num_system() <= 10){
                line.setNumber(Integer.parseInt(line3[1]));
                line.setResult(line.converter1to10(line.number, line.in_num_system, line.out_num_system));
            }else{
                line.setNumberString(line3[1]);
                line.setResult(line.converter10to16(line.numberString, line.in_num_system, line.out_num_system));
            }
            check_convert = true;
        }
    }
}
