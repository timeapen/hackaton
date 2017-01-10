// Constants should be eventually pulled out into configuration data, retrieved from the server
const accounts = '92692004|000133190USD|000100292HKD|000133077CHF|000133468EUR|000100675PHP|000667640GBP';
const userIndicators = ['Corruption', 'Conflict', 'BusinessFreedom', 'Environment', 'ReputationRisk'];

class PieController {

  /* @ngInject */
  constructor($http, $log, chartData) {
    $log.info('PIE Controller');
    this.$log = $log;
    this.indicators = chartData;

    this.year = '2016';
    this.years = ['2016', '2015', '2014'];

    this.indicatorPieChartData = [];
    angular.forEach(userIndicators, (userIndicator => {
      chartData.createPieChart(accounts, userIndicator)
       .then(response => {
         this.indicatorPieChartData.push(response);
       });
    }));
  }
}

export const pie = {
  template: require('./pie.html'),
  controller: PieController
};
