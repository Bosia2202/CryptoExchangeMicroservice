package com.cryptomarket.cryptoexchange.repositories;

import com.cryptomarket.cryptoexchange.models.CryptoBriefInfo;
import com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate;
import com.cryptomarket.cryptoexchange.models.quote.QuoteByDay;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CryptoBriefInfoRepository extends JpaRepository<CryptoBriefInfo, String> {

        @Query("SELECT new com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate(CAST(q.date AS LocalDate), AVG(q.price), MIN(q.price), MAX(q.price)) "
                        +
                        "FROM Quote q WHERE q.crypto.symbol = :symbol " +
                        "GROUP BY FUNCTION('date', q.date) ORDER BY FUNCTION('date', q.date) DESC")
        List<QuoteAggregate> findBySymbolWithAllQuotes(@Param("symbol") String symbol);

        @Query("SELECT new com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate(CAST(q.date AS LocalDate), AVG(q.price), MIN(q.price), MAX(q.price)) "
                        +
                        "FROM Quote q WHERE q.crypto.symbol = :symbol AND q.date >= :startDate " +
                        "GROUP BY FUNCTION('date', q.date) ORDER BY FUNCTION('date', q.date) DESC")
        List<QuoteAggregate> findBySymbolWithQuotesFromDate(@Param("symbol") String symbol,
                        @Param("startDate") LocalDateTime startDate);

        @Query("SELECT new com.cryptomarket.cryptoexchange.models.quote.QuoteAggregate(CAST(q.date AS LocalDate), AVG(q.price), MIN(q.price), MAX(q.price)) "
                        +
                        "FROM Quote q WHERE q.crypto.symbol = :symbol AND q.date BETWEEN :startDate AND :endDate " +
                        "GROUP BY FUNCTION('date', q.date) ORDER BY FUNCTION('date', q.date) DESC")
        List<QuoteAggregate> findBySymbolWithQuotesBetweenDates(@Param("symbol") String symbol,
                        @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

        @Query("SELECT new com.cryptomarket.cryptoexchange.models.quote.QuoteByDay(q.date,q.price, q.circulatingSupply, q.totalSupply, q.maxSupply) "
                        +
                        "FROM Quote q WHERE q.date >= CURRENT_DATE AND q.date < DATEADD(DAY, 1, CURRENT_DATE) AND q.crypto.symbol = :symbol "
                        +
                        "ORDER BY q.date DESC")
        List<QuoteByDay> findBySymbolWithQuotesForDay(@Param("symbol") String symbol);

        Optional<CryptoBriefInfo> findBySymbol(String symbol);

        boolean existsByName(String name);

        void deleteByName(String name);

}