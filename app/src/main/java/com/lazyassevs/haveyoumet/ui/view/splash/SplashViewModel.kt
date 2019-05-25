package com.lazyassevs.haveyoumet.ui.view.splash

import com.lazyassevs.domain.usecase.UserUseCase
import com.lazyassevs.haveyoumet.util.base.mvi.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : BaseViewModel<Action, State>() {

    override val initialState: State
        get() = State.InitialState

    init {
        val getNextPageChange: Observable<State> = actions.ofType<Action.GetNextPage>()
            .switchMap { getNextPageObservable() }
            .distinctUntilChanged()

        subscribe(getNextPageChange)
    }

    private fun getNextPageObservable(): Observable<State> = Single.timer(2000, TimeUnit.MILLISECONDS)
        .flatMap {
            userUseCase.isUserLoggedIn()
                .subscribeOn(Schedulers.io())
        }
        .toObservable()
        .map<State> { if (it) State.GoToHome else State.GoToAuthentication }
        .onErrorReturn { State.Error(it) }

}