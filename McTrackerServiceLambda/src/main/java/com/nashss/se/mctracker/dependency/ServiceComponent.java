package com.nashss.se.mctracker.dependency;

import com.nashss.se.mctracker.activity.CreateGameLogActivity;
import com.nashss.se.mctracker.activity.DeleteGameLogActivity;
import com.nashss.se.mctracker.activity.GetAllGameLogsActivity;
import com.nashss.se.mctracker.activity.GetGameLogActivity;
import com.nashss.se.mctracker.activity.GetPlayerCharactersActivity;
import com.nashss.se.mctracker.activity.UpdateGameLogActivity;
import com.nashss.se.mctracker.activity.ViewAllStatsActivity;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * provideCreateGameLogActivity method.
     * @return CreateGameLogActivity
     */
    CreateGameLogActivity provideCreateGameLogActivity();

    /**
     * provideGetPlayerCharactersActivity method.
     * @return GetPlayerCharactersActivity
     */
    GetPlayerCharactersActivity provideGetPlayerCharactersActivity();

    /**
     * provideGetGameLogActivity method.
     * @return GetGameLogActivity
     */
    GetGameLogActivity provideGetGameLogActivity();

    /**
     * provideGetAllGameLogsActivity method.
     * @return GetAllGameLogsActivity
     */
    GetAllGameLogsActivity provideGetAllGameLogsActivity();

    /**
     * provideUpdateGameLogActivity method.
     * @return UpdateGameLogActivity
     */
    UpdateGameLogActivity provideUpdateGameLogActivity();

    /**
     * provideDeleteGameLogActivity method.
     * @return DeleteGameLogActivity
     */
    DeleteGameLogActivity provideDeleteGameLogActivity();

    /**
     * provideViewAllStatsActivity method.
     * @return ViewAllStatsActivity
     */
    ViewAllStatsActivity provideViewAllStatsActivity();
}
