package Project.Services;

import Project.Dao.TrackerDao;
import com.S63B.domain.Entities.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Nino Vrijman on 24-5-2017.
 */
@Service
@Transactional
public class TrackerService {
    private TrackerDao trackerDao;

    @Autowired
    public TrackerService(TrackerDao trackerDao) {
        this.trackerDao = trackerDao;
    }

    public Tracker create(Tracker tracker) {
        return this.trackerDao.save(tracker);
    }

    public Tracker findBySerialNumber(String serialNumber) {
        return this.trackerDao.findBySerialNumber(serialNumber);
    }
}
