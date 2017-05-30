package Project.Dao;

import com.S63B.domain.Entities.LicensePlate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
public interface LicensePlateDao extends CrudRepository<LicensePlate, Integer> {
    LicensePlate findByLicense(String license);
}
