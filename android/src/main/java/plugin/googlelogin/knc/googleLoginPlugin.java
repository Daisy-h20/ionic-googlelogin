package plugin.googlelogin.knc;

import android.content.Intent;
import android.util.Log;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

@CapacitorPlugin(name = "googleLogin")
public class googleLoginPlugin extends Plugin {

    private static PluginCall googleCall;

    @PluginMethod
    public void googleLogin(PluginCall call) {
        Log.d("googleLogin", "googleLogin: 1");
        googleCall = call;

        Intent intent = new Intent(getActivity(), googleLogin.class);
        getActivity().startActivity(intent);
    }

    public void returnGoogleLogin(String email) {
        Log.d("googleLogin", "googleLogin: 4");
        if (email != null) {
            JSObject ret = new JSObject();
            ret.put("email", email);
            googleCall.resolve(ret);
        } else {
            googleCall.reject("하영님에게 문의하세요");
        }
    }
}
