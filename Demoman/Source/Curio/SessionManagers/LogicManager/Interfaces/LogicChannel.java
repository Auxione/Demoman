package Curio.SessionManagers.LogicManager.Interfaces;

public interface LogicChannel {
	public void setActiveState(int channelID,boolean state);

	public void readActiveState(boolean state);
}
