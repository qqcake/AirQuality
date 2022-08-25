package com.bigcake.airquality.data

import com.bigcake.airquality.CoroutineTestExtension
import com.bigcake.airquality.data.model.AirQualityData
import com.bigcake.airquality.data.source.AirQualityLocal
import com.bigcake.airquality.data.source.AirQualityRemote
import com.bigcake.airquality.domain.AirQualityRepository
import com.bigcake.airquality.domain.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.logging.Logger
import kotlin.time.Duration.Companion.hours

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutineTestExtension::class)
class AirQualityRepositoryImplTest {
    private val logger = Logger.getLogger("AirQualityRepositoryImplTest")

    @MockK
    private lateinit var mockRemoteSource: AirQualityRemote

    @MockK
    private lateinit var mockLocalSource: AirQualityLocal

    private lateinit var subject: AirQualityRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        subject = AirQualityRepositoryImpl(mockRemoteSource, mockLocalSource)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @DisplayName("Should load from remote and cache to local when no local data initially")
    @Test
    fun testLoadAndCache(testDispatcher: TestDispatcher) = runTest {
        // Given
        val dummyInitialLocalData = emptyList<AirQualityData>()
        val dummyRemoteData = listOf(
            AirQualityData.ofSiteId("1"),
            AirQualityData.ofSiteId("2")
        )
        coEvery { mockLocalSource.getAirQualities() } returnsMany listOf(
            dummyInitialLocalData, dummyRemoteData
        )
        coEvery { mockLocalSource.setAirQualities(any()) } returns Unit
        coEvery { mockRemoteSource.getAirQualities() } returns dummyRemoteData

        // When
        val flow = subject.getAirQualities(false)

        // Then
        flow.onEach { result ->
            when (result) {
                is Result.Failure -> {
                    logger.warning("failure result, message=${result.message}")
                    Assertions.fail()
                }
                is Result.Loading -> {
                    logger.info("loading result")
                    Assertions.assertEquals(dummyInitialLocalData.size, result.data?.size)
                }
                is Result.Success -> {
                    logger.info("success result")
                    Assertions.assertEquals(dummyRemoteData.size, result.data.size)
                }
            }
        }.collect()

        // 1st: load from local to check if fetch from network
        // 2nd: load from local to emit local data
        coVerify(exactly = 2) { mockLocalSource.getAirQualities() }

        // Verify loading from network
        coVerify(exactly = 1) { mockLocalSource.setAirQualities(any()) }
        coVerify(exactly = 1) { mockRemoteSource.getAirQualities() }
    }

    @DisplayName("Should load from local if local cache is not empty and not expired")
    @Test
    fun testLoadFromLocal(testDispatcher: TestDispatcher) = runTest {
        // Given
        val dummyLocalData = listOf(
            AirQualityData.ofSiteId("1"),
            AirQualityData.ofSiteId("2")
        )
        coEvery { mockLocalSource.getAirQualities() } returns dummyLocalData
        coEvery { mockLocalSource.setAirQualities(any()) } returns Unit
        coEvery { mockLocalSource.isExpired(1.hours) } returns false

        // When
        val flow = subject.getAirQualities(false)

        // Then
        flow.onEach { result ->
            when (result) {
                is Result.Failure -> {
                    logger.warning("failure result, message=${result.message}")
                    Assertions.fail()
                }
                is Result.Loading -> {
                    logger.info("loading result")
                    Assertions.assertEquals(dummyLocalData.size, result.data?.size)
                }
                is Result.Success -> {
                    logger.info("success result")
                    Assertions.assertEquals(dummyLocalData.size, result.data.size)
                }
            }
        }.collect()

        // 1st: load from local to check if fetch from network
        // 2nd: load from local to emit local data
        coVerify(exactly = 2) { mockLocalSource.getAirQualities() }

        // Not loading from remote
        coVerify(exactly = 0) { mockRemoteSource.getAirQualities() }
        coVerify(exactly = 0) { mockLocalSource.setAirQualities(any()) }
    }
}