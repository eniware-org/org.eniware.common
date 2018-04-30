/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.pidfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Class for creating a PID file for the running JVM process.
 * 
 * <p>This class is designed for Unix-like operating systems. It 
 * will run an external process <code>/bin/sh -c echo $PPID</code>
 * to attempt to obtain the JVM's PID.</p>
 * 
 * <p>It will emit some logging lines to STDOUT about the creation
 * of the PID file, and if an error occurs a log line to STDERR.
 * A logging framework is not used to minimize dependencies so this
 * bundle can be deployed early to create the PID file as soon as 
 * possible after being activated.</p>
 * 
 * @version $Id$
 */
public class PidFileCreator {

	/** The system property for the PID file location. */
	public static final String SETTING_PID_FILE = "eniwarenetwork.pidfile";

	public void createPidFile() {
		String pidFilePath = System.getProperty(SETTING_PID_FILE);
		if ( pidFilePath == null || "".equals(pidFilePath) ) {
			System.out.println("PID file not created because system property "
						+SETTING_PID_FILE +" not set");
			return;
		}
		File pidFile = new File(pidFilePath);
		if ( pidFile.exists() ) {
			System.out.println("PID file exists: " +pidFile.getAbsolutePath());
			pidFile.delete();
		}

		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "echo $PPID");
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			Process pr = pb.start();
			pr.waitFor();
			if ( pr.exitValue() == 0 ) {
				in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String pid = in.readLine().trim();
				out = new PrintWriter(pidFile);
				out.write(pid+"\n");
				pidFile.deleteOnExit();
				System.out.println("PID file created: " +pidFile.getAbsolutePath());
			} else {
				System.err.println("Error getting PID, exit status: " +pr.exitValue());
			}
		} catch ( IOException e ) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			if ( in != null ) {
				try {
					in.close();
				} catch ( IOException e ) {
					// ignore
				}
			}
			if ( out != null ) {
				out.flush();
				out.close();
			}
		}
	}

}
