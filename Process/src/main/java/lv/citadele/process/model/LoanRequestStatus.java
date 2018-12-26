package lv.citadele.process.model;

import java.util.stream.Stream;

public enum  LoanRequestStatus {
    PENDING(config()
            .isConfirmationAllowed(false)
            .isRejectAllowed(true)
            .isValidationAllowed(true)),
    VALIDATED(config()
            .isConfirmationAllowed(true)
            .isRejectAllowed(true)
            .isValidationAllowed(true)),
    INVALID(config()
            .isConfirmationAllowed(false)
            .isRejectAllowed(true)
            .isValidationAllowed(true)),
    REJECTED(config()
            .isConfirmationAllowed(false)
            .isRejectAllowed(false)
            .isValidationAllowed(false)),
    CONFIRMED(config()
            .isConfirmationAllowed(false)
            .isRejectAllowed(false)
            .isValidationAllowed(false)),
    ;

    private final Config config;

    public static LoanRequestStatus createFrom(String name) {
        return Stream.of(LoanRequestStatus.values()).findFirst().orElse(LoanRequestStatus.PENDING);
    }

    LoanRequestStatus(Config config) {
        this.config = config;
    }

    public boolean validationAllowed() {
        return config.validationAllowed;
    }

    public boolean confirmationAllowed() {
        return config.confirmationAllowed;
    }

    public boolean rejectAllowed() {
        return config.rejectAllowed;
    }

    private static Config config() {
        return new Config();
    }

    /**
     * Configuration holder.
     * Made for better enum comprehension
     */
    private static class Config {
        private boolean validationAllowed;
        private boolean confirmationAllowed;
        private boolean rejectAllowed;

        private Config isValidationAllowed(boolean validationAllowed) {
            this.validationAllowed = validationAllowed;
            return this;
        }

        private Config isConfirmationAllowed(boolean confirmationAllowed) {
            this.confirmationAllowed = confirmationAllowed;
            return this;
        }

        private Config isRejectAllowed(boolean rejectAllowed) {
            this.rejectAllowed = rejectAllowed;
            return this;
        }
    }
}
