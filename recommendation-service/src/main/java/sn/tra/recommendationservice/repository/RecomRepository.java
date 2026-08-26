package sn.tra.recommendationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sn.tra.recommendationservice.document.Recommendation;

import java.util.Optional;

public interface RecomRepository extends MongoRepository<Recommendation, String> {
    Optional<Recommendation> findByUserId(Long userId);
}