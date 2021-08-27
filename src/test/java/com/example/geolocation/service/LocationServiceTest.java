package com.example.geolocation.service;

import com.example.geolocation.models.MyLocation;
import com.example.geolocation.repositories.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService locationService;
    @Captor
    private ArgumentCaptor<MyLocation> locationArgumentCaptor;
    @Captor
    private ArgumentCaptor<Long> idNumber;

    private MyLocation myLocation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myLocation = new MyLocation();
    }

    @Test
    void addLocation() {
        //Given
        myLocation.setDeviceId(5615155);
        myLocation.setLongitude(5445325);
        myLocation.setLatitiude(434345);

        //When
        locationService.addLocation(myLocation);

        //Then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        MyLocation location1 = locationArgumentCaptor.getValue();
        assertThat(location1, equalTo(myLocation));
    }

    @Test
    void removeLocation() {
        //Given
        myLocation.setId(0L);
        myLocation.setDeviceId(5615155);
        myLocation.setLongitude(5445325);
        myLocation.setLatitiude(434345);

        //When
        locationService.removeLocation(0L);

        //Then
        verify(locationRepository).deleteById(idNumber.capture());
        assertThat(myLocation.getId(), equalTo(idNumber.getValue()));
    }

    @Test
    void updateLocation() {
        //Given
        myLocation.setDeviceId(561561);
        myLocation.setLongitude(42424);
        myLocation.setLatitiude(42424);

        //When
        locationService.updateLocation(myLocation);

        //Then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        MyLocation location1 = locationArgumentCaptor.getValue();
        assertThat(location1, equalTo(myLocation));
    }

    @Test
    void printAllLocation() {
        //when
        locationService.printAllLocation();
        //then
        verify(locationRepository).findAll();
    }

    @Test
    void printLocationWithId() {
        //when
        locationService.printLocationWithId(0);
        //then
        verify(locationRepository).findById(0L);
    }
}