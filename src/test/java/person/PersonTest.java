package person;

import com.rd.dz12.Man;
import com.rd.dz12.Person;
import com.rd.dz12.Woman;
import dataprovider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class PersonTest {
    private static final SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "singlePersons", dataProviderClass = TestDataProvider.class)
    public void testRegisterPartnershipIfSingle(Person person1, Person person2){
        person1.registerPartnership(person2);

        softAssert.assertEquals(person1.getPartner(),person2);
        softAssert.assertEquals(person2.getPartner(),person1);
        softAssert.assertEquals(person2.getLastName(), person1.getLastName(), "Lastname wasn't changed for woman.");
    }

    @Test(dataProvider = "marriedPersons", dataProviderClass = TestDataProvider.class)
    public void testRegisterPartnershipIfMarried(Person person1, Person person2){
        Person person3;
        person1.setPartner(person2);

        if(person1 instanceof Woman) {
           person3 = new Man("Bob","Black", 70, null);
        } else {
            person3 = new Woman("Belinda","Jonson",25,null);
        }

        person1.registerPartnership(person3);
        Assert.assertEquals(person1.getPartner(),person2);
    }

    @Test(dataProvider = "singlePersons", dataProviderClass = TestDataProvider.class)
    public void testDeregisterPartnershipWithLastNameChange(Person person1, Person person2){
        String oldLastName;

        if(person1 instanceof Woman) {
            oldLastName = person1.getLastName();
        } else {
             oldLastName = person2.getLastName();
        }
        person1.setPartner(person2);
        person1.deregisterPartnership(true);
        softAssert.assertNull(person1.getPartner());
        softAssert.assertNull(person2.getPartner());

        if(person1 instanceof Woman) {
            softAssert.assertEquals(person1.getLastName(), oldLastName);
        } else {
            softAssert.assertEquals(person2.getLastName(), oldLastName);
        }
    }

    @Test(dataProvider = "marriedPersons", dataProviderClass = TestDataProvider.class)
    public void testDeregisterPartnershipWithoutLastNameChange(Person person1, Person person2){
        person1.setPartner(person2);
        person2.setPartner(person1);
        person1.deregisterPartnership(false);
        softAssert.assertNull(person1.getPartner());
        softAssert.assertNull(person2.getPartner());
        softAssert.assertEquals(person2.getLastName(), person1.getLastName());
    }

    @Test(dataProvider = "singlePersons", dataProviderClass = TestDataProvider.class)
    public void testDeregisterPartnershipIfSingle(Person person1, Person person2){

        person1.deregisterPartnership(true);
        Assert.assertNull(person1.getPartner());
    }

    @Test(dataProvider = "dataForGetLastName", dataProviderClass = TestDataProvider.class)
    public void testGetLastName(Person person, String expectedName){

        Assert.assertEquals(person.getLastName(),expectedName);
    }

    @Test(dataProvider = "dataForSetLastNames", dataProviderClass = TestDataProvider.class)
    public void testSetLastName(Person person, List<String> names, List<String> expectedNames){
        for(int i =0; i < names.size(); i++) {
            person.setLastName(names.get(i));
            softAssert.assertEquals(person.getLastName(), expectedNames.get(i));
        }
    }

    @Test(dataProvider = "dataForGetFirstName", dataProviderClass = TestDataProvider.class)
    public void testGetFirstName(Person person, String expectedName){

        Assert.assertEquals(person.getFirstName(),expectedName);
    }

    @Test(dataProvider = "dataForSetFirstNames", dataProviderClass = TestDataProvider.class)
    public void testSetFirstName(Person person, List<String> names, List<String> expectedNames){
        for(int i =0; i < names.size(); i++) {
            person.setFirstName(names.get(i));
            softAssert.assertEquals(person.getFirstName(), expectedNames.get(i));
        }
    }

    @Test(dataProvider = "dataForGetAge", dataProviderClass = TestDataProvider.class)
    public void testGetAge(Person person, int expectedAge){

        Assert.assertEquals(person.getAge(),expectedAge);
    }

    @Test(dataProvider = "dataForSetAge", dataProviderClass = TestDataProvider.class)
    public void testSetAge(Person person, List<Integer> ages, List<Integer> expectedAges){
        for (int i = 0; i <ages.size(); i++) {
            person.setAge(ages.get(i));
            softAssert.assertEquals((Integer) person.getAge(),expectedAges.get(i));
        }
    }

    @Test(dataProvider = "dataForGetPartner", dataProviderClass = TestDataProvider.class)
    public void testGetPartner(Person person, Person expectedPerson){
        person.setPartner(expectedPerson);
        softAssert.assertEquals(person.getPartner(),expectedPerson);
        person.deregisterPartnership(true);
    }

    @Test (dataProvider = "singlePersons", dataProviderClass = TestDataProvider.class)
    public void testSetPartner(Person person1, Person person2){
        person1.setPartner(person2);
        softAssert.assertEquals(person1.getPartner(), person2);

        if (person1 instanceof Woman)
            softAssert.assertEquals(person1.getLastName(), person2.getLastName());
        else if (person2 instanceof Woman)
            softAssert.assertEquals(person2.getLastName(), person1.getLastName());

    }
}
