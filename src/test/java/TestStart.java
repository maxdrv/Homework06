import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestStart {
    RgsTestSteps user = new RgsTestSteps();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Шенген", "Испания", "01112018", "Ivanov Ivan", "10101990",
                        "Многократные поездки в течение года", "IVANOV IVAN", "10.10.1990", "Включен" },

        });
    }

    @Parameterized.Parameter(0)
    public String destinationRegionName;

    @Parameterized.Parameter(1)
    public String destinationCountryName;

    @Parameterized.Parameter(2)
    public String dateOfFirstTripValue;

    @Parameterized.Parameter(3)
    public String nameLastNameValue;

    @Parameterized.Parameter(4)
    public String dateOfBirthValue;

    @Parameterized.Parameter(5)
    public String multipleTripsValue;

    @Parameterized.Parameter(6)
    public String person;

    @Parameterized.Parameter(7)
    public String dateOfBirthFormatValue;

    @Parameterized.Parameter(8)
    public String activeRest;

    @Before
    public void before(){
        user.startUp();
    }

    @After
    public void after(){
        user.endTest();
    }


    @Test
    public void Homework06(){
        user.openInsuranceNavBar();
        user.choseInsuranceCategory("Выезжающим за рубеж");
        user.culculateOnlineAbroadsTripsButton();
        user.checkAbroadTripsHeader("Страхование выезжающих за рубеж");
        user.tripsMultipleButton();
        user.destinationRegionInput(destinationRegionName);
        user.destinationCountrySelect(destinationCountryName);
        user.dateOfFirstTripInput(dateOfFirstTripValue);
        user.noMoreThan90DaysButton();
        user.nameLastNameAndBirthDateInput(nameLastNameValue, dateOfBirthValue);
        user.planningActiveRestButton();
        user.personalDataButton();
        user.calculateInsuranceButton();
        user.checkValues(multipleTripsValue, destinationRegionName,
                person, dateOfBirthFormatValue, activeRest);
    }
}