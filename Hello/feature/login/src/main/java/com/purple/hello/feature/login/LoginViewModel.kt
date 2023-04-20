package com.purple.hello.feature.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext

    val isLoggedIn = MutableStateFlow<Boolean>(false)

    fun kakaoLogin() {
        viewModelScope.launch {
            isLoggedIn.emit(handleKakaoLogin())
        }
    }

    fun kakaoLogout() {
        viewModelScope.launch {
            if (handleKakaoLogout()) {
                isLoggedIn.emit(false)
            }
        }
    }

    private suspend fun handleKakaoLogout(): Boolean =
        suspendCoroutine { continuation ->
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    continuation.resume(false)
                } else {
                    continuation.resume(true)
                }
            }
        }

    private suspend fun handleKakaoLogin(): Boolean =
        suspendCoroutine<Boolean> { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    continuation.resume(false)
                } else if (token != null) {
                    continuation.resume(true)
                }
            }
            // 카카오톡 설치 체크
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                // 카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    // 로그인 실패 시,
                    if (error != null) {
                        // 사용자 취소
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        // 그 외의 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                        // 로그인 성공
                    } else if (token != null) {
                        continuation.resume(true)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
}
