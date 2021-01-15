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
        if (responseString == null)
            return new Response(false, true, "Ошибка: запрос не был осуществлен");

        boolean closureStatus = responseString.startsWith("стоп"); //todo regex
        boolean errorStatus = responseString.startsWith("ошибка");

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