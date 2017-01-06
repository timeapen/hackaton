const companyId = '000133190USD';

class RadarController {
  /** @ngInject */
  constructor($http, $log, indicators) {
    $log.info('RADAR Controller');

    indicators.createRadarChartIndicators(companyId)
     .then(response => {
       this.chartData = response;
     });
  }

}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
