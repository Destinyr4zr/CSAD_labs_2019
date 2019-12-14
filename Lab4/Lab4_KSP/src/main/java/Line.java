
public class Line {
    public int number;
    public String numberString;
    public int in_num_system;
    public int out_num_system;
    public String result;
    public String in_line;
    public String number_input;
    String res;

    public Line(String in_line) {
        this.in_line = in_line;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberString() {
        return numberString;
    }

    public int getIn_num_system() {
        return in_num_system;
    }

    public int getOut_num_system() {
        return out_num_system;
    }

    public String getIn_line() {
        return in_line;
    }

    public String getResult() {
        return result;
    }

    public String getNumber_input() {
        return number_input;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberString(String numberString) {
        this.numberString = numberString;
    }

    public void setIn_num_system(int in_num_system) {
        this.in_num_system = in_num_system;
    }

    public void setOut_num_system(int out_num_system) {
        this.out_num_system = out_num_system;
    }

    public void setIn_line(String in_line) {
        this.in_line = in_line;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setNumber_input(String number_input) {
        this.number_input = number_input;
    }

    public String converter1to10(int number, int in_num_system, int out_num_system) {
        int res_d = 0;
        int count;
        String n = Integer.toString(number);
        char[] arr = n.toCharArray();

        count = arr.length - 1;

        for (int i = 0; i < arr.length; i++){
            number = Character.getNumericValue(arr[i]);
            res_d = (int) (res_d + number * Math.pow(in_num_system, count));
            count--;
        }

        res = Integer.toString(res_d, out_num_system);

        return res;
    }

    public String converter10to16(String number, int in_num_system, int out_num_system) {
        int res_d = Integer.parseInt(number, in_num_system);
        res = Integer.toString(res_d, out_num_system);
        return res;
    }
}
