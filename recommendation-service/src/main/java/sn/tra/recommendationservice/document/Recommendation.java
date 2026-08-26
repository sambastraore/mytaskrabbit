package sn.tra.recommendationservice.document;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "recommendations")
public class Recommendation {

    @Id
    private String id;

    private Long userId;

    private List<Long> serviceIds;


}