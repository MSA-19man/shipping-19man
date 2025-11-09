package com.sparta.hubservice.application.service;

import com.sparta.hubservice.application.dto.CreateHubCommand;
import com.sparta.hubservice.application.dto.CreateHubResult;
import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubService {

    private final HubRepository hubRepository;

    @Transactional
    public CreateHubResult createHub(CreateHubCommand command){
        // Todo 허브 이름 중복 검사

        Hub newHub = Hub.of(
                command.name(),
                command.address(),
                command.latitude(),
                command.longitude()
        );

        Hub savedHub = hubRepository.save(newHub);
        log.info("허브 생성 완료 - hub id: "+savedHub.getId());

        return CreateHubResult.from(savedHub);
    }

}
