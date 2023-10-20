/**
 * 
 */
package com.dailycodebuffer.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public final class TimerUtil {
	private static final Log log = LogFactory.getLog(TimerUtil.class);

	private static TimerUtil instance = null;

	private static boolean isCanceled = false;

	private Timer timer = null;

	private TimerUtil() {
		log.debug("TimerUtil is instantiated.");
		this.timer = new Timer();
	}

	private void reloadTimer() {
		log.info("Reload Timer in TimerUtil.");
		if (!isCanceled) {
			this.timer.cancel();
			this.timer = new Timer();
		} else {
			log.warn("Cannot reload a cancelled Timer.");
		}
	}

	public static synchronized TimerUtil getInstance() {
		if (instance == null) {
			instance = new TimerUtil();
		}
		return instance;
	}

	public void cancel() {
		log.debug("TimerUtil.cancel() is called");

		isCanceled = true;
		this.timer.cancel();
	}

	public boolean isTimerCanceled() {
		return isCanceled;
	}

	public void schedule(TimerTask task, Date firstTime, long period) {
		if (!isCanceled) {
			try {
				this.timer.schedule(task, firstTime, period);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
		}
	}

	public void schedule(TimerTask task, Date time) {
		if (!isCanceled) {
			try {
				this.timer.schedule(task, time);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
		}
	}

	public void schedule(TimerTask task, long delay) {
		if (!isCanceled) {
			try {
				this.timer.schedule(task, delay);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
		}
	}

	public void schedule(TimerTask task, long delay, long period) {
		if (!isCanceled) {
			try {
				this.timer.schedule(task, delay, period);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
		}
	}

	public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
		if (!isCanceled)
			try {
				this.timer.schedule(task, firstTime, period);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
	}

	public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
		if (!isCanceled)
			try {
				this.timer.schedule(task, delay, period);
			} catch (IllegalStateException ex) {
				log.error("Cannot schedule task!", ex);
				reloadTimer();
			}
	}
}
