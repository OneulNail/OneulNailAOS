package com.today.nail.service

import com.today.nail.service.ui.TopLevelViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TopLevelViewModelModule {
    @Provides
    @Singleton
    fun provideTopLevelViewModel(): TopLevelViewModel {
        return TopLevelViewModel()
    }
}



