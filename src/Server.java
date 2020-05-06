
import java.rmi.*;
import java.rmi.registry.*;

public class Server {
	public static void main(String a[]) throws Exception
	{
		SensorImpl obj = new SensorImpl();
		Naming.rebind("rmi://localhost/ABC",obj);
		System.out.println("Server Started");
	}
}
