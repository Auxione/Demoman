package Curio.AI;

import Curio.GameObject.Controllers.ControlPackage;

public interface Task {

	public void Order(int currentTime,int time);

	public void update(ControlPackage controlPackage,int currentTime);

	public boolean active();
	
	public void finished();
	
	public boolean overrideLeftTimer();
}
