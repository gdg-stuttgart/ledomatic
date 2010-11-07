package com.ledomatic.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector()
    {
        // Further modules are omitted...
        return Guice.createInjector(new RestModule());
    }
}