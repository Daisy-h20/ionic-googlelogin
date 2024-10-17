package plugin.googlelogin.knc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.PluginHandle;

public class googleLogin extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerPlugin(googleLoginPlugin.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (this.bridge == null) {
            return;
        }

        if (requestCode == 1818) {
            PluginHandle pluginHandle = this.bridge.getPlugin(googleLoginPlugin.class.getSimpleName());
            if (pluginHandle != null) {
                if (pluginHandle.getInstance() instanceof googleLoginPlugin) {
                    googleLoginPlugin googleLoginPlugin = (googleLoginPlugin) pluginHandle.getInstance();
                    googleLoginPlugin.handleGoogleSignInResult(data);
                } else {
                    Log.d("NativeFn", "onActivityResult: type cast error");
                }
            } else {
                Log.d("NativeFn", "onActivityResult: no handle");
            }
        }
    }
}
