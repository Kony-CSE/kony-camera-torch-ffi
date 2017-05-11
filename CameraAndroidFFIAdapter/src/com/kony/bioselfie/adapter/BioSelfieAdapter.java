package com.kony.bioselfie.adapter;

public class BioSelfieAdapter {

	 private String user_name, IP, Port;
	    private final int TO_BREAK =3;
	    private int count = 0;


	    private static float leop;
		private static float lecp;
		private static float reop;
		private static float recp;
		private static float s;
		private static float ns;
	    private static int reqh, reqw, exp;


	    private final int LIVENESS_TASK = 1;
	    private final int SETTINGS_TASK = 2;
	    
	public static void invokeLibrary() {
		leop=0.4f;
	}
}
