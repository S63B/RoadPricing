package Project.Dao;

import com.S63B.domain.Entities.Tracker;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nino Vrijman on 24-5-2017.
 */
public interface TrackerDao extends CrudRepository<Tracker, Integer> {
    Tracker findBySerialNumber(String serialNumber);
}
