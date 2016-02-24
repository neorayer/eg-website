package com.skymiracle.gameUnion.models.msg;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.skymiracle.gameUnion.models.GameRoom;
import com.skymiracle.gameUnion.models.User;
import com.skymiracle.mdo5.MList;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.mdo5.Mdo.Length;
import com.skymiracle.sor.exception.AppException;
import com.skymiracle.util.CalendarUtil;

public abstract class RtMsg<T extends RtMsg<T>> extends Mdo<T> {

	public static class X<T extends RtMsg<T>> extends Mdo_X<T> {

		private Map<String, Queue<RtMsg<?>>> uMsgQueueMap = new ConcurrentHashMap<String, Queue<RtMsg<?>>>();

		public void send(RtMsg<?> msg, GameRoom room) throws AppException,
				Exception {
			MList<User> usersInRoom = room.getUsers();
			for (User user : usersInRoom) {
				Queue<RtMsg<?>> q = getQueue(user);
				if (q == null)
					continue;
				q.add(msg);
				synchronized (q) {
					q.notifyAll();
				}
			}
		}

		public void send(RtMsgChat msg) throws AppException, Exception {
			// 发给接受者
			sendToDest(msg);
			// 发给发送者
			{
				User user = msg.getSender().load();
				Queue<RtMsg<?>> q = getQueue(user);
				if (q == null)
					return;
				q.add(msg);
				synchronized (q) {
					q.notifyAll();
				}
			}
		}

		public void sendToDest(RtMsg<?> msg) throws AppException, Exception {
			// 发给接受者
			{
				User user = new User(msg.getDest()).load();
				Queue<RtMsg<?>> q = getQueue(user);
				if (q == null)
					return;
				q.add(msg);
				synchronized (q) {
					q.notifyAll();
				}
			}

		}

		/**
		 * 注意的user必须是Load过的
		 * @param user
		 * @return
		 */
		private Queue<RtMsg<?>> getQueue(User user) {
			//当user处于超时状态时，不返回队列，防止积压过多造成内存大量占用
			if (user.isTimeout()) {
				return null;
			}
			String key = user.getUsername();
			Queue<RtMsg<?>> q = uMsgQueueMap.get(key);
			if (q == null) {
				q = new ConcurrentLinkedQueue<RtMsg<?>>();
				uMsgQueueMap.put(key, q);
			}
			return q;
		}

		public void removeUserQueue(User user) {
			uMsgQueueMap.remove(user.getUsername());
		}

		public RtMsg<?> getMsg(User user) {
			Queue<RtMsg<?>> q = getQueue(user);
			if (q == null)
 				return null;
			return q.poll();
		}

		public void waitFor(User user, int milSeconds) throws AppException,
				Exception {
			Queue<RtMsg<?>> q = getQueue(user);
			if (q == null)
				return;
			synchronized (q) {
				q.wait(milSeconds);
			}
		}

		public int getMsgQueueSize(User user) {
			Queue<RtMsg<?>> q = getQueue(user);
			if (q == null)
				return 0;
			return q.size();
		}

	}

	protected String cmd;

	@Length(64)
	protected String dest;

	protected String msgTime;

	public abstract String getCmd();

	public abstract void setCmd(String cmd);

	public RtMsg() {
		super();
		msgTime = CalendarUtil.getLocalDateTime("HH:mm:ss");
	}

	public RtMsg(Mdo_X<T> mdoX) {
		super(mdoX);
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	@Override
	public String[] keyNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String table() {
		// TODO Auto-generated method stub
		return null;
	}

}
