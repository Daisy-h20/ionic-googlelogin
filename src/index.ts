import { registerPlugin } from '@capacitor/core';

import type { googleLoginPlugin } from './definitions';

const googleLogin = registerPlugin<googleLoginPlugin>('googleLogin', {
  web: () => import('./web').then(m => new m.googleLoginWeb()),
});

export * from './definitions';
export { googleLogin };
