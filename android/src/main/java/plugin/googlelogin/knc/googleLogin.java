package plugin.googlelogin.knc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.PluginHandle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class googleLogin extends BridgeActivity {

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Google Sign-In 설정
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1818);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1818) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("GoogleLoginActivity", "Login successful: " + account.getEmail());

                // Correct way to get the plugin instance
                PluginHandle pluginHandle = getBridge().getPlugin("googleLogin");
                if (pluginHandle != null) {
                    googleLoginPlugin plugin = (googleLoginPlugin) pluginHandle.getInstance();
                    plugin.returnGoogleLogin(account.getEmail());
                } else {
                    Log.e("GoogleLoginActivity", "Plugin handle is null");
                }
            } catch (ApiException e) {
                Log.e("GoogleLoginActivity", "Login failed: " + e.getMessage());
            }
            finish(); // Activity 종료
        }
    }
}
