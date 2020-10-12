package com.example.players.utility;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit tests for Utils class.
 *
 * @author Mohamed TOUITI (mtouiti.info@gmail.com)
 */
public class UtilsTest {
	
	/* ------------------------- Test checkParameters(args) ------------------------- */
	
	/**
     * Should throw IllegalArgumentException. Null arguments.
     */
    @Test
    public void nullArgIllegalArgumentException() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			Utils.checkParameters(null);
                }
        );
    } 

	/**
     * Should throw IllegalArgumentException. Number of parameter is not acceptable.
     */
    @Test
    public void minimumArgIllegalArgumentException() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			String[] args = {"5", "mono", "Hello", "player1"};	
        			Utils.checkParameters(args);
                }
        );
    }    
    
    /**
     * Should throw IllegalArgumentException. first argument is not a number
     */
    @Test
    public void firsArgNotANumberIllegalArgumentException() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			String[] args = {"a", "mono", "Hello", "player1", "player2"};	
        			Utils.checkParameters(args);
                }
        );
    } 
    
    /**
     * Should throw IllegalArgumentException. processing type shoud be equal to "mono" or "multi"
     */
    @Test
    public void  ProcessingTypeIllegalArgumentException() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			String[] args = {"5", "test", "Hello", "player1", "player2"};	
        			Utils.checkParameters(args);
                }
        );
    } 
    
    /**
     * Should return true and validate the minimum arguments for 2 players.
     */
    @Test
    public void shouldReadTheParamProperly() {
    	String[] args = {"5", "mono", "Hello", "player1", "player2"};	
    	assertTrue(Utils.checkParameters(args));
    }
    
    /**
     * Should return true and validate the arguments for many players.
     */
    @Test
    public void shouldReadTheParamProperlyForManyPlayers() {
    	String[] args = {"5", "mono", "Hello", "player1", "player2", "player3", "player4"};	
    	assertTrue(Utils.checkParameters(args));
    }
    
    /* ------------------------- Test isInteger(str) ------------------------- */
    
    /**
     * Should throw IllegalArgumentException. Null arguments.
     */
    @Test
    public void isIntegerForNull() {
        assertThrows(
        		IllegalArgumentException.class,() -> {
        			Utils.isInteger(null);
                }
        );
    } 
    
    /**
     * Should return false, the parameter is not a valid string number.
     */
    @Test
    public void isIntegerForNotValidStringNumber() {	
    	assertFalse(Utils.isInteger("b"));
    } 
    
    /**
     * Should return false, the parameter is not a valid string number.
     */
    @Test
    public void isIntegerMixteOfStringNumber() {	
    	assertFalse(Utils.isInteger("7a"));
    }
    
    /**
     * Should return true, the parameter is a valid string number.
     */
    @Test
    public void isIntegerForValidStringNumber() {	
    	assertTrue(Utils.isInteger("5"));
    }
}
