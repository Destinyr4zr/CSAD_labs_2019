import entity.Stat;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class In_Stat {
    public String date;
    public String distance;
    public String time;

    public String in_line;

    public In_Stat(String date, String distance, String time){
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public In_Stat(){

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

    public String getIn_line() {
        return in_line;
    }

    public void setIn_line(String in_line) {
        this.in_line = in_line;
    }

    public void insert_db(String date, String distance, String time){
        System.out.println("Maven + Hibernate + MySQL");

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Stat stat = new Stat();

        stat.setDate(date);
        stat.setDistance(distance);
        stat.setTime(time);

        session.save(stat);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete_db(String date){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String stringQuery = "delete from Stat" + " where date=:date";
            Query query = session.createQuery(stringQuery);
            query.setParameter("date", date);
            query.executeUpdate();

            transaction.commit();

            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public void update_db(String date, String distance, String time){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String stringQuery = "update Stat set date=:date, distance=:distance, time=:time" + " where date=:date";
            Query query = session.createQuery(stringQuery);
            query.setParameter("date", date);
            query.setParameter("distance", distance);
            query.setParameter("time", time);
            query.executeUpdate();

            transaction.commit();

            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Stat> load_db(){
        System.out.println("Maven + Hibernate + MySQL");

        Session session = HibernateUtil.getSessionFactory().openSession();

        Stat stat = null;
        ArrayList<Stat> stats = (ArrayList<Stat>) session.createCriteria(Stat.class).list();

        if (stats != null && stats.size() == 1){
            stat = stats.get(0);
        }

        session.close();

        return stats;
    }
}
