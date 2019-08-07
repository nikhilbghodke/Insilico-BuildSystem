package com.nikhil;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author Nikhil Ghodke
 *
 */

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Hello Everyone - Bundle is started!!!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye Everyone - Bundle is stoped!!!");
	}

}