package person;
import com.rd.dz12.Man;
import dataprovider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ManTest {
    private static Man man = new Man("Bob", "Black", 30, null);

    @Test (dataProvider = "dataForIsRetiredManTestMan", dataProviderClass = TestDataProvider.class)
    public void testIsRetired(int age, boolean expectedResult){
        man.setAge(age);
        Assert.assertEquals(man.isRetired(), expectedResult);
    }

}
