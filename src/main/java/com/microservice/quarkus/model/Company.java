package com.microservice.quarkus.model;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;

@MongoEntity(collection = "company")
public class Company extends ReactivePanacheMongoEntity {
	@NotBlank
	public String name;
	@NotBlank
	public String createdByUser;
	public boolean activated = true;
	public Instant createdDate = Instant.now();
	public String lastModifiedByUser;
	public Instant lastModifiedDate = Instant.now();

	public static Multi<Company> findActiveCompanies() {
		return stream("activated", true);
	}

	public static Multi<Company> findActiveCompaniesByUser(String user) {
		return stream("activated = ?1 and createdByUser = ?2", true, user);
	}
}
