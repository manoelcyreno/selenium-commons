package com.liferay.gs.testFramework.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

/**
 * @author Italo Laino
 */
public class ConcurrentSuite extends Suite {

	public ConcurrentSuite(Class<?> clazz, RunnerBuilder builder) throws InitializationError {
		super(clazz, builder);

		setScheduler(new MultipleThreadPoolScheduler());
	}

	private final int _NUMBER_OF_THREADS = SeleniumReadPropertyKeys.getNumberOfBrowserInParallel();

	private class MultipleThreadPoolScheduler implements RunnerScheduler {
		public MultipleThreadPoolScheduler() {
			String threads = System.getProperty("concurrentSuite.numberOfThreads", Integer.toString(_NUMBER_OF_THREADS));

			int numThreads = Integer.parseInt(threads);

			_executor = Executors.newFixedThreadPool(numThreads);
		}

		@Override
		public void finished() {
			_executor.shutdown();

			try {
				_executor.awaitTermination(1, TimeUnit.HOURS);
			} catch (InterruptedException exc) {
				throw new RuntimeException(exc);
			}
		}

		@Override
		public void schedule(Runnable childStatement) {
			System.out.println(String.format("Scheduled (%s): %s", childStatement.getClass(), childStatement));

			_executor.submit(childStatement);
		}

		private ExecutorService _executor;

	}

}