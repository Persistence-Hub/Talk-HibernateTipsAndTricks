package org.thoughts.on.java.multitenancy;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class MultiTenantConnectionProvider extends
		AbstractMultiTenantConnectionProvider {

	private static final long serialVersionUID = 1L;

	private Map<String, ConnectionProvider> conProvMap = new HashMap<String, ConnectionProvider>();
	
	public MultiTenantConnectionProvider() {
		conProvMap = new HashMap<String, ConnectionProvider>();
		
		Properties props = Environment.getProperties();
        String baseUrl = props.getProperty(Environment.URL);
        
        conProvMap.put("tenant1", createConnectionProvider("tenant1", props, baseUrl));
        conProvMap.put("tenant2", createConnectionProvider("tenant2", props, baseUrl));
	}
	
	private ConnectionProvider createConnectionProvider(String tenantId, Properties props, String baseUrl) {	
		props.put( Environment.URL, baseUrl.concat(tenantId));
        
		DriverManagerConnectionProviderImpl conProv = new DriverManagerConnectionProviderImpl();
		conProv.configure(props);
		return conProv;
	}
	
	@Override
	protected ConnectionProvider getAnyConnectionProvider() {
		return conProvMap.values().iterator().next();
	}

	@Override
	protected ConnectionProvider selectConnectionProvider(
			String tenantIdentifier) {
		return conProvMap.get(tenantIdentifier);
	}

}
