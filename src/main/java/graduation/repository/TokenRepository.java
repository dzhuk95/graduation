package graduation.repository;

import graduation.model.orm.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    TokenEntity findFirstByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);
}
