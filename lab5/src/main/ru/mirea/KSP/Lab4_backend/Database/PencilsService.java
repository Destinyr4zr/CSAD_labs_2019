package main.ru.mirea.KSP.Lab4_backend.Database;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PencilsService {
    private final SessionFactory sessionFactory;

    private static final String DATE = "date";
    private static final String COLOR = "color";
    private static final String LENGTH = "length";
    private static final String ALL = "all";

    public PencilsService(){
        SessionFactoryHelper sessionfactory = new SessionFactoryHelper();
        sessionFactory = sessionfactory.createSessionFactory();
    }

    public PencilsModel getPencil (int id) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            PencilsDAO dao = new PencilsDAO(session);
            PencilsModel pencil = dao.findbyID(id);
            session.getTransaction().commit();
            session.close();
            return pencil;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public List<PencilsModel> getPencils (String type, String query) throws Exception {
        try {
            Session session = sessionFactory.openSession();

            PencilsDAO dao = new PencilsDAO(session);
            List<PencilsModel> pencils;
        switch (type)
        {
            case ALL:
                pencils = dao.findAll();
                break;
            case DATE:
                pencils = dao.findbyDate(query);
                break;
            case COLOR:
                pencils = dao.findbyColor(query);
                break;
            case LENGTH:
                pencils = dao.findbyLength(query);
                break;
            default:
                throw new Exception("Неизвестный тип колонки");
        }
            session.close();
            return pencils;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public int countPencils () throws Exception {
        return getPencils(ALL, "").size();
    }

    public void setPencil (int id, String date, String color, String length) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            PencilsDAO dao = new PencilsDAO(session);
            dao.updatePencil(id,date, color,length);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public void createPencil (int id, String date, String color, String length) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            PencilsDAO dao = new PencilsDAO(session);
            dao.insertPencil(id, date, color,length);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public void deletePencil (int id) throws Exception {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            PencilsDAO dao = new PencilsDAO(session);
            dao.removePencil(id); ;
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }
}
