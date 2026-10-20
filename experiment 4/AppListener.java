import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener
        implements ServletContextListener {

    @Override
    public void contextInitialized(
            ServletContextEvent event) {

        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(
            ServletContextEvent event) {

        System.out.println("Application stopped");
    }
}
