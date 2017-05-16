package Project.Services;

import Project.Dao.IOwnerDao;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
@Service("ownerService")
public class OwnerService {
    @Autowired
    private IOwnerDao ownerDao;

    public Owner getById(int id) {
        return ownerDao.getById(id);
    }

    /**
     * Updates a owners (user) address and residence information.
     *
     * @param id The id of the owner (user);
     * @param address The address of the owner (user);
     * @param residence The residence of the owner (user);
     * @return Whether the operation has succeeded.
     */
    public boolean update(int id, String address, String residence) {
        return ownerDao.update(id, address, residence);
    }
}
