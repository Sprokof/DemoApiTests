package api.provider;

import java.util.Map;

public class FakeStoreIdValidationHolder {
    private final Map<String, String> params;
    private final int statusCode;
    private final boolean valid;

    public FakeStoreIdValidationHolder(Map<String, String> params, int statusCode, boolean valid) {
        this.params = params;
        this.statusCode = statusCode;
        this.valid = valid;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public boolean isValid() {
        return valid;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
