package com.becoder.doa;

import com.becoder.entity.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmpDao {
    private SessionFactory sessionFactory;

    public EmpDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public boolean saveEmp(Emp emp){
        boolean f = false;
        Session session = sessionFactory.openSession();
       Transaction transaction =  session.beginTransaction();
      int i =(Integer) session.save(emp);
      if(i > 0){
          f = true;
      }
    transaction.commit();
    session.close();
        return  f;
    }
}
