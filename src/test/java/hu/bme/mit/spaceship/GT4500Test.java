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
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
	verify(primaryMock, times(1)).fire(1);
	//verify(secondaryMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	//Mock behaviour
	when(primaryMock.fire(1)).thenReturn(true);
	when(secondaryMock.fire(1)).thenReturn(true);
    
	
	// Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
	verify(primaryMock, times(1)).fire(1);
	verify(secondaryMock, times(1)).fire(1);
  }
}
