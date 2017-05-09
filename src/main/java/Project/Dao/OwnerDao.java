package Project.Dao;

import com.S63B.domain.Entities.Owner;
import org.springframework.stereotype.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by Nino Vrijman on 9-5-2017.
 */
@Stateless
@Repository
public class OwnerDao implements IDaoFacade, IOwnerDao {
    private EntityManager em;

    public OwnerDao() {
        this.em = Persistence.createEntityManagerFactory("HibernatePersistenceUnit").createEntityManager();
    }

    @Override
    public void persist(Object o) {
        this.em.getTransaction().begin();
        this.em.persist(o);
        this.em.getTransaction().commit();
    }

    @Override
    public void remove(Object o) {
        this.em.getTransaction().begin();
        this.em.remove(o);
        this.em.getTransaction().commit();
    }

    @Override
    public void merge(Object o) {
        this.em.getTransaction().begin();
        this.em.merge(o);
        this.em.getTransaction().commit();
    }

    /**
     * Gets a owner (user) with a given id.
     *
     * @param id The id of the owner (user) which should be retrieved.
     * @return The owner (user) with the given id.
     */
    @Override
    public Owner getById(int id) {
        Owner owner = null;
        Query query = em.createQuery("SELECT o FROM Owner o WHERE o.id = :id").setParameter("id", id);
        try {
            owner = (Owner) query.getSingleResult();
            em.refresh(owner);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return owner;
    }

    /**
     * Updates a owners (user) address and residence information.
     *
     * @param id The id of the owner (user);
     * @param address The address of the owner (user);
     * @param residence The residence of the owner (user);
     * @return Whether the operation has succeeded.
     */
    @Override
    public boolean update(int id, String address, String residence) {
        Query query = em.createQuery("UPDATE Owner SET address = :address, residence = :residence WHERE id = :id").setParameter("address", address).setParameter("residence", residence).setParameter("id", id);
        boolean result = false;
        try {
            System.out.println(id + " " + address + " " + residence);
            em.getTransaction().begin();
            query.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.getTransaction().commit();
        }
        return result;
    }
}
