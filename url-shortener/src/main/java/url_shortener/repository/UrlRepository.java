package url_shortener.repository;

import url_shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    // Short code se DB mein URL search karne ke liye
    Optional<Url> findByShortCode(String shortCode);

    // Ye check karega ki naya banaya gaya code pehle se DB mein toh nahi hai
    boolean existsByShortCode(String shortCode);

    // Jab koi link pe click karega toh uski count badhane ke liye custom query
    @Modifying
    @Query("UPDATE Url u SET u.clickCount = u.clickCount + 1 WHERE u.shortCode = :shortCode")
    void incrementClickCount(String shortCode);
}