const accounts = '92692004|000133190USD|000100292HKD|000133077CHF|000133468EUR|000100675PHP|000667640GBP';

class RadarController {
  /** @ngInject */
  constructor($http, $log, indicatorsService, chartData) {
    $log.info('RADAR Controller');

    indicatorsService.getGaiaIndicators()
      .then(response => {
        const indicators = response.data;
        chartData.createRadarChartIndicators(accounts, indicators)
          .then(response => {
            this.chartData = response;

            indicatorsService.getGaiaIndicatorsTargets()
              .then(response => {
                chartData.getRadarChartTargetIndicatorData(response.data)
                  .then(response => {
                    const series = [];
                    series.push(response);
                    series.push(this.chartData.series[0]);
                    this.chartData.series = series;
                  });
              });
          });
      });
  }

}

export const radar = {
  template: require('./radar.html'),
  controller: RadarController
};
