package Project.Dao;

import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Repository
public interface CarOwnerDao extends CrudRepository<Car_Ownership, Integer> {
    List<Car_Ownership> getAllByOwner(Owner owner);
}