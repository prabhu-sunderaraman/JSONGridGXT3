package com.durasoft.client.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CountryProperties extends PropertyAccess<Country>{
	@Path("id")
	ModelKeyProvider<Country> key();

	ValueProvider<Country, String> name();
	ValueProvider<Country, String> capital();
}
