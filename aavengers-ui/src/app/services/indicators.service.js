import angualar from 'angular';

const serverDomain = '//localhost:8080/gaia';

class IndicatorsService {

  /** @ngInject **/
  constructor($http, $log) {
    this.$http = $http;
    this.$log = $log;
  }

  getGaiaIndicators() {
    return this.$http
      .get(`${serverDomain}/indicators`);
  }

  getGaiaIndicatorsTargets() {
    return this.$http
      .get(`${serverDomain}/indicators/settings`);
  }

}

export default angualar.module('services.indicators', [])
  .service('indicatorsService', IndicatorsService)
  .name;
