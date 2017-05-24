package Project.Services;

import Project.Dao.CarDao;
import Project.Dao.CarOwnerDao;
import Project.Dao.OwnerDao;
import com.S63B.domain.Entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Nino Vrijman on 16-5-2017.
 */
@Service
@Transactional
public class CarService {
    private CarDao carDao;

    @Autowired
    public CarService(CarDao carDao, CarOwnerDao ownershipDao, OwnerDao ownerDao) {
        this.carDao = carDao;
    }

    public Car getById(int id) {
        return carDao.findOne(id);
    }

    public Car create(Car newCar) {
        return this.carDao.save(newCar);
    }

    public Car update(Car updatedCar) {
        return this.carDao.save(updatedCar);
    }
}
