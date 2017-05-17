package Registration;

import common.BaseAPIs;
import org.testng.annotations.Test;

/**
 * Created by EliteBook on 4/25/2017.
 */
public class RegistrationPage extends BaseAPIs {


    @Test
    public void enterFirstName() throws InterruptedException {


        typeByID("name_3_firstname", "Sami");



        sleepFor(3);

    }


}
