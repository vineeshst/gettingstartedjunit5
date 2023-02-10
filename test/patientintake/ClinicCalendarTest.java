package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {
    private ClinicCalendar calendar;
    @BeforeAll
    static void testClassSetup(){
        System.out.println("Before all...");
    }
    @BeforeEach
    void init(){
        System.out.println("Before each....");
        calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
    }
    @Test
     void allowEntryOfAnAppointment(){
        System.out.println("Entry of appointment...");
        calendar.addAppointment("Vineesh","Anand", "Avery","02/26/2023 11:00 am");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppointment = appointments.get(0);

        assertAll(
                ()->assertEquals("Vineesh", enteredAppointment.getPatientFirstName()),
                ()->assertEquals("Anand", enteredAppointment.getPatientLastName()),
                ()->assertSame(Doctor.avery, enteredAppointment.getDoctor()),
                ()->assertEquals("2/26/2023 11:00 AM", enteredAppointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
                );
    }


    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        calendar.addAppointment("Vineesh","Anand", "Avery","02/26/2023 11:00 am");
        assertTrue(calendar.hasAppointment(LocalDate.of(2023, 2, 26)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        assertFalse(calendar.hasAppointment(LocalDate.of(2023, 2, 26)));
    }

    @Test
    @Disabled
    void returnCurrentDaysAppointments() {
        calendar.addAppointment("Jim", "Weaver", "avery",
                "08/26/2018 2:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "08/26/2018 3:00 pm");
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2018 2:00 pm");
        assertEquals(2, calendar.getTodayAppointments().size());
//        assertIterableEquals(calendar.getTodayAppointments(),calendar.getAppointments());
    }
    @AfterEach
    void tearDownEachTest(){
        System.out.println("After each...");
    }
    @AfterAll
    static void teraDownTestClass(){
        System.out.println("After all...");
    }
}