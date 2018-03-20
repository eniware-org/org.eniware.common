/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.pidfile;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * {@link BundleActivator} to create a {@link PidFileCreator} instance.
 * 
 * @author matt
 * @version $Id$
 */
public class Activator implements BundleActivator {
	
	private PidFileCreator pidFile = null;

	@Override
	public void start(BundleContext context) throws Exception {
		if ( pidFile == null ) {
			pidFile = new PidFileCreator();
			pidFile.createPidFile();
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// nothing to do
	}

}
