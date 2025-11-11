package com.sparta.userservice.user.presentation.request;

import java.util.UUID;

import com.sparta.userservice.user.application.dto.SignupUserCommand;
import com.sparta.userservice.user.domain.model.UserRole;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupUserRequest(
	@NotBlank(message = "아이디를 입력해주세요.")
	@Size(min = 4, max = 10, message = "아이디는 최소 4자 이상, 10자 이하입니다.")
	@Pattern(
		regexp = "^[a-z0-9]{4,10}$",
		message = "아이디는 알파벳 소문자(a~z), 숫자(0~9)를 포함해야 합니다."
	)
	String username,
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하입니다.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
		message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다."
	)
	String password,
	@NotBlank(message = "이름을 입력해주세요.")
	String name,
	@NotNull(message = "을 입력해주세요.")
	UserRole role,
	UUID hubId,
	UUID companyId

) {
	@AssertTrue(message = "허브 관리자는 허브 ID를 입력해야합니다.")
	public boolean isValidHubManager() {
		if (role == UserRole.HUB_MANAGER) {
			return hubId != null;
		}
		return true;
	}

	@AssertTrue(message = "업체 담당자는 업체 ID를 입력해야합니다.")
	public boolean isValidSupplierManager() {
		if (role == UserRole.SUPPLIER_MANAGER) {
			return companyId != null;
		}
		return true;
	}

	public SignupUserCommand toCommand() {
		return SignupUserCommand.builder()
			.username(username)
			.password(password)
			.name(name)
			.role(role)
			.hubId(hubId)
			.companyId(companyId)
			.build();
	}
}
