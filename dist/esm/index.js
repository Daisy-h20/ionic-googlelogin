import { registerPlugin } from '@capacitor/core';
const googleLogin = registerPlugin('googleLogin', {
    web: () => import('./web').then(m => new m.googleLoginWeb()),
});
export * from './definitions';
export { googleLogin };
//# sourceMappingURL=index.js.map