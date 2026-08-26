package sn.tra.recommendationservice.service;

import org.springframework.stereotype.Service;
import sn.tra.recommendationservice.document.Recommendation;
import sn.tra.recommendationservice.repository.RecomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecomService {
    private final RecomRepository recomRepository;

    public RecomService(RecomRepository recomRepository){
        this.recomRepository = recomRepository;
    }



    public Optional<Recommendation> getRecom(Long userId){
        return recomRepository.findByUserId(userId);
    }
}
