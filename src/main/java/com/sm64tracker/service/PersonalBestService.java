package com.sm64tracker.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.sm64tracker.model.PersonalBest;
import com.sm64tracker.repository.PersonalBestRepository;

public class PersonalBestService {
    private final PersonalBestRepository personalBestRepository;

    public PersonalBestService(PersonalBestRepository personalBestRepository) {
        this.personalBestRepository = personalBestRepository;
    }

    public PbDecisionResult submitTime(long starId, long timeInMs) {
        Optional<Long> currentPb = personalBestRepository.findCurrentPbTimeMs(starId);

        if (currentPb.isEmpty()) {
            personalBestRepository.insert(starId, timeInMs, LocalDateTime.now());
            return new PbDecisionResult(true, 0L);
        }

        long currentPbMs = currentPb.get();
        if (timeInMs < currentPbMs) {
            personalBestRepository.insert(starId, timeInMs, LocalDateTime.now());
            return new PbDecisionResult(true, currentPbMs);
        }

        return new PbDecisionResult(false, currentPbMs);
    }

    public List<PersonalBest> getProgression(long starId) {
        return personalBestRepository.findByStarId(starId).stream()
                .sorted(Comparator.comparing(PersonalBest::getAchievedAt))
                .toList();
    }

    public Optional<PersonalBest> getCurrentPb(long starId) {
        return personalBestRepository.findByStarId(starId).stream()
                .min(Comparator.comparingLong(PersonalBest::getTimeInMs));
    }
}
