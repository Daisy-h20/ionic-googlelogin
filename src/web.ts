import { WebPlugin } from '@capacitor/core';

import type { googleLoginPlugin } from './definitions';

export class googleLoginWeb extends WebPlugin implements googleLoginPlugin {
  googleLogin(_options: {
    googleClientId: string;
  }): Promise<{ email: string }> {
    throw new Error('Method not implemented.');
  }
}
