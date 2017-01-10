class MainController {

  /* @ngInject */
  constructor($http, $log, $mdMedia) {
    this.$log = $log;
    $log.info('Main Controller');
    this.$mdMedia = $mdMedia;
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
