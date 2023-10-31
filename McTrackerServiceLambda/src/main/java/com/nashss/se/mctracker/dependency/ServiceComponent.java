package com.nashss.se.mctracker.dependency;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
//    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return CreatePlaylistActivity
//     */
//    CreatePlaylistActivity provideCreatePlaylistActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistActivity
//     */
//    GetPlaylistActivity provideGetPlaylistActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistActivity
//     */
//    SearchPlaylistsActivity provideSearchPlaylistsActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistSongsActivity
//     */
//    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return UpdatePlaylistActivity
//     */
//    UpdatePlaylistActivity provideUpdatePlaylistActivity();

}
