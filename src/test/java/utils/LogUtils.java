package utils;

public class LogUtils {

    public static String getShortLog(Throwable exception, int stackTraceElementsToLog){
        StringBuilder sb = new StringBuilder();
        sb.append(exception.getMessage()).append("\n");
        for (int i = 0; i < stackTraceElementsToLog; i++){
            sb = sb.append(exception.getStackTrace()[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
