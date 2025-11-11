package com.sparta.productservice.presentation.request;

import java.util.UUID;

import com.sparta.productservice.application.dto.CreateProductCommand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(

	@NotBlank(message = "상품명은 필수입니다,")
	@Size(max = 100, message = "상품명은 100자 이하여야 합니다.")
	String name,

	@NotNull(message = "업체 ID는 필수입니다.")
	UUID companyId,

	@NotNull(message = "허브 ID는 필수입니다.")
	UUID hubId,

	@NotNull(message = "초기 재고는 필수입니다.")
	@Min(value = 0, message = "초기 재고는 0 이상이어야 합니다.")
	Integer stock

) {
	public CreateProductCommand toCommand() {
		return CreateProductCommand.builder()
			.name(name)
			.companyId(companyId)
			.hubId(hubId)
			.stock(stock)
			.build();
	}
}