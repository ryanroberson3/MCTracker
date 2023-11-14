package com.nashss.se.mctracker.dependency;

import com.nashss.se.mctracker.activity.CreateGameLogActivity;
import com.nashss.se.mctracker.activity.GetGameLogActivity;
import com.nashss.se.mctracker.activity.GetPlayerCharactersActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateGameLogActivity provideCreateGameLogActivity();
    GetPlayerCharactersActivity provideGetPlayerCharactersActivity();
    GetGameLogActivity provideGetGameLogActivity();

}
