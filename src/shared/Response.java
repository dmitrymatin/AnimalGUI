package shared;

public class Response {
    private final boolean closureStatus;
    private final boolean errorStatus;
    private final String message;

    public Response(boolean closureStatus, boolean errorStatus, String message) {
        this.closureStatus = closureStatus;
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public static Response parseResponse(String responseString) {
        boolean closureStatus = responseString.length() > 3 ? responseString.substring(0, 4).contains("стоп") : false;
        boolean errorStatus = responseString.length() > 6 ? responseString.substring(0, 6).contains("ошибка") : false;

        return new Response(closureStatus, errorStatus, responseString);
    }

    public boolean isClosureStatus() {
        return closureStatus;
    }

    public boolean isErrorStatus() {
        return errorStatus;
    }

    public String getMessage() {
        return message;
    }
}