package com.sparta.hubservice.domain.repository;

import com.sparta.hubservice.domain.model.Hub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubRepository {
    Hub save(Hub hub);

    Page<Hub> findAll(Pageable pageable);
}
