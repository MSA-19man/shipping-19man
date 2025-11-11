package com.sparta.userservice.domain.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(
		name = "role",
		nullable = false
	)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(
		name = "status",
		nullable = false
	)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	@Enumerated(EnumType.STRING)
	private ApprovalStatus status;

	@PrePersist
	public void prePersist() {
		if (this.status == null) {
			this.status = ApprovalStatus.PENDING;
		}
	}

	@Builder(access = AccessLevel.PRIVATE)
	public User(
		String username,
		String password,
		String name,
		UserRole role,
		ApprovalStatus status
	) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.status = status;
	}

	public static User of(
		String username,
		String encodedPassword,
		String name,
		UserRole role
	) {
		return User.builder()
			.username(username)
			.password(encodedPassword)
			.name(name)
			.role(role)
			.build();
	}

}
