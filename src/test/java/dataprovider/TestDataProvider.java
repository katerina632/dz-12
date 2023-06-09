package dataprovider;

import com.rd.dz12.Man;
import com.rd.dz12.Woman;
import org.testng.annotations.DataProvider;

import java.util.*;

public class TestDataProvider {
    private static final Woman woman = new Woman("Lesly", "Grey", 21, null);
    private  static final Man man = new Man("Bob", "Black", 30, null);

    @DataProvider(name = "singlePersons")
    public static Object[][] singlePersons() {
        Man man1 = new Man("Bob", "Black", 30, null);
        Woman woman1 = new Woman("Nancy", "Drew", 45, null);

        Man man2 = new Man("Ben", "Stenberg", 25, null);
        Woman woman2 = new Woman("Lesly", "Grey", 21, null);

        return new Object[][]{{man1, woman1}, {woman2, man2}};
    }

    @DataProvider(name = "marriedPersons")
    public static Object[][] marriedPersons() {

        Man man1 = new Man("Bob", "Black", 30, null);
        Woman woman1 = new Woman("Nancy", "Drew", 45, man1);

        Man man2 = new Man("Ben", "Stenberg", 25, null);
        Woman woman2 = new Woman("Lesly", "Grey", 21, man2);

        return new Object[][]{{man1, woman1}, {woman2, man2}};
    }

    @DataProvider(name = "dataForGetLastName")
    public static Object[][] dataForGetLastName() {

        Man man = new Man("Bob", "Black", 30, null);
        Woman woman = new Woman("Lesly", "Grey", 30, null);

        String expectedManName = "Black";
        String expectedWomanName = "Grey";

        return new Object[][]{{man, expectedManName}, {woman, expectedWomanName}};
    }

    @DataProvider(name = "dataForSetLastNames")
    public static Object[][] dataForSetLastNames() {
        Man man1 = new Man("Bob", "Black", 30, null);
        List<String> manNames = Arrays.asList(null, " ", "Blackwood");
        List<String> expectedManNames = Arrays.asList("Black", "Black", "Blackwood");

        Woman woman2 = new Woman("Lesly", "Grey", 21, null);
        List<String> womanNames = Arrays.asList(null, " ", "Greywood");
        List<String> expectedWomanNames = Arrays.asList("Grey", "Grey", "Greywood");

        return new Object[][]{{man1, manNames, expectedManNames}, {woman2, womanNames, expectedWomanNames}};
    }

    @DataProvider(name = "dataForGetFirstName")
    public static Object[][] dataForGetFirstName() {

        String expectedManName = "Bob";
        String expectedWomanName = "Lesly";

        return new Object[][]{{man, expectedManName}, {woman, expectedWomanName,}};
    }

    @DataProvider(name = "dataForSetFirstNames")
    public static Object[][] dataForSetFirstNames() {
        Man man1 = new Man("Bob", "Black", 30, null);
        List<String> manNames = Arrays.asList(null, " ", "Robert");
        List<String> expectedManNames = Arrays.asList("Bob", "Bob", "Robert");

        Woman woman2 = new Woman("Lesly", "Grey", 21, null);
        List<String> womanNames = Arrays.asList(null, " ", "Joan");
        List<String> expectedWomanNames = Arrays.asList("Lesly", "Lesly", "Joan");

        return new Object[][]{{man1, manNames, expectedManNames}, {woman2, womanNames, expectedWomanNames}};
    }

    @DataProvider(name = "dataForGetAge")
    public static Object[][] dataForGetAge() {

        int expectedManAge = 30;
        int expectedWomanAge = 21;

        return new Object[][]{{man, expectedManAge}, {woman, expectedWomanAge,}};
    }

    @DataProvider(name = "dataForSetAge")
    public static Object[][] dataForSetAge() {

        Man man = new Man("Bob", "Black", 30, null);
        Woman woman = new Woman("Lesly", "Grey", 30, null);

        List<Integer> ages = Arrays.asList(-5, -1, 0, 1, 50, 120, 121);
        List<Integer> expectedAges = Arrays.asList(30, 30, 0, 1, 50, 120, 120);

        return new Object[][]{{man, ages, expectedAges}, {woman, ages, expectedAges}};
    }

    @DataProvider(name = "dataForGetPartner")
    public static Object[][] dataForGetPartner() {

        man.setPartner(woman);

        return new Object[][]{{man, woman}, {woman, man,}};
    }

    @DataProvider(name = "dataForIsRetiredManTestMan")
    public static Object[][] dataForIsRetiredManTestMan() {

        return new Object[][]{{30, false}, {60, false}, {65, true}, {75, true}};
    }

    @DataProvider(name = "dataForIsRetiredManTestWoman")
    public static Object[][] dataForIsRetiredManTestWoman() {

        return new Object[][]{{30, false}, {60, true}, {65, true}, {75, true}};
    }

    @DataProvider(name = "dataForWomanLastNameChange")
    public static Object[][] dataForWomanLastNameChange() {
        Man man1 = new Man("Bob", "Black", 30, null);
        Man man2 = new Man("John", "Jones", 45, null);

        return new Object[][]{{man1}, {man2}};
    }
}
