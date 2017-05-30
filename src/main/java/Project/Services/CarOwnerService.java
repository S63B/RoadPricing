package Project.Services;

import Project.Dao.CarOwnerDao;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.Owner;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Service
@Transactional
public class CarOwnerService {
    private CarOwnerDao carOwnerDao;
    private CarService carService;

    @Autowired
    public CarOwnerService(CarOwnerDao carOwnerDao, CarService carService) {
        this.carOwnerDao = carOwnerDao;
        this.carService = carService;
    }

    public Car_Ownership create(Car_Ownership carOwnership) {
        return this.carOwnerDao.save(carOwnership);
    }

    public Car_Ownership getLatest(Car car) {
        Car_Ownership[] carOwnerships = Iterables.toArray(this.carOwnerDao.findAll(), Car_Ownership.class);
        return carOwnerships[carOwnerships.length - 1];
    }

    public List<Car> getCarsByOwner(Owner owner) {
        List<Car_Ownership> ownerships = carOwnerDao.getAllByOwner(owner);
        List<Car> cars = new ArrayList<>();

        for (Car_Ownership ownership : ownerships) {
            Car car = carService.getById(ownership.getCar().getId());
            cars.add(car);
        }

        return cars;
    }

    public Car_Ownership update(Car_Ownership ownership) {
        return this.carOwnerDao.save(ownership);
    }
}
