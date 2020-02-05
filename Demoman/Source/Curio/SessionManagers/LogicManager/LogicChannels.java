package Curio.SessionManagers.LogicManager;

import java.util.HashMap;

public class LogicChannels {
	private HashMap<Integer, Boolean> channel;

	public LogicChannels() {
		this.channel = new HashMap<Integer, Boolean>();
	}

	public LogicChannels(int maxChannels) {
		this.channel = new HashMap<Integer, Boolean>(maxChannels);
	}

	public boolean getState(int channelID) {
		if (channel.containsKey(channelID) == true) {
			return channel.get(channelID);
		}
		return false;
	}

	public void setState(int channelID, boolean state) {
		if (channel.containsKey(channelID) == false) {
			this.channel.put(channelID, state);
		}

		else if (channel.containsKey(channelID) == true) {
			this.channel.replace(channelID, state);
		}
	}
}
