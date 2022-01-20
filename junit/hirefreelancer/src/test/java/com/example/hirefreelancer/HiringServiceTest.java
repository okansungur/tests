package com.example.hirefreelancer;

import com.example.hirefreelancer.model.FreeLancer;
import com.example.hirefreelancer.model.HiringRequest;
import com.example.hirefreelancer.services.*;
import com.example.hirefreelancer.utilities.HireException;
import com.example.hirefreelancer.utilities.Utility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HiringServiceTest {
    @InjectMocks
    private HiringService hiringService;
    @Spy
    private DepositService depositServiceMock;
    @Spy
    private FreelancerService freelancerServiceMock;
    @Spy
    private HiringDAO hiringDAOMock;
    @Mock
    private Notification notificationMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;
    @Captor
    private ArgumentCaptor<HiringRequest> hiringRequestArgumentCaptor;


    @Test
    void testFromHiringServiceToCalculatePrice() {

        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 3, "1");
        double expected = 2 * 3 * 30;
        //2 days 3 experience years daily 30
        double actual = hiringService.calculatePayment(hiringRequest);
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("MockingDefaultValue")
    void testFromHiringServiceToGetAvailableFreelancerExperienceCount() {
        //İt will return 0 and list will be null
        int expected = 8;
        int actual = hiringService.getAvailableFreelancerExperienceYearsCount();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("assertEquals0")
    void testFromHiringServiceToGetAvailableFreelancerExperienceCount1() {

        when(this.freelancerServiceMock.getAvailableFreelancers()).
                thenReturn(Collections.singletonList(new FreeLancer("5", 5, "C#")));
        int expected = 5;
        int actual = hiringService.getAvailableFreelancerExperienceYearsCount();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("giveninsteadofwhen")
    void testFromHiringServiceToGetAvailableFreelancerExperienceCount2() {

        given(this.freelancerServiceMock.getAvailableFreelancers())
                .willReturn(Collections.singletonList((new FreeLancer("5", 5, "C#"))));
        int expected = 5;


        int actual = hiringService.getAvailableFreelancerExperienceYearsCount();


        assertEquals(expected, actual);
    }




    @Test
    @DisplayName("assertEquals")
    void testFromHiringServiceToGetAvailableMultipleFreelancersExperienceCount2() {
        List<FreeLancer> frelancers = Arrays.asList(new FreeLancer("5", 5, "C#"), new FreeLancer("6", 1, "Kotlin"));
        when(this.freelancerServiceMock.getAvailableFreelancers()).
                thenReturn(frelancers);
        int expected = 6;
        int actual = hiringService.getAvailableFreelancerExperienceYearsCount();
       // System.out.println(actual);
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("assertAll")
    void testFromHiringServiceToGetAvailableMultipleFreelancersExperienceCount3() {
        //Multiple thenReturnDStatement
        when(this.freelancerServiceMock.getAvailableFreelancers()).
                thenReturn(Collections.singletonList(new FreeLancer("5", 5, "C#"))).
                thenReturn(Collections.EMPTY_LIST);
        int expectedFirst = 5;
        int expectedSecond = 0;
        int actualFirst = hiringService.getAvailableFreelancerExperienceYearsCount();
        int actualSecond = hiringService.getAvailableFreelancerExperienceYearsCount();
        assertAll(
                () -> assertEquals(expectedFirst, actualFirst),
                () -> assertEquals(expectedSecond, actualSecond)
        );
    }


    @Test
    @DisplayName("assertThrows")
    void testThrowExceptionWhen() throws Exception {
        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 3, "1");
        when(this.freelancerServiceMock.findAvailableFreelancerId(hiringRequest))
                .thenThrow(HireException.class);
        Executable executable = () -> hiringService.requestHiring(hiringRequest);
        assertThrows(HireException.class, executable);
    }


    @Test
    @DisplayName("verifyNoMoreInteractions")
    void InvokeOtherMethods() throws Exception {
        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 2, "1");
        hiringService.requestHiring(hiringRequest);
        //Checking whether other calls are made or not
        verify(depositServiceMock, times(1)).pay(hiringRequest, 120.0);
        verifyNoMoreInteractions(depositServiceMock); //No more methods
    }

    @Test
    @DisplayName("verify1")
    void InvokeOtherMethods1() throws Exception {
        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), true, 2, "1");
        hiringService.requestHiring(hiringRequest);
        verify(depositServiceMock, never()).pay(any(), anyDouble());
    }

    @Test
    @DisplayName("verify2")
    void testMakeHiring() throws Exception {

        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 2, "1");
        String hiringId = hiringService.requestHiring(hiringRequest);
        verify(hiringDAOMock).save(hiringRequest);
        //System.out.println("hiringId=" + hiringId); //null Mock returns default value as null
    }


    @Test
    @DisplayName("doThrow")
    void testThrowExceptionWhenNotificationNotReady() {

        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 2, "1");
        doThrow(new HireException()).when(notificationMock).sendNotificationConfirmation(any());
        Executable executable = () -> hiringService.requestHiring(hiringRequest);
        assertThrows(HireException.class, executable);
    }


    @Test
    @DisplayName("doNothing")
    void testNotThrowExceptionWhenNotificationNotReady() throws Exception {
        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 2, "1");
        doNothing().when(notificationMock).sendNotificationConfirmation(any());
        hiringService.requestHiring(hiringRequest);
    }


    @Test
    @DisplayName("ArgumentCaptor")
    void testCorrectIdWithArgumentCaptor() throws Exception {

        HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 03), false, 2, "1");
        hiringService.requestHiring(hiringRequest);
        //2*2*30 =120
     /*   verify(depositServiceMock, times(1)).pay(eq(hiringRequest), doubleCaptor.capture());
        double captureddata = doubleCaptor.getValue();
        System.out.println(captureddata);
        assertEquals(120.0, captureddata);*/
        verify(hiringDAOMock, times(1)).save(hiringRequestArgumentCaptor.capture());
        HiringRequest capturedRequest = hiringRequestArgumentCaptor.getValue();
        //hiringRequestArgumentCaptor.getAllValues() //Can be used for multiple requests

        assertEquals("1", capturedRequest.getFreelancerId());

    }



    @Test
    @DisplayName("MockStatic")
    void testStaticUtilityClass() {
        try (MockedStatic<Utility> mockedConverter = mockStatic(
                Utility.class)) {

            HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                    LocalDate.of(2021, 01, 03), false, 2, "1");
            double expected = 120.0;
            mockedConverter.when(() -> Utility.convertingEuro(anyDouble())).thenReturn(120.0);
            double actual = hiringService.paymentConvertUSDtoEuro(hiringRequest);
            assertEquals(expected, actual);
        }

    }



    @Test
    @DisplayName("MockStaticAnswer")
    void testStaticUtilityClass2() {
        try (MockedStatic<Utility> mockedConverter = mockStatic(
                Utility.class)) {

            HiringRequest hiringRequest = new HiringRequest("1", LocalDate.of(2021, 01, 01),
                    LocalDate.of(2021, 01, 03), false, 2, "1");
            double expected = 120.0 * 0.8;
            mockedConverter.when(() -> Utility.convertingEuro(anyDouble()))
                    .thenAnswer(param -> (double) param.getArgument(0) * 0.8);

            double actual = hiringService.paymentConvertUSDtoEuro(hiringRequest);

            assertEquals(expected, actual);
        }

    }



    @Test
    @DisplayName("FinalKeywordTest")
    void testStaticUtilityClass3() {
        // instead of mockito-core  use mockito-inline
        when(this.freelancerServiceMock.getAvailableFreelancers())
                .thenReturn(Collections.singletonList(new FreeLancer("5", 5, "C#")));
        int expected = 5;


        int actual = hiringService.getAvailableFreelancerExperienceYearsCount();


        assertEquals(expected, actual);
    }


    //Testing Rest




}















