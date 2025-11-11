package com.sparta.userservice.domain.model;

import java.util.UUID;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_hub_manager")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HubManager extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID hubId;

	@OneToOne(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder(access = AccessLevel.PRIVATE)
	private HubManager(UUID hubId, User user) {
		this.hubId = hubId;
		this.user = user;
	}

	public static HubManager of(UUID hubId, User user) {
		return HubManager.builder()
			.hubId(hubId)
			.user(user)
			.build();
	}
}
