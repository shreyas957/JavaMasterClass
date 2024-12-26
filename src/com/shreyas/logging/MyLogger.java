package com.shreyas.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Java provides a built-in logging framework that is part of the Java API.
 * The API is {@code java.util.logging} package.
 * It provides functionalities such as logging levels, loggers, handlers, and formatters.
 * <br>
 *  Features of java.util.logging
 *   <p>The java.util.logging package provides a powerful and flexible framework for logging in Java applications.
 *  It allows you to log messages, categorize them based on severity, and direct them to various outputs.</p>
 *   <h2>Log Levels:</h2>
 *  <ul>
 *      <li>Predefined levels include:
 *          <ul>
 *              <li>{@code SEVERE}</li>
 *              <li>{@code WARNING}</li>
 *              <li>{@code INFO}</li>
 *              <li>{@code CONFIG}</li>
 *              <li>{@code FINE}</li>
 *              <li>{@code FINER}</li>
 *              <li>{@code FINEST}</li>
 *              <li>{@code ALL}</li>
 *              <li>{@code OFF}</li>
 *          </ul>
 *      </li>
 *      <li>Use {@link java.util.logging.Logger#setLevel(Level)} to configure the minimum level to be logged.</li>
 *  </ul>
 *   <h2>Loggers:</h2>
 *  <ul>
 *      <li>Central objects for creating and managing log messages.</li>
 *      <li>Created using {@link java.util.logging.Logger#getLogger(String)}.</li>
 *      <li>Support hierarchical naming for organization (e.g., {@code com.shreyas.app}).</li>
 *  </ul>
 *   <h2>Handlers:</h2>
 *  <ul>
 *      <li>Direct log records to specific destinations such as the console, files, or custom outputs.</li>
 *      <li>Common handlers include:
 *          <ul>
 *              <li>{@link java.util.logging.ConsoleHandler}: Logs to the console.</li>
 *              <li>{@link java.util.logging.FileHandler}: Logs to a file.</li>
 *              <li>{@link java.util.logging.SocketHandler}: Sends logs over a network.</li>
 *              <li>{@link java.util.logging.MemoryHandler}: Buffers log records in memory.</li>
 *              <li>{@link java.util.logging.StreamHandler}: Writes logs to a generic output stream.</li>
 *          </ul>
 *      </li>
 *  </ul>
 *   <h2>Formatters:</h2>
 *  <ul>
 *      <li>Customize the format of log messages.</li>
 *      <li>Built-in formatters include:
 *          <ul>
 *              <li>{@link java.util.logging.SimpleFormatter}: Default text-based formatting.</li>
 *              <li>{@link java.util.logging.XMLFormatter}: Formats logs as XML.</li>
 *          </ul>
 *      </li>
 *      <li>Create custom formatters by extending {@link java.util.logging.Formatter}.</li>
 *  </ul>
 *   <h2>Filters:</h2>
 *  <ul>
 *      <li>Allow advanced control over which log messages are processed.</li>
 *      <li>Implement the {@link java.util.logging.Filter} interface and override the {@code isLoggable(LogRecord)} method.</li>
 *  </ul>
 *   <h2>Log Configuration:</h2>
 *  <ul>
 *      <li>Configure logging properties programmatically or via a configuration file (e.g., {@code logging.properties}).</li>
 *      <li>{@code System.setProperty("java.util.logging.config.file", "path/to/logging.properties");}</li>
 *      <li>Typical settings include:
 *          <ul>
 *              <li>Logger levels.</li>
 *              <li>Handlers and their levels.</li>
 *              <li>Formatter configuration.</li>
 *          </ul>
 *      </li>
 *  </ul>
 *   <h2>Thread-Safe:</h2>
 *  <ul>
 *      <li>The logging API is thread-safe, making it suitable for multi-threaded applications.</li>
 *  </ul>
 *   <h2>Localization:</h2>
 *  <ul>
 *      <li>Supports localized log messages using {@link java.util.ResourceBundle}.</li>
 *  </ul>
 *
 */
public class MyLogger {
    public static void main(String[] args) {
        var logger = Logger.getLogger(MyLogger.class.getName());

        // Set the logger level to the lowest level
        logger.setLevel(Level.ALL);

        // Get the default handler and set its level to ALL
        // A ConsoleHandler is used to write log messages to the console
        // The default handler is a ConsoleHandler
        // The default handler is set to Level.INFO by default
        // The default handler is set to useParentHandlers to true by default
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        // Remove the default ConsoleHandler to avoid duplicate logs
        logger.setUseParentHandlers(false);

        logger.info("Hello, World!");
        logger.warning("This is a warning message.");
        logger.severe("This is a severe message.");
        logger.config("This is a config message.");
        logger.fine("This is a fine message.");
        logger.finer("This is a finer message.");
        logger.finest("This is a finest message.");


        // Other API
         logger.log(Level.SEVERE, "This is a severe message.");
    }
}



