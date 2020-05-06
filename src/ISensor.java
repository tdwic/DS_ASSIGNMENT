import java.awt.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//Interface extesnd remote
public interface ISensor extends Remote {
	public int addsensor(SensorModel sensormodel) throws RemoteException;	//addSensor method
	public ArrayList<SensorModel> getSensors() throws RemoteException;	//getSensor method
	public void updatesensor(SensorModel sensormodel) throws RemoteException;	//updateSensor Method
}
