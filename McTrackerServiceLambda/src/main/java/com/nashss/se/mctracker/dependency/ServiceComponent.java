package com.nashss.se.mctracker.dependency;

import com.nashss.se.mctracker.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    CreateGameLogActivity provideCreateGameLogActivity();
    GetPlayerCharactersActivity provideGetPlayerCharactersActivity();
    GetGameLogActivity provideGetGameLogActivity();
    GetAllGameLogsActivity provideGetAllGameLogsActivity();
    UpdateGameLogActivity provideUpdateGameLogActivity();
    DeleteGameLogActivity provideDeleteGameLogActivity();
}
