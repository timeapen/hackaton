const accountId = '92692004|000133190USD|000100292HKD|000133077CHF|000133468EUR|000100675PHP|000667640GBP';

class RadarController {
  /** @ngInject */
  constructor($http, $log, indicators, chartData) {
    $log.info('RADAR Controller');

    indicators.getGaiaIndicators()
      .then(response => {
        const indicators = response.data;
        chartData.createRadarChartIndicators(accountId, indicators)
          .then(response => {
            this.chartData = response;
          });
      });
  }

}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
