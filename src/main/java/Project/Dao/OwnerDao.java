package Project.Dao;

import com.S63B.domain.Entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Repository
public interface OwnerDao extends CrudRepository<Owner, Integer> {
	Owner findByUsername(String username);
}
