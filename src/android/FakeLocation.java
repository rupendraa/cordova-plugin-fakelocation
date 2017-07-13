package tomloprod;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.provider.Settings.Secure;
import android.provider.Settings;

import android.location.Location;
import android.location.LocationManager;

import android.content.Context;

public class FakeLocation extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
         String result="";  

        if (action.equals("check")) {
			if (android.os.Build.VERSION.SDK_INT < 23) {
				if (Secure.getString(this.cordova.getActivity().getContentResolver(), Secure.ALLOW_MOCK_LOCATION).equals("1")) {
					status = PluginResult.Status.OK;
					result = "true";
				} else {
					status = PluginResult.Status.OK;
					result = "false";
				}
			} else {
				status = PluginResult.Status.OK;
				result = "false";
			}
        }else if (action.equals("IsCachedLocationMocked"))
        {
            try{
                android.location.LocationManager lManager = (android.location.LocationManager)cordova.getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                android.location.Location loc = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                boolean locationFromMock = loc.isFromMockProvider();
                if(locationFromMock){
                    status = PluginResult.Status.OK;
                    result = "true";
                }
                else{
                    status = PluginResult.Status.ERROR;
                    result = "false";
                }
            }catch(Exception err){
                status = PluginResult.Status.OK;
                result = err.toString();
            }
        }else if(action.equals("IsGPSEnabled")){
				android.content.ContentResolver contentResolver = cordova.getActivity().getApplicationContext().getContentResolver();
        		boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
                if(gpsEnabled){
                    status = PluginResult.Status.OK;
                    result = "true";
                }
                else{
                    status = PluginResult.Status.OK;
                    result = "false";
                }		
			}
        callbackContext.sendPluginResult(new PluginResult(status,result));
		return true;
    }
}