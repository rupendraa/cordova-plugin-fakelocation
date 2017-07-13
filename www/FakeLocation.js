var fakeLocation = {
  check: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'FakeLocation', 'check', []);
  },
  IsCachedLocationMocked: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'FakeLocation', 'IsCachedLocationMocked', []);
  },
  IsGPSEnabled: function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'FakeLocation', 'IsGPSEnabled', []);
  }
}

if (!window.plugins) { window.plugins = {}; }

window.plugins.fakeLocation = fakeLocation;
return window.plugins.fakeLocation;