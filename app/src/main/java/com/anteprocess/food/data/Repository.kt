package com.anteprocess.food.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

// Survive the configuration changes

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource){
    val remote = remoteDataSource
    val local = localDataSource
}