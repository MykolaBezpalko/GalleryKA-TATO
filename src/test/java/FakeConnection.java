import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.Manager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FakeConnection implements Manager {
    private static final Logger log = Logger.getLogger(DBManager.class);

    public Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gallery_test?serverTimezone=UTC",
                    "root",
                    "123qwe123"
            );
            c.setAutoCommit(false);
        }catch (SQLException e){
            log.error("cant get TESTING connection." + e);
        }
        return c;
    }


    public void commitAndClose(Connection con) {
        try {
            if (con == null) {
                throw new NullPointerException("TESTING connection is NULL");
            }
            con.commit();
            con.close();
        } catch (SQLException | NullPointerException e) {
            log.error("cant close TESTING connection." + e);
        }
    }
}
