package lv.citadele.process.model;

import java.util.stream.Stream;

public enum  LoanRequestStatus {
    PENDING(config()
            .confirmationAllowed(false)
            .rejectAllowed(true)
            .validationAllowed(true)),
    VALIDATED(config()
            .confirmationAllowed(true)
            .rejectAllowed(true)
            .validationAllowed(true)),
    INVALID(config()
            .confirmationAllowed(false)
            .rejectAllowed(true)
            .validationAllowed(true)),
    REJECTED(config()
            .confirmationAllowed(false)
            .rejectAllowed(false)
            .validationAllowed(false)),
    CONFIRMED(config()
            .confirmationAllowed(false)
            .rejectAllowed(false)
            .validationAllowed(false)),
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

        private Config validationAllowed(boolean validationAllowed) {
            this.validationAllowed = validationAllowed;
            return this;
        }

        private Config confirmationAllowed(boolean confirmationAllowed) {
            this.confirmationAllowed = confirmationAllowed;
            return this;
        }

        private Config rejectAllowed(boolean rejectAllowed) {
            this.rejectAllowed = rejectAllowed;
            return this;
        }
    }
}
