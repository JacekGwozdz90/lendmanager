package lendmanager.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends MongoRepository<Account, String> {

    public Account findByUserName(String userName);

}