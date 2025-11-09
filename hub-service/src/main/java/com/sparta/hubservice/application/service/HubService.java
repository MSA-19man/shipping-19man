package com.sparta.hubservice.application.service;

import com.sparta.hubservice.application.dto.CreateHubCommand;
import com.sparta.hubservice.application.dto.CreateHubResult;
import com.sparta.hubservice.application.dto.FindHubResult;
import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.repository.HubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
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

    public Page<FindHubResult> getHubs(Pageable pageable){
        Page<Hub> hubPage = hubRepository.findAll(pageable);

        // Page<Hub>(엔티티)를 Page<FindHubResult>(DTO)로 변환
        return hubPage.map(FindHubResult::from);
    }

}
