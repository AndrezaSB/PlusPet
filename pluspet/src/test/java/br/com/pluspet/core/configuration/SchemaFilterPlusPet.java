package br.com.pluspet.core.configuration;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

public class SchemaFilterPlusPet implements SchemaFilter {

	public static final SchemaFilterPlusPet INSTANCE = new SchemaFilterPlusPet();

	@Override
	public boolean includeNamespace(Namespace namespace) {
		return true;
	}

	@Override
	public boolean includeTable(Table table) {
		return true;
	}

	@Override
	public boolean includeSequence(Sequence sequence) {
		return true;
	}

}
