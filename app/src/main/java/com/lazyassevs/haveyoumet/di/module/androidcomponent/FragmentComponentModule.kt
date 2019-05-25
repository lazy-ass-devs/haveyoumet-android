package com.lazyassevs.haveyoumet.di.module.androidcomponent

import com.lazyassevs.haveyoumet.di.component.FragmentSubComponent
import com.lazyassevs.haveyoumet.ui.view.album.detail.AlbumDetailFragment
import com.lazyassevs.haveyoumet.ui.view.album.list.AlbumListFragment
import com.lazyassevs.haveyoumet.ui.view.authentication.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    subcomponents = [
        FragmentSubComponent::class
    ]
)
abstract class FragmentComponentModule {

    @ContributesAndroidInjector
    abstract fun signInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun albumListFragment(): AlbumListFragment

    @ContributesAndroidInjector
    abstract fun albumDetailFragment(): AlbumDetailFragment

}