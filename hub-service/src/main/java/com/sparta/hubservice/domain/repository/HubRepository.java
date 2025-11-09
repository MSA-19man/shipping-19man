package com.sparta.hubservice.domain.repository;

import com.sparta.hubservice.domain.model.Hub;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRepository {
    Hub save(Hub hub);
}
