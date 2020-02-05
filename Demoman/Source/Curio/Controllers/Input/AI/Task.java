package Curio.Controllers.Input.AI;

import Curio.Controllers.ControlPackage;
import Curio.Physics.Time;

public interface Task {

	public void Order(Time currentTime,int seconds);

	public void update(ControlPackage controlPackage,Time currentTime);

	public boolean active();
	
	public void finished();
	
	public boolean overrideLeftTimer();
}
