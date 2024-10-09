# Google Login Plugin

## Setting Up

### 1. Google Developers Setup

- **Google Developers** 사이트에서 설정을 시작합니다.  
  [Google Cloud Console](https://console.cloud.google.com/apis/)

#### 1.1 Developer 설정

1. **OAuth 동의 화면**

   - 필수 작성
   - 사용자 유형: 외부
   - 민감하지 않은 범위: `../auth/userinfo.email`, `.../auth/userinfo.profile`, `openid` 설정

2. **Credentials**
   - OAuth 2.0 Client IDs를 Android, iOS 각각 생성합니다.

### 2. Google Plugin 설정

#### 2.1 Android 설정

- Android 설정은 해당 사항이 없습니다. (X)

#### 2.2 iOS 설정

1. **Xcode > info.plist**

   - 추가 내용:

   ```xml
   <key>CFBundleURLTypes</key>
   <array>
       <dict>
           <key>CFBundleTypeRole</key>
           <string>Editor</string>
           <key>CFBundleURLName</key>
           <string></string>
           <key>CFBundleURLSchemes</key>
           <array>
               <string>${clientIosKey}</string>
           </array>
       </dict>
   </array>
   ```

   - **iOS OAuth Key를 reverse 해서 작성**

   ```xml
   <key>GIDClientID</key>
   <string>${clientIosKey-Reverse}</string>
   ```

---

## Usage Example

```javascript
let googleClientId = this.platform.is('ios')
  ? '${ios Oauth Client Id}'
  : '${android Oauth Client Id}';

let emailObject = await googleLogin.googleLogin({
  googleClientId,
});

console.log(emailObject.email);
```
