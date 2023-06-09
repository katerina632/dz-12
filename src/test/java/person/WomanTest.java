package person;

import com.rd.dz12.Man;
import com.rd.dz12.Woman;
import dataprovider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomanTest {
    private static final Woman woman= new Woman("Lesly","Grey",30, null);

    @Test (dataProvider = "dataForIsRetiredManTestWoman", dataProviderClass = TestDataProvider.class)
    public void testIsRetired(int age, boolean expectedResult){
        woman.setAge(age);
        Assert.assertEquals(woman.isRetired(), expectedResult);
    }

    @Test(dataProvider = "dataForWomanLastNameChange", dataProviderClass = TestDataProvider.class)
    public void testChangeLastName(Man man){
        woman.setPartner(man);
        Assert.assertEquals(woman.getLastName(),man.getLastName());
    }

    @Test(dataProvider = "dataForWomanLastNameChange", dataProviderClass = TestDataProvider.class)
    public void testChangeOldLastName(Man man){
        String oldLastName = woman.getLastName();
        man.setPartner(woman);
        woman.changeOldLastName(true);
        Assert.assertEquals(woman.getLastName(),oldLastName);
    }


}
