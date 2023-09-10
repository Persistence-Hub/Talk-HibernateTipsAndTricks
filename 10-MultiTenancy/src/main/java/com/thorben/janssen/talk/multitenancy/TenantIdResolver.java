package com.thorben.janssen.talk.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {

	private String tenantIdentifier;

	@Override
	public String resolveCurrentTenantIdentifier() {
		return tenantIdentifier;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	public void setTenantIdentifier(String tenantIdentifier) {
		this.tenantIdentifier = tenantIdentifier;
	}
}