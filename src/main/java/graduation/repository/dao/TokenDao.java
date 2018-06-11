package graduation.repository.dao;

import graduation.model.orm.TokenEntity;
import graduation.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TokenDao {
    private final TokenRepository tokenRepository;

    public TokenDao(@Autowired TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public TokenEntity getByTokenId(String tokenId) {
        return tokenRepository.findFirstByTokenId(tokenId);
    }

    @Transactional
    public void delete(String tokenId) {
        tokenRepository.deleteByTokenId(tokenId);
    }

    public TokenEntity save(TokenEntity entity) {
        return tokenRepository.save(entity);
    }
}
