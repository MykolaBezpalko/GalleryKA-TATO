import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.Encoder;
import com.gallery.webjava.db.entity.Administrator;
import org.junit.*;

import static org.junit.Assert.*;

public class ConnectionTest {
    AdminDAO aDao;
    Administrator testAdmin;

    @Before
    public void prepare() {
        aDao = new AdminDAO(new FakeConnection());
        testAdmin = new Administrator("testAdmin");
        testAdmin.setPassword("1111");
    }

    @Test
    public void createAdminTest() {
        aDao.createAdmin(testAdmin);
        assertTrue(true);
    }

    @After
    public void cleanUp(){
        aDao.deleteAdmin(testAdmin.getName());
    }
}
