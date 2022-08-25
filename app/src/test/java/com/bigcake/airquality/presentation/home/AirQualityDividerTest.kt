package com.bigcake.airquality.presentation.home

import com.bigcake.airquality.domain.entity.AirQuality
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AirQualityDividerTest {
    @DisplayName("Should divide air qualities items by average pm2.5")
    @ParameterizedTest
    @MethodSource("airQualitiesProvider")
    fun parse(airQualities: List<AirQuality>, expected: AirQualityDivider.DivideByPm25Result) {
        Assertions.assertEquals(expected, AirQualityDivider.divideByPm25(airQualities))
    }

    companion object {
        private val AQ_INVALID = AirQuality.ofPm25(AirQuality.INVALID_PM25)
        private val AQ_1 = AirQuality.ofPm25(1)
        private val AQ_2 = AirQuality.ofPm25(2)
        private val AQ_3 = AirQuality.ofPm25(3)

        @JvmStatic
        private fun airQualitiesProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(emptyList<AirQuality>(), AirQualityDivider.DivideByPm25Result.EMPTY),
                Arguments.of(
                    listOf(AQ_1, AQ_2, AQ_3),
                    AirQualityDivider.DivideByPm25Result(
                        divider = 2,
                        lowPm25Items = listOf(AQ_1, AQ_2),
                        highPm25Items = listOf(AQ_3)
                    )
                ),
                Arguments.of(
                    listOf(AQ_1, AQ_2, AQ_3, AQ_INVALID),
                    AirQualityDivider.DivideByPm25Result(
                        divider = 2,
                        lowPm25Items = listOf(AQ_1, AQ_2, AQ_INVALID),
                        highPm25Items = listOf(AQ_3)
                    )
                ),
                Arguments.of(
                    listOf(AQ_INVALID, AQ_INVALID, AQ_INVALID),
                    AirQualityDivider.DivideByPm25Result(
                        divider = AirQualityDivider.DEFAULT_PM25_DIVIDER,
                        lowPm25Items = listOf(AQ_INVALID, AQ_INVALID, AQ_INVALID),
                        highPm25Items = listOf()
                    )
                )
            )
        }
    }
}