package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;

  @BeforeEach
  public void init(){
	this.primaryMock = mock(TorpedoStore.class);
	this.secondaryMock = mock(TorpedoStore.class);
	  
    this.ship = new GT4500(this.primaryMock, this.secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
	//Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	//Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);
    
	
	// Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(1)).fire(1);
  }
  
  //Test whether the first torpedostore is fired, it has time to 
  //cool down, so the second one is fired after
  @Test
  public void fireTorpedo_Cooling() {
	// Arrange
	//Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean resultFirst = ship.fireTorpedo(FiringMode.SINGLE);
	boolean resultSecond = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, resultFirst);
	assertEquals(true, resultSecond);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(1)).fire(1);
  }
  
  @Test
  public void fireTorpedo_Primary_isEmpty() {
	// Arrange
	//Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(true);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(false);
	when(secondaryMock.fire(1)).thenReturn(true);
    
	
	// Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
	verify(primaryMock, times(0)).fire(1);
	verify(secondaryMock, times(1)).fire(1);
  }
  
  @Test
  public void fireTorpedo_Primary_Faliure() {
	// Arrange
	// Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(false);
	when(secondaryMock.fire(1)).thenReturn(true);
  
	// Act
	boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	
	assertEquals(false, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(0)).fire(1);
  }
  
  @Test
  public void fireTorpedo_Secondary_Faliure() {
	// Arrange
	// Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(false);
  
	// Act
	boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	result = ship.fireTorpedo(FiringMode.SINGLE);
	
	// Assert
	assertEquals(false, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(1)).fire(1);
  }
  
  @Test
  public void fireTorpedo_All_FirstFaliure() {
	// Arrange
	// Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(false);
	when(primaryMock.fire(1)).thenReturn(false);
	when(secondaryMock.fire(1)).thenReturn(true);
	
	// Act
	boolean result = ship.fireTorpedo(FiringMode.ALL);
	
	// Assert
	assertEquals(false, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(0)).fire(1);
  }
  
  @Test
  public void fireTorpedo_BothEmpty() {
	// Arrange
	// Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(true);
	when(secondaryMock.isEmpty()).thenReturn(true);
	when(primaryMock.fire(1)).thenReturn(false);
	when(secondaryMock.fire(1)).thenReturn(false);
	
	// Act
	boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	
	// Assert
	assertEquals(false, result);
	verify(primaryMock, times(0)).fire(1);
	verify(secondaryMock, times(0)).fire(1);
  }
  
  @Test
  public void fireTorpedo_Secondary_isEmpty() {
	// Arrange
	// Mock behaviour
	when(primaryMock.isEmpty()).thenReturn(false);
	when(secondaryMock.isEmpty()).thenReturn(true);
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);
	
	// Act
	boolean result = ship.fireTorpedo(FiringMode.SINGLE);
	result = ship.fireTorpedo(FiringMode.SINGLE);
	
	// Assert
	assertEquals(true, result);
	verify(primaryMock, times(2)).fire(1);
	verify(secondaryMock, times(0)).fire(1);
  }
}
