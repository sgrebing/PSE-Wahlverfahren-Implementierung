package edu.pse.beast.propertychecker;

import java.io.BufferedWriter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import edu.pse.beast.propertychecker.jna.Win32Process;
import edu.pse.beast.toolbox.ErrorLogger;
import edu.pse.beast.toolbox.FileLoader;

public class WindowsProcess extends CBMCProcess {
	protected int maxWaits = 5;

	public WindowsProcess(int voters, int candidates, int seats, String advanced, File toCheck, CheckerFactory parent) {
		super(voters, candidates, seats, advanced, toCheck, parent);
	}

	@Override
	protected Process createProcess(File toCheck, int voters, int candidates, int seats, String advanced) {

		// trace is mandatory under windows, or the counter example couldn't get
		// generated
		advanced = advanced + " --trace";

		// set the values for the voters, candidates and seats
		String arguments = advanced + " -D V=" + voters + " -D C=" + candidates + " -D S=" + seats;

		String vsCmd = null;
		Process startedProcess = null;

		try {
			vsCmd = getVScmdPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (vsCmd == null) {
			ErrorLogger.log("Cant find the VScmd. Is it installed correctly?");
		}

		String cbmcEXE = FileLoader.getFileFromRes("/cbmcWIN/cbmc.exe");

		// TODO this is just a debug file
		toCheck = new File("./src/main/resources/c_tempfiles/test.c");
		ErrorLogger.log("WindowsProcess.java lien 48 has to be removed, when the code creation works");

		// because windows is weird the whole call that would get placed inside
		// VScmd has to be in one giant string
		String cbmcCall = "\"" + vsCmd + "\"" + " & " + cbmcEXE + " " + "\"" + toCheck.getAbsolutePath() + "\"" + " "
				+ arguments;

		// this call starts a new VScmd isntance and lets cbmc run in it
		ProcessBuilder prossBuild = new ProcessBuilder("cmd.exe", "/c", cbmcCall);

		System.out.println("AUFRUF:" + String.join(" ", prossBuild.command()));

		try {
			// save the new process in this var
			startedProcess = prossBuild.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return startedProcess;
	}

	@Override
	protected void stopProcess() {
		if (!process.isAlive()) {
			ErrorLogger.log("Warning, process isn't alive anymore");
			return;
		} else {

			
			
			int pid = getWindowsProcessId(process);

			
			
			if (pid >= 0) {
			//	int pid = handle;
				Win32Process toKill;
				try {
					toKill = new Win32Process(pid);
					kill(toKill);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ErrorLogger.log("Error getting the Process Handle for windows");
			}

			// possible solution:
			// http://stackoverflow.com/questions/10124299/how-do-i-terminate-a-process-tree-from-java

			// process.destroyForcibly();
			System.out.println("destroyed " + process.isAlive());
		}

		if (process.isAlive()) {
			ErrorLogger.log("Warning, the program was unable to shut down the CBMC Process \n"
					+ "Please kill it manually, especially if it starts taking up a lot of ram");
		}
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private String getVScmdPath() throws IOException {
		// TODO: this could be cached, because it takes a significant time on
		// Windows every startup
		Path x86 = new File("C:/Program Files (x86)").toPath();
		Path x64 = new File("C:/Program Files").toPath();
		String searchTerm = "Microsoft Visual Studio";
		String pathToBatch = "/Common7/Tools/VsDevCmd.bat";

		ArrayList<String> toSearch = new ArrayList<>();
		Files.list(x86).filter(Files::isReadable).filter(path -> path.toString().contains(searchTerm))
				.forEach(VSPath -> toSearch.add(VSPath.toString()));
		Files.list(x64).filter(Files::isReadable).filter(path -> path.toString().contains(searchTerm))
				.forEach(VSPath -> toSearch.add(VSPath.toString()));

		for (Iterator<String> iterator = toSearch.iterator(); iterator.hasNext();) {
			String toCheck = ((String) iterator.next()) + pathToBatch;

			if (Files.isReadable(new File(toCheck).toPath())) {
				return toCheck;
			}
		}

		String userInput = JOptionPane
				.showInputDialog("The progam was unable to find a Developer Command Prompt for Visual Studio. \n"
						+ " Please search for it on your own and paste the path to the batch-file here!");

		// important that the check against null is done first, so invalid
		// inputs are caught without causing an error
		if (userInput != null && Files.isReadable(new File(userInput).toPath()) && userInput.contains("VsDevCmd.bat")) {
			return userInput;
		} else {
			System.err.println("The provided path did not lead to the command prompt. Shutting down now.");
			return null;
		}
	}

	@Override
	protected String sanitizeArguments(String toSanitize) {
		return toSanitize;
	}

	public void kill(Win32Process target) throws IOException {
		List<Win32Process> children = target.getChildren();
		target.terminate();
		for (Win32Process child : children)
			kill(child);
	}

	private int getWindowsProcessId(Process proc) {
		
		System.out.println("neue methode");
		
		if (proc.getClass().getName().equals("java.lang.Win32Process")
				|| proc.getClass().getName().equals("java.lang.ProcessImpl")) {
			
			
			System.out.println("ist windows");
			
			/* determine the pid on windows plattforms */
			try {
				Field f = proc.getClass().getDeclaredField("handle");
				f.setAccessible(true);
				long handl = f.getLong(proc);
				Kernel32 kernel = Kernel32.INSTANCE;
				// be careful! If you use 3.3.0 version of JNA, you wil not
				// found W32API.HANDLE. In stead, you should use WinNT.HANDLE
				WinNT.HANDLE handle = new WinNT.HANDLE();
				// be careful for the security issue.

				Field toSet = handle.getClass().getDeclaredField("immutable");

				toSet.setAccessible(true);

				boolean savedState = toSet.getBoolean(handle);

				System.out.println("saved: " + savedState);
				
//				toSet.setBoolean(toSet, false);

				
				handle.setPointer(Pointer.createConstant(handl));
				
				
				int pid = kernel.GetProcessId(handle);

//				toSet.set(toSet, savedState);
//				toSet.setAccessible(false);

				System.out.println("neue methode zur bestimmung: " + pid);
				
				return pid;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	
	/* Copyright (c) 2007 Timothy Wall, All Rights Reserved
	*
	* This library is free software; you can redistribute it and/or
	* modify it under the terms of the GNU Lesser General Public
	* License as published by the Free Software Foundation; either
	* version 2.1 of the License, or (at your option) any later version.
	* 
	* This library is distributed in the hope that it will be useful,
	* but WITHOUT ANY WARRANTY; without even the implied warranty of
	* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
	* Lesser General Public License for more details.  
	*/
	public interface W32Errors {
		   int NO_ERROR               = 0;
	   int ERROR_INVALID_FUNCTION = 1;
	   int ERROR_FILE_NOT_FOUND   = 2;
	   int ERROR_PATH_NOT_FOUND   = 3;
	}


//	public interface Kernel32 extends W32API {
//		Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32",
//				Kernel32.class, DEFAULT_OPTIONS);
//		/* http://msdn.microsoft.com/en-us/library/ms683179(VS.85).aspx */
//		HANDLE GetCurrentProcess();
//		/* http://msdn.microsoft.com/en-us/library/ms683215.aspx */
//		int GetProcessId(WinNT.HANDLE Process);
//			int GetCurrentProcessId();
//	}

	/** Base type for most W32 API libraries.  Provides standard options
	 * for unicode/ASCII mappings.  Set the system property w32.ascii
	 * to true to default to the ASCII mappings.
	 */
	public interface W32API extends StdCallLibrary, W32Errors {
	  
	    /** Standard options to use the unicode version of a w32 API. */
	    Map UNICODE_OPTIONS = new HashMap() {
	        /**
			 *
			 */
			private static final long serialVersionUID = 1L;
			{
	            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
	            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
	        }
	    };
	  
	    /** Standard options to use the ASCII/MBCS version of a w32 API. */
	    Map ASCII_OPTIONS = new HashMap() {
	        /**
			 *
			 */
			private static final long serialVersionUID = 1L;
			{
	            put(OPTION_TYPE_MAPPER, W32APITypeMapper.ASCII);
	            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.ASCII);
	        }
	    };
	    Map DEFAULT_OPTIONS = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS : UNICODE_OPTIONS;
	  
	    public class HANDLE extends PointerType {
	        public Object fromNative(Object nativeValue, FromNativeContext context) {
	            Object o = super.fromNative(nativeValue, context);
	            if (INVALID_HANDLE_VALUE.equals(o))
	                return INVALID_HANDLE_VALUE;
	            return o;
	        }
	    }
	    /** Constant value representing an invalid HANDLE. */
	    HANDLE INVALID_HANDLE_VALUE = new HANDLE() {
	        { super.setPointer(Pointer.createConstant(-1)); }
	        public void setPointer(Pointer p) {
	            throw new UnsupportedOperationException("Immutable reference");
	        }
	    };
	}







}
