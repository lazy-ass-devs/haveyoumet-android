package com.lazyassevs.haveyoumet.ui.view.authentication.signin

import com.lazyassevs.domain.enum.SignInProvider
import com.lazyassevs.domain.usecase.UserUseCase
import com.lazyassevs.haveyoumet.util.base.mvi.BaseViewModel
import com.lazyassevs.haveyoumet.util.base.mvi.Reducer
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : BaseViewModel<Action, State>() {

    override val initialState: State
        get() = State()

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            is Change.SignInError -> state.copy(isSignInSuccess = false, isSigningIn = false, error = change.error)
            Change.SignInSuccess -> state.copy(isSignInSuccess = true, isSigningIn = false, error = null)
            Change.SigningIn -> state.copy(isSignInSuccess = false, isSigningIn = true, error = null)
            Change.ReturnToSignIn -> state.copy(isSignInSuccess = false, isSigningIn = false, error = null)
        }
    }

    private val doSignInChange = actions.ofType<Action.DoSignIn>()
        .switchMap { loginObservable(it.emailAddress, it.password) }

    private val trySignInAgainChange = actions.ofType<Action.TrySignInAgain>()
        .switchMap { trySignInAgainObservable() }

    private val doSignInProviderChange = actions.ofType<Action.DoSignInProvider>()
        .switchMap { signInUsingProvider(it.provider, it.id) }

    init {
        val changes = Observable.mergeArray(doSignInChange, doSignInProviderChange, trySignInAgainChange)
            .scan(initialState, reducer)
            .distinctUntilChanged()

        subscribe(changes)
    }

    private fun loginObservable(emailAddress: String, password: String): Observable<Change> =
        userUseCase.signIn(emailAddress, password)
            .toObservable()
            .map<Change> { Change.SignInSuccess }
            .startWith(Change.SigningIn)
            .onErrorReturn { Change.SignInError(it) }
            .subscribeOn(Schedulers.io())

    private fun signInUsingProvider(provider: SignInProvider, id: String): Observable<Change> =
        userUseCase.signInUsingProvider(provider.name, id)
            .toObservable()
            .map<Change> { Change.SignInSuccess }
            .startWith(Change.SigningIn)
            .onErrorReturn { Change.SignInError(it) }
            .subscribeOn(Schedulers.io())

    private fun trySignInAgainObservable(): Observable<Change> = Observable.just<Change>(Change.ReturnToSignIn)

}