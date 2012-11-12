package com.durasoft.client.model;

import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

public class CountryJsonReader extends JsonReader<ListLoadResult<Country>, CountryJson> {

	public CountryJsonReader(AutoBeanFactory factory,
			Class<CountryJson> rootBeanType) {
		super(factory, rootBeanType);
	}

	@Override
	protected ListLoadResult<Country> createReturnData(Object loadConfig,
			CountryJson records) {
		return new ListLoadResultBean<Country>(records.getCountries());
	}
	
}