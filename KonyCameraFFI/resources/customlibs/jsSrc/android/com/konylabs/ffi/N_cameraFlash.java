package com.konylabs.ffi;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import com.konylabs.api.TableLib;
import com.konylabs.vm.LuaTable;



import com.kony.cameraflash.CameraFlash;
import com.konylabs.libintf.Library;
import com.konylabs.libintf.JSLibrary;
import com.konylabs.vm.LuaError;
import com.konylabs.vm.LuaNil;


public class N_cameraFlash extends JSLibrary {

 
 
	public static final String startCameraFlash = "startCameraFlash";
 
 
	public static final String stopCameraFlash = "stopCameraFlash";
 
	String[] methods = { startCameraFlash, stopCameraFlash };


 Library libs[] = null;
 public Library[] getClasses() {
 libs = new Library[0];
 return libs;
 }



	public N_cameraFlash(){
	}

	public Object[] execute(int index, Object[] params) {
		// TODO Auto-generated method stub
		Object[] ret = null;
 try {
		int paramLen = params.length;
 int inc = 1;
		switch (index) {
 		case 0:
 if (paramLen != 0){ return new Object[] {new Double(100),"Invalid Params"}; }
 ret = this.startCameraFlash( );
 
 			break;
 		case 1:
 if (paramLen != 0){ return new Object[] {new Double(100),"Invalid Params"}; }
 ret = this.stopCameraFlash( );
 
 			break;
 		default:
			break;
		}
 }catch (Exception e){
			ret = new Object[]{e.getMessage(), new Double(101), e.getMessage()};
		}
		return ret;
	}

	public String[] getMethods() {
		// TODO Auto-generated method stub
		return methods;
	}
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "cameraFlash";
	}


	/*
	 * return should be status(0 and !0),address
	 */
 
 
 	public final Object[] startCameraFlash( ){
 
		Object[] ret = null;
 com.kony.cameraflash.CameraFlash.startCameraFlash( );
 
 ret = new Object[]{LuaNil.nil, new Double(0)};
 		return ret;
	}
 
 
 	public final Object[] stopCameraFlash( ){
 
		Object[] ret = null;
 com.kony.cameraflash.CameraFlash.stopCameraFlash( );
 
 ret = new Object[]{LuaNil.nil, new Double(0)};
 		return ret;
	}
 
};
