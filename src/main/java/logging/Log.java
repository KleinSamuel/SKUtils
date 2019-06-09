package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static boolean WRITE_LOG = false;
    private static String logFilePath;

    public static void setWriteLog(boolean writeLog) {
        WRITE_LOG = writeLog;
    }
    public static void setLogPath(String logPath) {
        logFilePath = logPath;
    }

    private static void printFormatted(String type, String message) {
        String toLog = String.format("%s%5s%s%s%s%-5s%s\n", "[",type,"]","(",getTimestamp(),")",message);
        System.out.print(toLog);
        try {
            if (WRITE_LOG && logFilePath != null) {
                File file = new File(logFilePath);
                FileWriter fr = new FileWriter(file, true);
                fr.write(toLog);
                fr.close();
            }
        } catch (IOException e){
            Log.error("Could not open log file to write!");
        }
    }

    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }

    public static void info(String message) {
        printFormatted("INFO",message);
    }

    public static void error(String message) {
        printFormatted("ERROR", message);
    }

    public static void ok(String message) {
        printFormatted("OK", message);
    }

    public static void warn(String message) {
        printFormatted("WARN", message);
    }

    public static void debug(String message) {
        printFormatted("DEBUG", message);
    }

}
