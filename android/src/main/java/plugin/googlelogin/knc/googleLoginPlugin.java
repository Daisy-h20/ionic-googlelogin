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
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

@CapacitorPlugin(name = "googleLogin")
public class googleLoginPlugin extends Plugin {

    private static PluginCall googleCall;
    private GoogleSignInClient mGoogleSignInClient;

    @PluginMethod
    public void googleLogin(PluginCall call) {
        googleCall = call;

        String googleClientId = googleCall.getString("googleClientId");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleClientId) // Google Client ID를 여기에 입력하세요.
            .requestEmail()
            .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mGoogleSignInClient.signOut(); // 이전 세션 로그아웃
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        getActivity().startActivityForResult(signInIntent, 1818);
    }

    public void handleGoogleSignInResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            returnGoogleLogin(account.getEmail());
        } catch (ApiException e) {
            Log.e("SocialLoginPlugin", "Login failed: " + e.getStatusCode() + " " + e.getMessage());
            googleCall.reject("로그인 실패: " + e.getMessage());
        }
    }

    public void returnGoogleLogin(String email) {
        if (email != null) {
            JSObject ret = new JSObject();
            ret.put("email", email);
            googleCall.resolve(ret);
        } else {
            googleCall.reject("하영님에게 문의하세요");
        }
    }
}
