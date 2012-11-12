package com.durasoft.client.model;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;

public interface CountryJsonAutoBeanFactory extends AutoBeanFactory{
    AutoBean<CountryJson> jsonRootObject();
    AutoBean<ListLoadConfig> loadConfig();
}

