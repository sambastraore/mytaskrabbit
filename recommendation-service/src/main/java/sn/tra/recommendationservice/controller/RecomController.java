package sn.tra.recommendationservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.tra.recommendationservice.document.Recommendation;
import sn.tra.recommendationservice.service.RecomService;

@RestController
@RequestMapping("/recommendations")
public class RecomController {
    private final RecomService recomService;


    public RecomController(RecomService recomService){
        this.recomService = recomService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Recommendation> getRecommendations(@PathVariable Long userId){
        return recomService.getRecom(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
