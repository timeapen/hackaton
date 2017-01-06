// Constants should be eventually pulled out into configuration data, retrieved from the server
const accountId = '92692004|000133190USD|000100292HKD|000133077CHF|000133468EUR|000100675PHP|000667640GBP';
const userIndicators = ['Corruption', 'Conflict', 'Freedom', 'Environment', 'ReputationRisk'];

class PieController {

  /** @ngInject */
  constructor($http, $log, indicators) {
    this.$log = $log;
    $log.info('PIE Controller');
    this.indicators = indicators;

    this.year = '2016';
    this.years = ['2016', '2015', '2014'];

    this.indicatorPieChartData = [];
    angular.forEach(userIndicators, (userIndicator => {
      indicators.createPieChart(accountId, userIndicator)
       .then(response => {
         this.indicatorPieChartData.push(response);
       });
    }));
  }

  yearChange(year) {
    this.$log.info('year changed: ', year);
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};
