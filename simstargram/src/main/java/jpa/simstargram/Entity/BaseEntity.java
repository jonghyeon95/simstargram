package jpa.simstargram.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @PrePersist
    private void prePersist() {
        createdDate = LocalDateTime.now();
    }


}
