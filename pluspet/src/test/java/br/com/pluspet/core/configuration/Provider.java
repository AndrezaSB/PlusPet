package br.com.pluspet.core.configuration;

import org.hibernate.tool.schema.spi.SchemaFilter;
import org.hibernate.tool.schema.spi.SchemaFilterProvider;

public class Provider implements SchemaFilterProvider{

	@Override
	public SchemaFilter getCreateFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemaFilter getDropFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemaFilter getTruncatorFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemaFilter getMigrateFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemaFilter getValidateFilter() {
		// TODO Auto-generated method stub
		return null;
	}

}
