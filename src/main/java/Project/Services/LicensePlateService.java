package Project.Services;

import Project.Dao.LicensePlateDao;
import com.S63B.domain.Entities.LicensePlate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Service
@Transactional
public class LicensePlateService {
    private LicensePlateDao licensePlateDao;

    @Autowired
    public LicensePlateService(LicensePlateDao licensePlateDao) {
        this.licensePlateDao = licensePlateDao;
    }

    public LicensePlate findByLicense(String license) {
        return this.licensePlateDao.findByLicense(license);
    }

    public LicensePlate create(LicensePlate licensePlate) {
        return this.licensePlateDao.save(licensePlate);
    }
}
