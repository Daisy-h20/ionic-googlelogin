import Foundation
import Capacitor
import GoogleSignIn

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(googleLoginPlugin)
public class googleLoginPlugin: CAPPlugin {
    
    // jsCode => here
    @objc func googleLogin(_ call: CAPPluginCall)
    
    {
        
        guard let googleClientId = call.getString("googleClientId") else {
            call.reject("googleClientId not provided")
            return
        }
        
        let googleSignInConfiguration = GIDConfiguration.init(clientID:googleClientId)
        
        guard let presentingVc = self.bridge?.viewController else {
            call.reject("No presenting view controller")
            return
        }
        
        GIDSignIn.sharedInstance.signIn(withPresenting: presentingVc) {user, error in
            
            if error != nil {
                call.reject(error.debugDescription)
            }
            
            if let result = user, let googleUser = result.user as? GIDGoogleUser, let email = googleUser.profile?.email {
                call.resolve([
                    "email": email
                ])
            }
        }
    }
    
    
}



