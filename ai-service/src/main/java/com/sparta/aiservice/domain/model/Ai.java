package com.sparta.aiservice.domain.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "p_ai")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Ai extends BaseEntity {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@Column(nullable = false)
	private UUID orderId;

	@Column(name = "final_dispatch_deadline", nullable = false)
	private ZonedDateTime finalDispatchDeadline;

	@Column(name = "ai_summary", columnDefinition = "TEXT")
	private String aiSummary;

	@Column(name = "request_payload", columnDefinition = "json")
	private String requestPayload;

	@Column(name = "response_payload", columnDefinition = "json")
	private String responsePayload;
}
