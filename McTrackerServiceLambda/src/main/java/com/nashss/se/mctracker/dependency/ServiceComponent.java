package com.nashss.se.mctracker.dependency;

import com.nashss.se.mctracker.activity.CreateGameLogActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateGameLogActivity provideCreateGameLogActivity();
}
