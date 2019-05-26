package com.lazyassevs.data.repository

import com.lazyassevs.domain.model.Album
import com.lazyassevs.domain.repository.Repository
import com.lazyassevs.domain.source.LocalSource
import com.lazyassevs.domain.source.RemoteSource
import javax.inject.Inject

class AlbumListRepository @Inject constructor(
    localSource: LocalSource<Long, List<Album>>,
    remoteSource: RemoteSource<Long, List<Album>>
) : Repository<Long, List<Album>>(localSource, remoteSource)