package main.ru.mirea.KSP.Lab4_backend.Database;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Entity
@Table(name = "Pencil")
public class PencilsModel {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "length", nullable = false)
    private String length;

    public PencilsModel() { }

    public PencilsModel(int id, String date, String color, String length)
    {
        this.id = id;
        this.date = date;
        this.color = color;
        this.length = length;
    }

    /*public PencilsModel(String date, String color, String length)
    {
        this.date = date;
        this.color = color;
        this.length = length;
    }*/

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "models.Pencil{"+
                "id="+id+
                "color="+color+
                "date="+date+
                "length="+length+
                "}";
    }
}
