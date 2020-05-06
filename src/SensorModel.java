import java.io.Serializable;

public class SensorModel implements Serializable  {

	//Sensor Model 
	int id;
	
	boolean Status;
	int FloorNumber;
	int RoomNumber;
	int SmokeLevel;
	int CO2Level;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public int getFloorNumber() {
		return FloorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		FloorNumber = floorNumber;
	}
	public int getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}
	public int getSmokeLevel() {
		return SmokeLevel;
	}
	public void setSmokeLevel(int smokeLevel) {
		SmokeLevel = smokeLevel;
	}
	public int getCO2Level() {
		return CO2Level;
	}
	public void setCO2Level(int cO2Level) {
		CO2Level = cO2Level;
	}
	
	
	
}
