package com.sparta.hubservice.application.dto;

public record CreateHubCommand (
        String name,
        String address,
        Double latitude,
        Double longitude
){
}
