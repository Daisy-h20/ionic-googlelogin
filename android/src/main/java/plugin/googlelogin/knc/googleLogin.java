package plugin.googlelogin.knc;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import com.getcapacitor.BridgeActivity;

public class googleLogin extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPlugin(googleLoginPlugin.class);
    }
}
