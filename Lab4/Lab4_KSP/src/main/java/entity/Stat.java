package entity;

import java.io.Serializable;

public class Stat implements Serializable {
    private static final long serialVersionSID = 1L;

    private Integer id;
    private String date;
    private String distance;
    private String time;

    public Stat(String date, String distance, String time){
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public Stat(){

    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer statID) {
        this.id = statID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
