package CSCI5308.GroupFormationTool.LoggerTest;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Logger.InfoLoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class LoggerFactoryTest {

    @Test
    void testCreateInfoLogger() {
        ILoggerFactory factory = new InfoLoggerFactory();
        ILogger logger = factory.createLogger();
        logger.logMessage("test message","leave this alone", LogDBMock.getInstance());
        assertFalse(logger.checkLogValid("test message","leave this alone"));
        assertTrue(logger.checkLogValid(null,"leave this alone"));
        logger.logMessage(null,"leave this alone", LogDBMock.getInstance());
        assertEquals(LogDBMock.getInstance().toString(),"1");
    }
    @Test
    void testCreateErrorLogger() {
        ILoggerFactory factory = new ErrorLoggerFactory();
        ILogger logger = factory.createLogger();
        logger.logMessage("test message","leave this alone", LogDBMock.getInstance());
        assertFalse(logger.checkLogValid("test message","leave this alone"));
        assertTrue(logger.checkLogValid(null,"leave this alone"));
        assertTrue(logger.checkLogValid("test message",null));
        assertEquals(LogDBMock.getInstance().toString(),"1");
    }
}