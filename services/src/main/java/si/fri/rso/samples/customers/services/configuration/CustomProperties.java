package si.fri.rso.samples.customers.services.configuration;


import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("custom-properties")
public class CustomProperties {


    @ConfigValue(value = "show-user", watch = true)
    private int myRandomNumber;

    public int getMyRandomNumber() {
        return myRandomNumber;
    }

    public void setMyRandomNumber(int myRandomNumber) {
        this.myRandomNumber = myRandomNumber;
    }

}
