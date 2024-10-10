var capacitorgoogleLogin = (function (exports, core) {
    'use strict';

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

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
