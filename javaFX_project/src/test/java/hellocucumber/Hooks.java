package hellocucumber;

import app.SystemModel;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void resetContext() {
        CommonSteps.system = new SystemModel();
        CommonSteps.project = null;
        CommonSteps.activity = null;
        CommonSteps.employee = null;
    }
}