package repositories;

import com.CryptoMarket.models.CryptoBriefInfo;
import com.CryptoMarket.models.quote.QuoteAggregate;
import com.CryptoMarket.models.quote.QuoteByDay;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CryptoBriefInfoRepository extends JpaRepository<CryptoBriefInfo,Integer> {

    @Query(value = "SELECT new com.CryptoMarket.models.quote.QuoteAggregate(DATE(q.date), AVG(q.price), MIN(q.price), MAX(q.price)) " +
            "FROM Quote q WHERE q.crypto.id = :id " +
            "GROUP BY DATE(q.date) ORDER BY DATE(q.date)", nativeQuery = true)
    List<QuoteAggregate> findByIdWithAllQuotes(@Param("id") int id);

    @Query(value = "SELECT new com.CryptoMarket.models.quote.QuoteAggregate(DATE(q.date), AVG(q.price), MIN(q.price), MAX(q.price)) " +
            "FROM Quote q WHERE q.crypto.id = :id AND q.date >= :startDate " +
            "GROUP BY DATE(q.date) ORDER BY DATE(q.date)", nativeQuery = true)
    List<QuoteAggregate> findByIdWithQuotesFromDate(@Param("id") int id, @Param("startDate") LocalDate startDate);

    @Query(value = "SELECT new com.CryptoMarket.models.quote.QuoteAggregate(DATE(q.date), AVG(q.price), MIN(q.price), MAX(q.price)) " +
            "FROM Quote q WHERE q.crypto.id = :id AND q.date BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(q.date) ORDER BY DATE(q.date)", nativeQuery = true)
    List<QuoteAggregate> findByIdWithQuotesBetweenDates(@Param("id") int id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT new com.CryptoMarket.models.quote.QuoteByDay(q.price,q.circulatingSupply,q.totalSupply,q.maxSupply " +
            "FROM Quote q WHERE FUNCTION('current_timestamp') >= q.date AND q.crypto.id = :id) " +
            "GROUP BY q.date ORDER BY q.date", nativeQuery = true)
    List<QuoteByDay> findByIdWithQuotesFromDay(@Param("id") int id);


}
