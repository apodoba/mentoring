package com.classloader.task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class MyClassLoader extends ClassLoader {

	private final static Logger LOGGER = Logger.getLogger(Main.class);
	private static final String JAR_PATH = Util.getParam("jars.directory");

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}

	public MyClassLoader() {
		this(MyClassLoader.class.getClassLoader());
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException {
		synchronized (getClassLoadingLock(name)) {
			// First, check if the class has already been loaded
			Class c = findLoadedClass(name);
			if (c == null) {
				long t0 = System.nanoTime();
				try {
					if (super.getParent() != null) {
						c = getParent().loadClass(name);
					}
				} catch (ClassNotFoundException e) {
					// ClassNotFoundException thrown if class not found
					// from the non-null parent class loader
				}

				if (c == null) {
					// If still not found, then invoke findClass in order
					// to find the class.
					long t1 = System.nanoTime();
					c = findClass(name);

					// this is the defining class loader; record the stats
					sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
					sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
					sun.misc.PerfCounter.getFindClasses().increment();
				}
			}
			if (c != null && resolve) {
				resolveClass(c);
			}
			return c;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class result = null;
		try {
			byte[] classBytes = getBytes(name);
			if (classBytes == null) {
				result = findSystemClass(name);
			} else {
				result = defineClass(name, classBytes, 0, classBytes.length);
			}
		} catch (ClassFormatError e) {
			throw new ClassNotFoundException("Incorrect class format", e);
		}
		return result;
	}

	private byte[] getBytes(String className) {
		File jarsDir = new File(JAR_PATH);
		if (jarsDir.exists() && jarsDir.isDirectory()) {
			File[] allJars = jarsDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return "jar".equals(FilenameUtils.getExtension(name));
				}
			});
			
			for (int i = 0; i < allJars.length; i++) {
				byte[] bytes = getBytesFromJar(className, allJars[i].getName());
				if (bytes != null)	return bytes;
			}
		}

		return null;
	}

	private byte[] getBytesFromJar(String className, String jarName) {
		try {
			JarFile jar = new JarFile(JAR_PATH +jarName);
			JarEntry entry = jar.getJarEntry(className.replace('.', '/') + ".class");
			InputStream is = jar.getInputStream(entry);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			int nextValue = is.read();
			while (-1 != nextValue) {
				byteStream.write(nextValue);
				nextValue = is.read();
			}

			byte bytes[] = byteStream.toByteArray();

			byteStream.close();
			is.close();
			jar.close();

			return bytes;
		} catch (Exception e) {
			LOGGER.error("Can not find class: " + className + " in jar", e);
		}

		return null;
	}
}
