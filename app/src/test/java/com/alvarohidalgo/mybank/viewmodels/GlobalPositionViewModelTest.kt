package com.alvarohidalgo.mybank.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alvarohidalgo.mybank.domain.usecases.GetGlobalPositionUseCase
import com.alvarohidalgo.mybank.mothers.GlobalPositionMother
import com.alvarohidalgo.mybank.presentation.globalposition.GlobalPositionViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock


@RunWith(JUnit4::class)
class GlobalPositionViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getGlobalPositionMock: GetGlobalPositionUseCase =
        mock(GetGlobalPositionUseCase::class.java).apply {
            whenever(execute()).thenReturn(
                Single.just(GlobalPositionMother.createGlobalPosition(name = "Alvaro"))
            )
        }

    private val viewmodel = GlobalPositionViewModel(getGlobalPositionMock)


    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun onCreateShouldTriggerGetGlobalPosition() {
        viewmodel.onCreate()
        verify(getGlobalPositionMock, times(1)).execute()
    }
}
