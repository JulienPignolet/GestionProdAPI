package univ.lorraine.gestionprodapi;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTests {

    @Autowired
    HomeController homeController;

    /**
     * Test si on a bien 200 ou 500 en retour
     */
    @Test
    public void testError500Generator(){
        assertTrue(homeController.getError500().getStatusCode() == HttpStatus.OK || homeController.getError500().getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
