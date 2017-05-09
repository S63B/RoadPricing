package Project.Dao;

import com.S63B.domain.Entities.Owner;

/**
 * Created by Nino Vrijman on 9-5-2017.
 */
public interface IOwnerDao {
    Owner getById(int id);

    boolean update(int id, String address, String residence);
}
