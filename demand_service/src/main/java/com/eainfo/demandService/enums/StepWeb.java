package com.eainfo.demandService.enums;

public enum StepWeb {
    EMAIL_STEP,
    PHONE_STEP,
    OCR_STEP,
    AGENCY_STEP,
    PAYMENT_STEP,
    RDV_STEP;

    public StepWeb getNext() {
        switch (this) {
            case EMAIL_STEP:
                return PHONE_STEP;
            case PHONE_STEP:
                return OCR_STEP;
            case OCR_STEP:
                return AGENCY_STEP;
            case AGENCY_STEP:
                return PAYMENT_STEP;
            case PAYMENT_STEP:
                return RDV_STEP;
            default:
                throw new IllegalStateException("Unknown step: " + this);
        }
    }
}
