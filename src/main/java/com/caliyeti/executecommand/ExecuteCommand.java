package com.caliyeti.executecommand;

/**
 * Created by ashesdhakal on 5/29/15.
 */

import java.io.*;
import java.lang.*;
import java.lang.ProcessBuilder.*;

public class ExecuteCommand {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public static File getScriptFile(String fileName) {
        String pathAndFileName = "src" + getFileSeparator()
                + "main" + getFileSeparator()
                + "scripts" + getFileSeparator()
                + fileName;
        File file = null;
        try {
            file = new File(pathAndFileName);
            if (file.exists()) {
            } else {

            }
            changeOwnership(file, true, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void runShellCommand(String locationOfFile, boolean isWaitForProcess) {
        File file = new File(locationOfFile);
        changeOwnership(file, true, true, true);
        runShellCommand(file, isWaitForProcess);
    }

    public static void runShellCommand(File file, boolean isWaitForProcess) {
        if (isWindows() && file.toString().contains(".bat")) {
            runShellCommand_Win(file, isWaitForProcess);
        } else if (isMac() && file.toString().contains(".sh")) {
            runShellCommand_Mac(file, isWaitForProcess);
        } else if (isUnix()) {
            runShellCommand_Unix(file, isWaitForProcess);
        } else if (isSolaris()) {
            runShellCommand_Solaris(file, isWaitForProcess);
        }
    }


    private static void runShellCommand_Mac(File file, boolean isWaitForProcess) {
        Process process = null;
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", file.toString());
        processBuilder.redirectOutput(new File("target" + getFileSeparator() + file.toString() + "Output.log"));
        processBuilder.redirectError(new File("target" + getFileSeparator() + file.toString() + "Error.log"));

        File workingDirectory = new File(getUserDir().toString());
        processBuilder.directory(workingDirectory);

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isWaitForProcess) {
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            process.destroy();
        }

    }

    private static void runShellCommand_Win(File file, boolean isWaitForProcess) {
        Process process = null;
        ProcessBuilder processBuilder = new ProcessBuilder("CMD", "/C" + file.toString());
        processBuilder.redirectOutput(new File("target" + getFileSeparator() + file.toString() + "Output.log"));
        processBuilder.redirectError(new File("target" + getFileSeparator() + file.toString() + "Error.log"));

        File workingDirectory = new File(getUserDir().toString());
        processBuilder.directory(workingDirectory);

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isWaitForProcess) {
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            process.destroy();
        }

    }

    private static void runShellCommand_Unix(File file, boolean isWaitForProcess) {

    }

    private static void runShellCommand_Solaris(File file, boolean isWaitForProcess) {

    }

    public static void changeOwnership(File file, boolean isReadable, boolean isWritable, boolean isExecutable) {
        file.setReadable(isReadable);
        file.setWritable(isWritable);
        file.setExecutable(isExecutable);
    }

    public static void runCommand(File file, boolean isWaitForProcess) {
        Process process = null;
        ProcessBuilder processBuilder = null;

        if (isWindows()) {
            processBuilder = new ProcessBuilder("CMD", "/C" + file.toString());
        } else if (isMac()) {
            processBuilder = new ProcessBuilder("/bin/sh", file.toString());
        } else if (isUnix()) {
        } else if (isSolaris()) {
        }
        processBuilder.redirectOutput(Redirect.INHERIT);
        processBuilder.redirectError(Redirect.INHERIT);

        File workingDirectory = new File(getUserDir().toString());
        processBuilder.directory(workingDirectory);

        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isWaitForProcess) {
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            process.destroy();
        }

    }
    public static void startCommand(File file, boolean isWaitForProcess) {
        
    }

}
