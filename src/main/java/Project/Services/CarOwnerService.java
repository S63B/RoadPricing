package Project.Services;

import Project.Dao.CarOwnerDao;
import com.S63B.domain.Entities.Car_Ownership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Service
@Transactional
public class CarOwnerService {
    private CarOwnerDao carOwnerDao;

    @Autowired
    public CarOwnerService(CarOwnerDao carOwnerDao) {
        this.carOwnerDao = carOwnerDao;
    }

    public Car_Ownership create(Car_Ownership carOwnership) {
        return this.carOwnerDao.save(carOwnership);
    }
}
