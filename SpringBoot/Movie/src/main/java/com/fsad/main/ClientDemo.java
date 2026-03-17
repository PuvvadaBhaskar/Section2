package com.fsad.main;

import com.fsad.entity.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo {

    public static void main(String[] args) {

        // Step 1: Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // Step 2: Open Session
        Session session = factory.openSession();

        // Step 3: Begin Transaction
        Transaction tx = session.beginTransaction();

        // 🔹 INSERT DATA
        Movie m1 = new Movie("RRR", "2022", "Hit");
        Movie m2 = new Movie("Pushpa", "2021", "Super Hit");

        session.save(m1);
        session.save(m2);

        System.out.println("Data Inserted Successfully!");

        // 🔹 UPDATE using HQL (positional parameters)
        String hql = "update Movie set name=?1, status=?2 where id=?3";

        Query query = session.createQuery(hql);
        query.setParameter(1, "KGF");
        query.setParameter(2, "Blockbuster");
        query.setParameter(3, 1);

        int result = query.executeUpdate();
        System.out.println("Rows Updated: " + result);

        // Step 4: Commit
        tx.commit();

        // Step 5: Close
        session.close();
        factory.close();
    }
}