package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class HbmTracker implements AutoCloseable, Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void close() throws Exception {
        sf.getCurrentSession().close();
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        boolean res = false;
        try {
            session.beginTransaction();
            session.createQuery("""
                    update item  set name = :fName, created = :fCreated, where id = :fId
                    """)
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", Timestamp.valueOf(item.getCreated()))
                    .setParameter("fId", id);
            res = true;
            session.getTransaction().commit();
        } catch (Exception e) {
            res = false;
            session.getTransaction().rollback();
        }
        return res;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        boolean res = false;
        try {
            session.beginTransaction();
            session.createQuery("""
                    delete Item where id=:fId
                    """).setParameter("fId", id)
                    .executeUpdate();
            res = true;
            session.getTransaction().commit();
        } catch (Exception e) {
            res = false;
            session.getTransaction().rollback();
        }
        return res;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item", Item.class).list();
        session.getTransaction().commit();
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("from Item where as i where name = :fName", Item.class)
                .setParameter("fName", key);
        List<Item> items = query.getResultList();
        session.getTransaction().commit();
        return items;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        return item;
    }
}
