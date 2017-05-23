package Project.Services;

import Project.Dao.OwnerDao;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
@Service("ownerService")
public class OwnerService {
    private OwnerDao ownerDao;

    @Autowired
    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Owner getById(int id) {
        return ownerDao.findOne(id);
    }

    /**
     * Updates a owners (user).
     *
     * @param owner The owner that should be updated.
     * @return Whether the operation has succeeded.
     */
    public Owner update(Owner owner) {
        return ownerDao.save(owner);
    }
}
