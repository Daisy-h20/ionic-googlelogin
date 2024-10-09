export interface googleLoginPlugin {
  googleLogin(options: { googleClientId: string }): Promise<{ email: string }>;
}
