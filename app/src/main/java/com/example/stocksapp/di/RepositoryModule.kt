package com.example.stocksapp.di

import com.example.stocksapp.data.StockRepository
import com.example.stocksapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindsRepository(stockRepository: StockRepository): Repository
}