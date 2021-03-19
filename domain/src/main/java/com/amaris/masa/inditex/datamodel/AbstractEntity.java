package com.amaris.masa.inditex.datamodel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity <T extends AbstractEntity> implements Serializable, Cloneable {

    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
