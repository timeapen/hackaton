class MainController {

  /** @ngInject */
  constructor($http, $log) {
    this.$log = $log;
    $log.info('Main Controller');
    this.year = '2016';
    this.years = ['2016', '2015', '2014'];
  }

  yearChange(year) {
    this.$log.info('year changed: ', year);
  }
}

export const main = {
  template: require('./main.html'),
  controller: MainController
};
