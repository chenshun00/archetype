import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/7/4 8:39 下午
 */
public class LogContext extends HashMap<String, StringBuilder> {

    private static final ThreadLocal<LogContext> LOCAL = new ThreadLocal<>();

    public static void init() {
        LOCAL.set(new LogContext());
        LOCAL.get().put("log", new StringBuilder());
    }

    public static LogContext getContext() {
        return LOCAL.get();
    }

    public static void clean() {
        LOCAL.remove();
    }

    public String getAll() {
        return LOCAL.get().get("log").toString();
    }

    public LogContext fill(String log) {
        log = Optional.ofNullable(log).orElse("null");
        LOCAL.get().get("log").append(log).append("\n");
        return this;
    }

    public LogContext fill(String format, Object... args) {
        final String result = format(format, args);
        return fill(result);
    }

    public static String format(String from, Object... arguments) {
        if (from == null) {
            return null;
        } else {
            String computed = from;
            if (arguments != null && arguments.length != 0) {
                for (Object o : arguments) {
                    Object argument = Optional.ofNullable(o).orElse("null");

                    String result;
                    if (argument instanceof Throwable) {
                        Throwable throwable = (Throwable) argument;
                        final StringWriter stringWriter = new StringWriter();
                        throwable.printStackTrace(new PrintWriter(stringWriter));
                        result = stringWriter.toString();
                    } else {
                        result = argument.toString();
                    }
                    computed = computed.replaceFirst("\\{}", Matcher.quoteReplacement(result));
                }
            } else if (arguments == null) {
                computed = computed.replaceFirst("\\{}", "null");
            }

            return computed;
        }
    }

}
