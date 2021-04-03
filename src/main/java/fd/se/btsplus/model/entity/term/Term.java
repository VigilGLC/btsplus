package fd.se.btsplus.model.entity.term;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Term {
    @Id
    private Long id;
    private String name;
}
