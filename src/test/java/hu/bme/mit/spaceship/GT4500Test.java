package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTS;
  private TorpedoStore mockTS2;

  @BeforeEach
  public void init(){
    mockTS = mock(TorpedoStore.class);
    mockTS2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTS, mockTS2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(1)).fire(1);
    verify(mockTS2, times(0)).isEmpty();
    verify(mockTS2, times(0)).fire(1);
    assertEquals(true, result);
    
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(1)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_StoresEmpty() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(true);
    when(mockTS.fire(1)).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(0)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(0)).fire(1);

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_StoresEmpty() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(true);
    when(mockTS.fire(1)).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(0)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(0)).fire(1);

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_OneStoreEmpty() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(true);
    when(mockTS.fire(1)).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(0)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(1)).fire(1);

    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_SingleTwice_Success() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(1)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(1)).fire(1);

    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_OnFailure_OtherTorpedo_Not_Fired() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(false);
    when(mockTS2.isEmpty()).thenReturn(false);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(1)).fire(1);
    verify(mockTS2, times(0)).isEmpty();
    verify(mockTS2, times(0)).fire(1);

    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_SecondaryEmpty_PrimaryOk() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(1)).isEmpty();
    verify(mockTS, times(1)).fire(1);
    verify(mockTS2, times(0)).isEmpty();
    verify(mockTS2, times(0)).fire(1);

    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_PrimaryEmpty_SecondaryOk() {
    // Arrange
    when(mockTS.isEmpty()).thenReturn(false);
    when(mockTS.fire(1)).thenReturn(true);
    when(mockTS2.isEmpty()).thenReturn(true);
    when(mockTS2.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(mockTS, times(2)).isEmpty();
    verify(mockTS, times(2)).fire(1);
    verify(mockTS2, times(1)).isEmpty();
    verify(mockTS2, times(0)).fire(1);

    assertEquals(true, result);
  }

  @Test
  public void fireLaser() {

    assertEquals(false, ship.fireLaser(FiringMode.SINGLE));

  }

}
