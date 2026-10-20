
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Calculator obj = new CalculatorImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", obj);

            System.out.println("RMI Server started!");
            System.out.println("Waiting for client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

