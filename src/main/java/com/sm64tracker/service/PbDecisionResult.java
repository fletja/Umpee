package com.sm64tracker.service;

public class PbDecisionResult {
    private final boolean newPb;
    private final long previousPbMs;

    public PbDecisionResult(boolean newPb, long previousPbMs) {
        this.newPb = newPb;
        this.previousPbMs = previousPbMs;
    }

    public boolean isNewPb() {
        return newPb;
    }

    public long getPreviousPbMs() {
        return previousPbMs;
    }
}
