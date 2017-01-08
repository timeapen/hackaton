import angualar from 'angular';

const serverDomain = '//localhost:8080/gaia';

class Indicators {

  /** @ngInject **/
  constructor($http, $log) {
    this.$http = $http;
    this.$log = $log;
  }

  getGaiaIndicators() {
    return this.$http
      .get(`${serverDomain}/indicators`);
  }

}

export default angualar.module('services.indicators', [])
  .service('indicators', Indicators)
  .name;
