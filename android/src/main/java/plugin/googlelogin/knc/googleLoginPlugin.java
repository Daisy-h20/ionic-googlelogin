package plugin.googlelogin.knc;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

@CapacitorPlugin(name = "googleLogin")
public class googleLoginPlugin extends Plugin {

    // see https://developers.google.com/android/reference/com/google/android/gms/auth/api/signin/GoogleSignInStatusCodes#SIGN_IN_CANCELLED
    private static final int SIGN_IN_CANCELLED = 12501;

    private GoogleSignInClient googleSignInClient;

    public void loadSignInClient(String clientId) {
        Log.d("googleLogin", "loadSignInClient: " + clientId);
        GoogleSignInOptions.Builder googleSignInBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail();

        GoogleSignInOptions googleSignInOptions = googleSignInBuilder.build();
        googleSignInClient = GoogleSignIn.getClient(this.getContext(), googleSignInOptions);
    }

    @Override
    public void load() {}

    @PluginMethod
    public void googleLogin(PluginCall call) {
        String googleClientId = call.getString("googleClientId");

        Log.d("googleLogin", "googleLogin: " + googleClientId);
        loadSignInClient(googleClientId);

        if (googleSignInClient == null) {
            rejectWithNullClientError(call);
            return;
        }

        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(call, signInIntent, "signInResult");
    }

    @ActivityCallback
    protected void signInResult(PluginCall call, ActivityResult result) {
        if (call == null) return;

        Task<GoogleSignInAccount> completedTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String email = account.getEmail();

            JSObject ret = new JSObject();
            ret.put("email", email);
            call.resolve(ret);
        } catch (ApiException e) {
            if (SIGN_IN_CANCELLED == e.getStatusCode()) {
                call.reject("The user canceled the sign-in flow.", "" + e.getStatusCode());
            } else {
                call.reject("Something went wrong", "" + e.getStatusCode());
            }
        }
    }

    private void rejectWithNullClientError(final PluginCall call) {
        call.reject("Google services are not ready. Please call initialize() first");
    }
}
