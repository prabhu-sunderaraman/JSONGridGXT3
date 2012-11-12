package com.durasoft.client;

import java.util.ArrayList;
import java.util.List;

import com.durasoft.client.model.Country;
import com.durasoft.client.model.CountryJson;
import com.durasoft.client.model.CountryJsonAutoBeanFactory;
import com.durasoft.client.model.CountryJsonReader;
import com.durasoft.client.model.CountryProperties;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class SampleEntryPoint implements EntryPoint {
	private CountryProperties countryProps = GWT.create(CountryProperties.class);     
	private CountryJsonAutoBeanFactory factory;  
	private CountryJsonReader reader;

	@Override
	public void onModuleLoad() {
		factory = GWT.create(CountryJsonAutoBeanFactory.class);
		reader = new CountryJsonReader(factory, CountryJson.class);
		String path = "countries.txt";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, path);
		HttpProxy<ListLoadConfig> proxy = new HttpProxy<ListLoadConfig>(builder);
		final ListLoader<ListLoadConfig, ListLoadResult<Country>> loader = new ListLoader<ListLoadConfig, ListLoadResult<Country>>(
				proxy, reader);
		loader.useLoadConfig(factory.create(ListLoadConfig.class).as());
		loader.addLoadHandler(new LoadHandler<ListLoadConfig, ListLoadResult<Country>>(){
			@Override
			public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<Country>> event) {
				ListLoadResult<Country> countries = event.getLoadResult();	
				List<Country> countryList = countries.getData();
				ListStore<Country> store = new ListStore<Country>(countryProps.key());
				store.addAll(countryList);
				loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, Country, ListLoadResult<Country>>(store));
				ColumnModel<Country> colModel = new ColumnModel<Country>(getColumns());
				Grid<Country> countryGrid = new Grid<Country>(store, colModel);
				countryGrid.setWidth(200);
				RootPanel.get().add(countryGrid);
			}
		});
		loader.load();
	}
	private List<ColumnConfig<Country, ?>> getColumns(){
		ColumnConfig<Country, String> nameCol = new ColumnConfig<Country, String>(countryProps.name(),100,"Name");
		ColumnConfig<Country, String> capitalCol = new ColumnConfig<Country, String>(countryProps.capital(),100,"Capital");

		List<ColumnConfig<Country, ?>> columnsList = new ArrayList<ColumnConfig<Country,?>>();
		columnsList.add(nameCol);
		columnsList.add(capitalCol);
		return columnsList;
	}
}
