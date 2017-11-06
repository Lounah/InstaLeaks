package domain.model;

public class Response {
    private static int response_code;

    private Response(int code) {
        response_code = code;
    }

    public static int code() {
        return response_code;
    }

    public static void setResponseCode(final int code) {
        response_code = code;
    }

    public static Response withCode(final int code) {
        return new Response(code);
    }

}
