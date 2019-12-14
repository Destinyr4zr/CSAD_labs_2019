package main.ru.mirea.KSP.Lab4_backend.Database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PencilsDAO {

    private Session session;
    private List<PencilsModel> pencils;

    public PencilsDAO(Session session) {
        this.session = session;
    }

    public List<PencilsModel> findAll() throws HibernateException
    {
        try{
            pencils = createselectquery("id", true).getResultList();
        }catch (NoResultException ex){
            System.out.println("Нет карандашей");
            return null;
        }
        return pencils;
    }

    public  PencilsModel findbyID(int id) throws HibernateException
    {return session.get(PencilsModel.class, id); }

    public List<PencilsModel> findbyDate(String date) throws HibernateException
    {
        try{
            pencils = createselectquery("date",date).getResultList();
        }catch (NoResultException ex){
            System.out.println("Нет карандашей по данной дате");
            return null;
        }
        return pencils;
    }

    public  List<PencilsModel> findbyColor(String color) throws HibernateException
    {
        try{
            pencils = createselectquery("color",color).getResultList();
        }catch (NoResultException ex){
            System.out.println("Нет карандашей по данному цвету");
            return null;
        }
        return pencils;
    }

    public  List<PencilsModel> findbyLength(String length) throws HibernateException
    {
        try{
            pencils = createselectquery("length",length).getResultList();
        }catch (NoResultException ex){
            System.out.println("Нет карандашей по данной длине");
            return null;
        }
        return pencils;
    }

    public  void insertPencil(String date, String color, String length) throws HibernateException
    {session.save(new PencilsModel(date,color,length));session.flush();}

    public  void updatePencil(int id, String date, String color, String length) throws HibernateException
    {session.update(new PencilsModel(id, date,color,length));;session.flush();}

    public  void removePencil(int id) throws HibernateException
    {session.delete(findbyID(id));session.flush();}

    private  <T> Query <PencilsModel> createselectquery(String fieldname, T data)
    {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PencilsModel> cq = cb.createQuery(PencilsModel.class);
        Root<PencilsModel> root = cq.from(PencilsModel.class);
        cq.select(root);
        if (!fieldname.equals("id"))
        cq.where(cb.equal(root.get(fieldname), data));
        return session.createQuery(cq);
    }
}
