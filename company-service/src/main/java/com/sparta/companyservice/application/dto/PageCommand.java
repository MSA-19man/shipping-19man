package com.sparta.companyservice.application.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Builder;

@Builder
public record PageCommand(
	Integer page,
	Integer size,
	String sort,
	String direction
) {
	public PageCommand {
		page = (page == null || page < 0) ? 0 : page;
		size = (size == null || size < 1) ? 10 : Math.min(size, 100);
		sort = (sort == null || sort.isBlank()) ? "createdAt" : sort;
		direction = (direction == null || direction.isBlank()) ? "DESC" : direction.toUpperCase();
	}

	public static PageCommand of(Integer page, Integer size, String sort, String direction) {
		return PageCommand.builder()
			.page(page)
			.size(size)
			.sort(sort)
			.direction(direction)
			.build();
	}

	public Pageable toPageable() {
		Sort.Direction sortDirection = Sort.Direction.fromString(direction);
		return PageRequest.of(page, size, Sort.by(sortDirection, sort));
	}
}
