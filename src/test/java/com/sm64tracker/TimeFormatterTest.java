package com.sm64tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.sm64tracker.service.PbDecisionResult;
import com.sm64tracker.service.PersonalBestService;
import com.sm64tracker.util.TimeFormatter;

class TimeFormatterTest {

    @Test
    void shouldConvertSecondsStringToMilliseconds() {
        assertEquals(26420L, TimeFormatter.parseToMilliseconds("26.42"));
        assertEquals(62000L, TimeFormatter.parseToMilliseconds("1:02.00"));
        assertEquals(133570L, TimeFormatter.parseToMilliseconds("2:13.57"));
    }

    @Test
    void shouldConvertMillisecondsBackToFormattedTime() {
        assertEquals("26.42", TimeFormatter.formatFromMilliseconds(26420L));
        assertEquals("1:02.00", TimeFormatter.formatFromMilliseconds(62000L));
        assertEquals("2:13.57", TimeFormatter.formatFromMilliseconds(133570L));
    }

    @Test
    void shouldDetermineWhetherSubmittedTimeIsANewPb() {
        PersonalBestService service = new PersonalBestService(new FakePersonalBestRepository());

        PbDecisionResult first = service.submitTime(1L, 31_840L);
        PbDecisionResult second = service.submitTime(1L, 29_510L);
        PbDecisionResult slower = service.submitTime(1L, 35_000L);

        assertTrue(first.isNewPb());
        assertTrue(second.isNewPb());
        assertFalse(slower.isNewPb());
    }

    private static class FakePersonalBestRepository extends com.sm64tracker.repository.PersonalBestRepository {
        private java.util.Map<Long, java.util.List<Long>> data = new java.util.HashMap<>();

        @Override
        public java.util.Optional<Long> findCurrentPbTimeMs(long starId) {
            java.util.List<Long> values = data.get(starId);
            if (values == null || values.isEmpty()) {
                return java.util.Optional.empty();
            }
            return java.util.Optional.of(values.stream().min(Long::compareTo).orElseThrow());
        }

        @Override
        public void insert(long starId, long timeInMs, java.time.LocalDateTime achievedAt) {
            data.computeIfAbsent(starId, key -> new java.util.ArrayList<>()).add(timeInMs);
        }
    }
}
