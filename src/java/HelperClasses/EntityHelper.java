/*
 All Entity relate functions are here
 */
package HelperClasses;

import Hibernate.FactoryProvider;
import entities.HousePhotos;
import java.util.List;
import org.hibernate.Query;
//import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class EntityHelper {
      
    public static List getByQuery(String q){
        List resultListData;
        Session session=FactoryProvider.getFactory().openSession();
        
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Query query= session.createQuery(q);
        resultListData =query.list();
        session.close();
        return resultListData;
    }
    
    public static List getDataForpagination(String q,int start,int end){
        List resultListData;
        Session session=FactoryProvider.getFactory().openSession();
        Query query=(Query) session.createQuery(q);
        query.setFirstResult(start);
        query.setMaxResults(end); 
        resultListData =query.list();
        session.close();
         return resultListData;
    }
 
    public static boolean updateData(List data){
        boolean flag=false;
        try{
        Session session=FactoryProvider.getFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.update(data.get(0));
        tx.commit();
        session.close();
        flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public static boolean saveNewData(List data){
        System.out.println("Save house dataails  : "+data);
        boolean flag=false;
        Session session=FactoryProvider.getFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(data.get(0));
        tx.commit();
        session.close();
        return flag;
    }
    public static boolean saveNewPhotos(List<HousePhotos> data){
        System.out.println("Save house photots : "+data);
        try{
        boolean flag=false;
        Session session=FactoryProvider.getFactory().openSession();
        Transaction tx=session.beginTransaction();
        for(HousePhotos photo:data){
             session.save(photo);
        }
        tx.commit();
        session.close();
        }catch(Exception e){
            System.out.println("Data insertion error : "+e.getMessage());
        }
        return true;
    }
    public static void deleteData(List data){
        Session session=FactoryProvider.getFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.delete(data.get(0));
        tx.commit();
        session.close();
    }
}
