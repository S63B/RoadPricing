package Project.Dao;

import com.S63B.domain.Entities.Car;
import org.springframework.stereotype.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by Nino Vrijman on 16-5-2017.
 */
@Stateless
@Repository
public class CarDao implements IDaoFacade {
    private EntityManager em;

    public CarDao() {
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

    public Car create(Car newCar) {
        try {
            this.persist(newCar);
        } catch (Exception e) {
            e.printStackTrace();
            newCar = null;
        }
        return newCar;
    }

    /**
     * Gets a car with the given id.
     * @param id The id of the car which should be retrieved.
     * @return The car with the given id or null when no car could be found.
     */
    public Car getById(int id) {
        Car car = null;
        Query query = em.createNamedQuery("Car.getById", Car.class).setParameter("id", id);
        try {
            car = (Car) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return car;
    }

    /**
     * Gets a car with the given license plate.
     * @param licenseplate The license plate of the car which should be retrieved.
     * @return The car with the given license plate or null when no car could be found.
     */
    public Car getByLicensePlate(String licenseplate) {
        Car car = null;
        Query query = em.createNamedQuery("Car.getCar", Car.class).setParameter("licensePlate", licenseplate);
        try {
            car = (Car) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return car;
    }
}
