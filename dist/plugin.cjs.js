'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

const googleLogin = core.registerPlugin('googleLogin', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.googleLoginWeb()),
});

class googleLoginWeb extends core.WebPlugin {
    googleLogin(_options) {
        throw new Error('Method not implemented.');
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    googleLoginWeb: googleLoginWeb
});

exports.googleLogin = googleLogin;
//# sourceMappingURL=plugin.cjs.js.map
